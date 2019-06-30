/**
 * @author Karen Yavine (308428804)
 * @author Ohad Shultz (201319563)
 * @duedate 30/07/2019
 */

package com.mmn15;

public class Suggestions {

    /**
     * getSuggestion find a word that had a Levinshtein distance of 1 (max) from the original word.
     * This is done with 4 functions:
     * ValidateUsingSwap, ValidateUsingReplacement, ValidateUsingDeletion and ValidateUsingInsertion.
     * The suggestions are ordered by time complexity
     * @timeComplexity O(n^2)
     * @param word - The word from the text file.
     * @param dictionary - The dictionary file.
     * @return A guess at which word should have been in the text, if no guess found then returns original word.
     */
    static String getSuggestion(String word, MyHashTable dictionary) {
        String guess = ValidateUsingSwap(word, dictionary);
        if (guess != null) return guess;
//
        guess = ValidateUsingReplacement(word, dictionary);
        if (guess != null) return guess;

        guess = ValidateUsingDeletion(word, dictionary);
        if (guess != null) return guess;

        guess = ValidateUsingInsertion(word, dictionary);
        if (guess != null) return guess;

        return word;
    }

    /**
     * ValidateUsingSwap iterates over word, and calls swap() in order to swap between two letters.
     * We iterate over the word up to word.length()-1 to avoid ArrayOutOfIndexExceptions
     * @timeComplexity O(n)
     * @param word - The word from the text file.
     * @param dictionary - The dictionary file.
     * @return A guess at fixing the typo in word, null if no word found
     */
    private static String ValidateUsingSwap(String word, MyHashTable dictionary) {
        for (int i = 0; i < word.length() - 1; i++) {
            String wordGuess = swap(word, i);
            if (dictionary.contains(wordGuess)) return wordGuess;
        }
        return null;
    }

    /**
     * swap() swaps between a letter and the letter after it.
     * @timeComplexity O(1)
     * @param word - The word from the text file.
     * @param index - The index from which we perform the swap.
     * @return A guess at fixing the typo in word, null if no word found
     */
    private static String swap(String word, int index) {
        StringBuilder sb = new StringBuilder(word);
        char temp = sb.charAt(index);
        sb.setCharAt(index, sb.charAt(index+1));
        sb.setCharAt(index+1, temp);
        return sb.toString();
    }

    /**
     * ValidateUsingReplacement iterates over word, and attempts to replace an existing char with a different char.
     * @timeComplexity O(n) => [O(26*n) = O(n)]
     * @param word - The word from the text file.
     * @param dictionary - The dictionary file.
     * @return A guess at fixing the typo in word, null if no word found
     */
    private static String ValidateUsingReplacement(String word, MyHashTable dictionary) {
        for (int i = 0; i < word.length(); i++) {
            char orig = word.charAt(i);
            char next = getNextLetter(word, i);
            while (next != orig) {
                String newstring = replaceAtIndex(word, i, next);
                if (dictionary.contains(newstring)) return newstring;
                next = getNextLetter(newstring, i);
            }
        }
        return null;
    }

    /**
     * replaceAtIndex replaces an existing char with a different char.
     * @timeComplexity O(1)
     * @param word - The word from the text file.
     * @param index - The index from which we perform the replacement.
     * @param c - The char that replaces the original char.
     * @return a new guess
     */
    private static String replaceAtIndex(String word, int index, char c) {
        StringBuilder sb = new StringBuilder(word);
        sb.setCharAt(index, c);
        return sb.toString();
    }

    /**
     * getNextLetter gets the next letter from the alphabet, given a char.
     * @timeComplexity O(1)
     * @param word - The word from the text file.
     * @param index - The index from which we get the char.
     * @return the next char
     */
    private static char getNextLetter(String word, int index) {
        char c = word.charAt(index);
        if (c == 122) c = 96;
        return ++c;
    }

    /**
     * ValidateUsingDeletion iterates over word, and calls deleteAtIndex() in order to delete a char at an index.
     * @timeComplexity O(n^2)
     * @param word - The word from the text file.
     * @param dictionary - The dictionary file.
     * @return A guess at fixing the typo in word, null if no word found
     */
    private static String ValidateUsingDeletion(String word, MyHashTable dictionary) {
        for (int i = 0; i < word.length(); i++) {
            String newString = deleteAtIndex(word, i);
            if (dictionary.contains(newString)) return newString;
        }
        return null;
    }

    /**
     * deleteAtIndex deletes an existing char from the word.
     * @timeComplexity O(n)
     * @param word - The word from the text file.
     * @param index - The index from which we perform the deletion.
     * @return a new guess
     */
    private static String deleteAtIndex(String word, int index) {
        StringBuilder sb = new StringBuilder(word);
        sb.deleteCharAt(index);
        return sb.toString();
    }

    /**
     * ValidateUsingInsertion iterates over word, and calls insertAtIndex() in order to insert a char at an index.
     * @timeComplexity O(n^2) => [O(26*n) = O(n)]
     * @param word - The word from the text file.
     * @param dictionary - The dictionary file.
     * @return A guess at fixing the typo in word, null if no word found
     */
    private static String ValidateUsingInsertion(String word, MyHashTable dictionary) {
        for (int i = 0; i < word.length(); i++) {
            char orig = word.charAt(i);
            String newstring = insertAtIndex(word, i, orig);
            if (dictionary.contains(newstring)) return newstring;
            char next = getNextLetter(word, i);
            while (next != orig) {
                newstring = insertAtIndex(word, i, next);
                if (dictionary.contains(newstring)) return newstring;
                next = getNextLetter(newstring, i);
            }
        }
        return null;
    }

    /**
     * insertAtIndex Inserts a char into the string, given an index.
     * @timeComplexity O(n)
     * @param word - The word from the text file.
     * @param index - The index in which we insert the letter.
     * @param letter - The letter we need to insert.
     * @return a new guess
     */
    private static String insertAtIndex(String word, int index, char letter) {
        StringBuilder sb = new StringBuilder(word);
        sb.insert(index, letter);
        return sb.toString();

    }

}

