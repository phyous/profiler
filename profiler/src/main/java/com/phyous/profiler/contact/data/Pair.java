package com.phyous.profiler.contact.data;

/**
 * Created by pyoussef on 8/24/13.
 */
public class Pair {
    public String key;
    public String value;

    public Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String toString() {
        return "{" + key + ", " + value + "}";
    }
}
