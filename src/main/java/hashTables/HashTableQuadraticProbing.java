package hashTables;

import java.util.LinkedList;
import java.util.Queue;

public class HashTableQuadraticProbing<Key, Value> {
    private static final int DEFAULT_CAPACITY = 4;

    // number of key-value pairs in the symbol table
    private int keyCount;
    // size of linear probing table
    private int capacity;
    //array of keys
    private Key[] keys;
    //array of values
    private Value[] values;

    public HashTableQuadraticProbing() {
        this(DEFAULT_CAPACITY);
    }

    public HashTableQuadraticProbing(int capacity) {
        this.capacity = capacity;
        keyCount = 0;
        keys = (Key[]) new Object[capacity];
        values = (Value[]) new Object[capacity];
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return keyCount;
    }

    /**
     * Returns true if this symbol table is empty.
     *
     * @return {@code true} if this symbol table is empty;
     * {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("Null key");
        return get(key) != null;
    }

    // hash function for keys - returns value between 0 and Capacity-1
    private int hash(Key key) {
        return (key.hashCode() & 0x7FFFFFFF) % capacity;
    }

    private void resize(int newCapacity) {
        //Create new table with updated capacity
        HashTableQuadraticProbing<Key, Value> temp = new HashTableQuadraticProbing<>(newCapacity);
        //Rehash and insert all values again
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null)
                temp.put(keys[i], values[i]);
        }
        keys = temp.keys;
        values = temp.values;
        capacity = temp.capacity;
    }

    /**
     * Inserts the specified key-value pair into the symbol table, overwriting the old
     * value with the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("Null key");

        if (val == null) {
            delete(key);
            return;
        }

        // double table size if 50% full
        if (keyCount > capacity / 2) resize(2 * capacity);

        int i;

        //offset for quadratic probing
        int h = 1;

        //Probe quadratically and find an empty
        //index to insert key in
        for (i = hash(key); keys[i] != null; i = (i + h * h++)) {
            //if key exists already, update the value
            if (keys[i].equals(key)) {
                values[i] = val;
                return;
            }
        }

        //once an empty index 'i' is found while linear probing, insert
        //K,V pair
        keys[i] = key;
        values[i] = val;
        keyCount++;
    }

    /**
     * Returns the value associated with the specified key.
     *
     * @param key the key
     * @return the value associated with {@code key};
     * {@code null} if no such value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("Null key");
        //offset for quadratic probing
        int h = 1;
        for (int i = hash(key); keys[i] != null; i = (i + h * h++) % capacity) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("Null key");
        if (!contains(key)) return;

        //offset for quadratic probing
        int h = 1;
        int i = hash(key);

        //keep probing quadratically
        //until key to be deleted is found
        while (!key.equals(keys[i])) {
            i = (i + h * h++) % capacity;
        }

        // delete key and associated value
        keys[i] = null;
        values[i] = null;

        //Rehash all the keys in same cluster
        //from index post index of deleted key
        i = (i + h * h) % capacity;
        while (keys[i] != null) {
            //Delete keys[i] & values[i] and reinsert them
            Key keyToRehash = keys[i];
            Value valueToRehash = values[i];
            values[i] = null;
            keys[i] = null;
            keyCount--;
            put(keyToRehash, valueToRehash);
            i = (i + h * h++) % capacity;
        }
        keyCount--;

        // halves size of array if it's 12.5% full or less
        if (keyCount > 0 && keyCount < capacity / 8) resize(capacity / 2);
    }

    /**
     * Returns all keys in this symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st},
     * use the foreach notation: {@code for (Key key : st.keys())}.
     *
     * @return all keys in this symbol table
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<Key>();
        for (int i = 0; i < capacity; i++)
            if (keys[i] != null) queue.add(keys[i]);
        return queue;
    }
}

