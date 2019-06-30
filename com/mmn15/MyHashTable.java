/**
 * @author Karen Yavine (308428804)
 * @author Ohad Shultz (201319563)
 * @duedate 30/07/2019
 */

package com.mmn15;


/**
 * MyHashTable is a hash table implementation using a linked list, as a collision resolution technique.
 * This implementation gave is the lowest complexity and easiest implementation, giving it a high ROI.
 * @timeComplexity of all functions is O(1)
ֿ */

public class MyHashTable {

    private int tableSize = 100000;

    Item[] values = new Item[tableSize];

    /**
     * insert inserts into the hash table.
     * @timeComplexity O(1)
     * @param value a given string
    ֿ */

    void insert(String value) {
        int hash = hash(value);
        Item newItem = new Item(value);

        if (values[hash] == null || values[hash].value.isEmpty()) {
            values[hash] = newItem;
        } else {
            Item item = values[hash];
            if (item.value.equals(value)) return;

            while (item.next != null) {
                item = item.next;
                if (item.value.equals(value)) return;
            }
            item.next = newItem;
        }
    }

    /**
     * contains checks if the value of the given string exists in the array.
     * @timeComplexity O(1)
     * @param value a given string
     * @return true if the hash table already contains the value, false if not..
    ֿ */

    boolean contains(String value) {
        int hash = hash(value);

        Item item = values[hash];
        while (item != null) {
            if (item.value.equals(value)) return true;
            item = item.next;
        }
        return false;
    }

    /**
     * hash is a function that calculates the hash of a given string. The hash will represent the index of the string
     * in the array.
     * @timeComplexity O(n)
     * @param value a given string.
     * @return a hash of the value.
    ֿ */

    int hash(String value) {
        long hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = hash*7 + (int)value.charAt(i);
        }
        if (hash < 0) hash *= -1; // since has is array index it must be positive.
        hash = hash % tableSize;
        return (int)hash;
    }

    /**
     * Item is a single chain in the linked list.
    ֿ */
    private class Item
    {
        String value;
        Item next;

        Item(String value) { this.value = value; }
    }
}
