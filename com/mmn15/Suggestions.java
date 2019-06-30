package com.mmn15;


public class Suggestions {
    static String getSuggestion(String word, MyHashTable dictionary) {
        return GetClosestWordInDict(word, dictionary);
    }

    private static String GetClosestWordInDict(String word, MyHashTable dictionary) {
        long startTime = System.nanoTime();
        String string = ValidateUsingTranspose(word, dictionary);
        if (string != null) return string;
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.

        startTime = System.nanoTime();
        string = ValidateUsingReplacement(word, dictionary);
        if (string != null) return string;
        endTime = System.nanoTime();
        duration = (endTime - startTime);  //divide by 1000000 to get milliseconds.

        return word;
    }

    private static String ValidateUsingTranspose(String string, MyHashTable dictionary) {
        for (int i = 0; i < string.length() - 1; i++) {
            String newString = transpose(string, i);
            if (dictionary.contains(newString)) return newString;
        }
        return null;
    }

    private static String transpose(String string, int index) {
        StringBuilder sb = new StringBuilder(string);
        char temp = sb.charAt(index);
        sb.setCharAt(index, sb.charAt(index+1));
        sb.setCharAt(index+1, temp);
        return sb.toString();
    }

    private static String ValidateUsingReplacement(String string, MyHashTable dictionary) {
        for (int i = 0; i < string.length(); i++) {
            char orig = string.charAt(i);
            char next = getNextLetter(string, i);
            while (next != orig) {
                string = replaceAtIndex(string, i, next);
                if (dictionary.contains(string)) return string;
                next = getNextLetter(string, i);
            }
        }
        return null;
    }

    private static String replaceAtIndex(String string, int index, char c) {
        StringBuilder sb = new StringBuilder(string);
        sb.setCharAt(index, c);
        return sb.toString();
    }

    private static char getNextLetter(String string, int index) {
        char c = string.charAt(index);
        if (c == 122) c = 96;
        return ++c;
    }
}

