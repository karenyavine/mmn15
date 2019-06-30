package com.mmn15;


public class Suggestions {
    static String getSuggestion(String word, MyHashTable dictionary) {
        return GetClosestWordInDict(word, dictionary);
    }

    private static String GetClosestWordInDict(String word, MyHashTable dictionary) {
        int j = 0;
        boolean found = false;
        String orig = word;
        for (int i = 0; i < word.length(); i++) {
            for (j = 0; j < 26; j++) {
                if (dictionary.contains(word)) {
                    found = true;
                    break;
                }

                StringBuilder sb = new StringBuilder(word);
                sb.setCharAt(i, (char)(sb.charAt(i) + 1));
                word = sb.toString();
            }
        }
        if (found) return word;
        return orig;
    }
}
