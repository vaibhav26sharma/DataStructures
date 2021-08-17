package cache;

import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFUCache {
    //Contains the actual cache K and V
    HashMap<Integer, Integer> values;
    //Contains the count/frequency corresponding to every key
    HashMap<Integer, Integer> counts;
    //Contains key and a linkedHashSet in every key/bucket, which has linked elements in FIFO having the same
    //frequency as designated by the key
    HashMap<Integer, LinkedHashSet<Integer>> lists;
    //Maximum allowed capacity of the cache
    int cap;
    //Current minimum frequency of a key or a set of keys
    int min = -1;

    public LFUCache(int capacity) {
        this.cap = capacity;
        this.values = new HashMap<>();
        this.counts = new HashMap<>();
        this.lists = new HashMap<>();
        //Initialize list with count key "1"
        lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Key does not exist in the cache");
        }
        //Get the count of the key
        int count = counts.get(key);
        //Increase the counter
        counts.put(key, count + 1);
        //Remove element from old count bucket in lists map
        lists.get(count).remove(key);

        //Increase the min count, since earlier min key no more has elements in lists map
        if (count == min && lists.get(count).size() == 0) {
            min++;
        }
        //If the lists map does not have a key with new count for the key, create it
        if (!lists.containsKey(count)) {
            lists.put(count, new LinkedHashSet<>());
        }
        //Add key to new bucket(corresponding  linkedHashSet) as per updated count
        lists.get(count).add(key);
        return values.get(key);
    }

    public void set(int key, int value) {
        if (cap <= 0)
            throw new IllegalArgumentException("Provided capacity is not legal");
        //if map already has the key, update the value
        //and update all the maps for count.frequency
        if (values.containsKey(key)) {
            values.put(key, value);
            get(key);
            return;
        }

        //If cache is full, evict based on frequecy(FIFO if more than one with same min frequency)
        //and add the new element
        if (values.size() >= cap) {
            int evict = lists.get(min).iterator().next();//Gets LFU element based on FIFO
            lists.get(min).remove(evict);
            values.remove(evict);
            counts.remove(evict);
        }
        //if key doesn't exist already, reset min to 1
        values.put(key, value);
        counts.put(key, 1);
        min = 1;
        lists.get(1).add(key);
    }
}
