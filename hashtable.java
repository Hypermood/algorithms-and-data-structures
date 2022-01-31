package weblab;

import java.util.*;

/**
 * Entry objects are used to represent "Key-Value" pairs.
 * An entry can be created by using new Entry(String key, Integer Value)
 * The .equals() method of Entry will compare the keys only.
 */
class Entry {

    public final String key;

    public final Integer value;

    public Entry(String s, Integer v) {
        key = s;
        value = v;
    }

    public boolean equals(String s) {
        return s == null && key == null || key.equals(s);
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o != null && getClass() == o.getClass() && this.equals(((Entry) o).key);
    }

    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }
}

abstract class HashTable {

    protected LinkedList<Entry>[] myTable;

    /**
     * Constructs a new HashTable.
     *
     * @param capacity
     *     to be used as capacity of the table.
     * @throws IllegalArgumentException
     *     if the input capacity is invalid.
     */


    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {

        if(capacity < 1){
            throw new IllegalArgumentException();
        }

        this.myTable = new LinkedList[capacity];
    }

    /**
     * Add a key value pair to the HashTable.
     *
     * @param key
     *     to identify the value.
     * @param value
     *     that is identified by the key.
     */
    public void put(String key, Integer value) {
        if(myTable[hash(key)] == null){
            myTable[hash(key)] = new LinkedList<>();
            myTable[hash(key)].add(new Entry(key,value));
            return;
        }
        if(this.containsKey(key)){
            myTable[hash(key)].remove(myTable[hash(key)].stream().filter(e -> {

                if (key == null) {
                    return e.key == null;
                } else {
                    return e.key.equals(key);
                }

            }).findFirst().get());
            myTable[hash(key)].add(new Entry(key,value));
            return;
        }
        myTable[hash(key)].add(new Entry(key,value));

    }

    /**
     * @param key
     *     to look for in the HashTable.
     * @return true iff the key is in the HashTable.
     */
    public boolean containsKey(String key) {
        int index = hash(key);

        if(myTable[index] == null){
            return false;
        }

        Optional<Entry> first = myTable[index].stream().filter(e -> {

            if (key == null) {
                return e.key == null;
            } else {
                return e.key.equals(key);
            }

        }).findFirst();

        return first.isPresent();

    }

    /**
     * Get a value from the HashTable.
     *
     * @param key
     *     that identifies the value.
     * @return the value associated with the key or `null` if the key is not in the HashTable.
     */
    public Integer get(String key) {
        if(myTable[hash(key)] == null){
            return null;
        }


        Optional<Entry> first = myTable[hash(key)].stream().filter(e -> {

            if (key == null) {
                return e.key == null;
            } else {
                return e.key.equals(key);
            }

        }).findFirst();
        if(first.isPresent()){
            return first.get().getValue();
        }
        return null;
    }

    /**
     * Gets the capacity of this HashTable.
     *
     * @return the capacity of the HashTable.
     */
    public int getCapacity() {
        return myTable.length;
    }

    /**
     * Hashes a string/key.
     *
     * @param item
     *     to hash.
     * @return the hashcode of the string, modulo the capacity of the HashTable.
     */
    public abstract int hash(String item);
}

class HashTableImpl extends HashTable {

    public int numCalls;

    public HashTableImpl(int size) {
        super(size);
    }

    @Override
    public int hash(String item) {
        numCalls++;
        return item == null ? 0 : Math.abs(item.hashCode()) % getCapacity();
    }

    public LinkedList<Entry>[] getTable() {
        return myTable;
    }
}
