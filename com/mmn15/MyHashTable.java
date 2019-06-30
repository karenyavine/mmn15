package com.mmn15;

import java.util.ArrayList;

public class MyHashTable {

    private int tableSize = 710000000;

    Item[] values = new Item[tableSize];

    void insert(String value) {
        int hash = hash(value);
        Item newItem = new Item(value);

        if (values[hash] == null) {
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

    boolean contains(String value) {
        int hash = hash(value);

        Item item = values[hash];
        while (item != null) {
            if (item.value.equals(value)) return true;
            item = item.next;
        }
        return false;
    }

    int hash(String value) {
        long hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash = hash*2 + (int)value.charAt(i);
        }
        hash = hash % tableSize;
        return (int)hash;
    }

    private class Item
    {
        String value;
        Item next;

        Item(String value) { this.value = value; }
    }
}
