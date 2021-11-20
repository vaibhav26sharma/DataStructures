package hashTables;

import java.util.*;

class Entry<K, V> {
    int hash;
    K key;
    V value;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;

        //Whenever an entry is generated,
        //a hashcode corresponding to its key
        //is automatically generated and saved
        this.hash = key.hashCode();
    }

    //Not an overridden method, but check if two entries are same
    //entry.equals(otherEntry)
    public boolean equals(Entry<K, V> other) {
        //if hash isn't equal, they surely can't be equal
        if (hash != other.hash) return false;

        //if hash is same check if keys are equal to confirm the equality
        return key.equals(other.key);
    }

    @Override
    public String toString() {
        return key + " => " + value;
    }
}

public class HashTableSeparateChaining<K, V> implements Iterable<K> {

    private static final int DEFAULT_CAPACITY = 3;

    //LF = total items in table / total size of table
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    //Max allowed LF surpassing which there
    // will be a need to resize the table
    private double maxLoadFactor;

    //Maximum table size
    private int capacity;

    //threshold = capacity * max_LF
    private int threshold;

    //Current size of the table
    private int size = 0;

    private LinkedList<Entry<K, V>>[] table;

    public HashTableSeparateChaining() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    public HashTableSeparateChaining(int capacity, double maxLoadFactor) {
        if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity");
        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
            throw new IllegalArgumentException("Illegal load factor");

        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(capacity, DEFAULT_CAPACITY);
        threshold = (int) (this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This converts hashValue to a index in range of the capacity
     * <p>
     * Masking with "& 0x7FFFFFFF" remove the negative sign & "%capacity" places hash value in
     * range [0,capacity)
     *
     * @param keyHash -> calculated hashcode of the key
     * @return normalized index in range [0,capacity)
     */
    public int normalizeIndex(int keyHash) {
        return (keyHash & 0x7FFFFFFF) % capacity;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean containsKey(K key) {
        return hasKey(key);
    }

    // Returns true/false depending on whether a key is in the hash table
    public boolean hasKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    public V put(K key, V value) {
        return insert(key, value);
    }

    public V add(K key, V value) {
        return insert(key, value);
    }

    public V insert(K key, V value) {
        if (key == null) throw new IllegalArgumentException("Null key");
        //Create a new entry, since linkedlist needs to have entry objects
        //for every Key Value pair
        Entry<K, V> newEntry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(newEntry.hash);
        return bucketInsertEntry(bucketIndex, newEntry);
    }

    public V get(K key) {
        if (key == null) return null;
        //fetch the bucket in which key is saved
        int bucketIndex = normalizeIndex(key.hashCode());
        //Fetch entry on that bucket index from table
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) return entry.value;
        return null;
    }

    public V remove(K key) {
        if (key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    private V bucketRemoveEntry(int bucketIndex, K key) {
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) {
            LinkedList<Entry<K, V>> links = table[bucketIndex];
            links.remove(entry);
            --size;
            return entry.value;
        }
        //if the entry is not found
        //or doesn't exist in bucket, return null
        else
            return null;
    }

    private V bucketInsertEntry(int bucketIndex, Entry<K, V> entry) {
        //Check if a bucket or linked list already exist at that index
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];

        //if a bucket doesn't exist, create it
        if (bucket == null) table[bucketIndex] = new LinkedList<>();

        //Check if entry already exists
        Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);

        //if it doesn't exist, create it
        if (existentEntry == null) {
            bucket.add(entry);
            if (++size > threshold) resizeTable();
            //use null to indicate that there was no previous entry
            return null;
        }
        //if entry already existed, update its value
        else {
            V oldVal = existentEntry.value;
            existentEntry.value = entry.value;
            return oldVal;
        }
    }

    // Finds and returns a particular entry in a given bucket if it exists, returns null otherwise
    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
        if (key == null) return null;
        LinkedList<Entry<K, V>> bucket = table[bucketIndex];
        if (bucket == null) return null;
        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) return entry;
        }
        return null;
    }

    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];

        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry : table[i]) {
                    int bucketIndex = normalizeIndex(entry.hash);
                    //if bucket already exists at that index, fetch it
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    //if bucket doesn't exist, create it
                    if (bucket == null) newTable[bucketIndex] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }

                // Avoid memory leak. Help the GC
                table[i].clear();
                table[i] = null;
            }
        }
        table = newTable;
    }


    // Returns the list of keys found within the hash table
    public List<K> keys() {

        List<K> keys = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
            if (bucket != null) for (Entry<K, V> entry : bucket) keys.add(entry.key);
        return keys;
    }

    // Returns the list of values found within the hash table
    public List<V> values() {

        List<V> values = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
            if (bucket != null) for (Entry<K, V> entry : bucket) values.add(entry.value);
        return values;
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    // Return an iterator to iterate over all the keys in this map
    @Override
    public java.util.Iterator<K> iterator() {
        final int elementCount = size();
        return new java.util.Iterator<K>() {

            int bucketIndex = 0;
            java.util.Iterator<Entry<K, V>> bucketIter = (table[0] == null) ? null : table[0].iterator();

            @Override
            public boolean hasNext() {

                // An item was added or removed while iterating
                if (elementCount != size) throw new java.util.ConcurrentModificationException();

                // No iterator or the current iterator is empty
                if (bucketIter == null || !bucketIter.hasNext()) {

                    // Search next buckets until a valid iterator is found
                    while (++bucketIndex < capacity) {
                        if (table[bucketIndex] != null) {
                            // Make sure this iterator actually has elements -_-
                            java.util.Iterator<Entry<K, V>> nextIter = table[bucketIndex].iterator();
                            if (nextIter.hasNext()) {
                                bucketIter = nextIter;
                                break;
                            }
                        }
                    }
                }
                return bucketIndex < capacity;
            }

            @Override
            public K next() {
                return bucketIter.next().key;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Returns a string representation of this hash table
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (int i = 0; i < capacity; i++) {
            if (table[i] == null) continue;
            for (Entry<K, V> entry : table[i]) sb.append(entry + ", ");
        }
        sb.append("}");
        return sb.toString();
    }
}
