package com.mmn15;

import java.util.Map;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;


public class Suggestions {
    static String getSuggestion(String word, Map<Integer, String> dictionary) {
//        Integer min = Integer.MAX_VALUE;
//        String s = "suggest";
        return  suggestLED(word,1, dictionary);
//        for (String suggestion : dictionary.values()) {
//            int score = getLevenshteinDistanceOfOne(word, suggestion);
//            if (score == 1) {
//                return  suggestion;
//            } else if (score < min) {
//                min = score;
//                s = suggestion;
//            }
//        }
//        return s;
    }

    /**
     * Calculates the Levenshtein Edit Distance (LED) between two words
     *
     * @param word1
     *            the first word
     * @param word2
     *            the second word
     * @return the LED between the two words
     */
    public static int led(String word1, String word2) {
        char[] wordArr1 = (' ' + word1).toCharArray();
        char[] wordArr2 = (' ' + word2).toCharArray();
        int[][] matrix = new int[wordArr1.length][wordArr2.length];
        IntStream.range(1, matrix.length).forEach(i -> matrix[i][0] = i);
        IntStream.range(1, matrix[0].length).forEach(j -> matrix[0][j] = j);
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                matrix[i][j] = matrix[i - 1][j - 1];
                if (wordArr1[i] != wordArr2[j]) {
                    matrix[i][j] = Stats.min(matrix[i][j], matrix[i - 1][j], matrix[i][j - 1]) + 1;
                }
            }
        }
        return matrix[word1.length()][word2.length()];
    }

    /* Recursive helper function for LED suggestions */
    private static void suggestLEDAux(String node, char[] letters, StringBuilder compWord,
                                      int[][] matrix, List<String> suggestions) {
        if (compWord.length() > matrix.length) {
            return;
        }
        int length = letters.length;
        int i = compWord.length() - 1;
        matrix[i][0] = i;
        int minVal = i;
        for (int j = 1; j < length; j++) {
            matrix[i][j] = matrix[i - 1][j - 1];
            if (compWord.charAt(i) != letters[j]) {
                matrix[i][j] = Math.min(matrix[i][j], matrix[i - 1][j], matrix[i][j - 1]) + 1;
            }
            if (matrix[i][j] < minVal) {
                minVal = matrix[i][j];
            }
        }
        if (matrix[compWord.length() - 1][length - 1] <= 1 && node.isWord()) {
            suggestions.add(compWord.toString().substring(1));
        }
        if (minVal <= 1) {
            for (Entry<Character, TrieNode> e : node.entrySet()) {
                compWord.append(e.getKey());
                suggestLEDAux(e.getValue(), letters, 1, compWord, matrix, suggestions);
                Strings.pop(compWord);
            }
        }
    }


    /**
     * @param word the word from which to generate suggestions
     * @return the first word fitting a levinshtein distance of 1
     */

    public String suggestLED(String word, Map<Integer, String> dictionary) {
        List<String> suggestions = new ArrayList<>();

        if (dictionary.containsValue(word)) {
            return word;
        }
        char[] letters = (" " + word).toCharArray();
        int[][] matrix = new int[letters.length + 1][letters.length];
        IntStream.range(1, letters.length).forEach(j -> matrix[0][j] = j);
        StringBuilder compWord = new StringBuilder(" ");
        root.entrySet().stream().forEach(e -> {
            compWord.append(e.getKey());
            suggestLEDAux(e.getValue(), letters, 1, compWord, matrix, suggestions);
            Strings.pop(compWord);
        });
        return suggestions[0];
    }


//    private static String getLevenshteinDistanceOfOne(String newWord, Map<Integer, String> dictionary ) {
//        if (dictionary.containsValue(newWord)){
//            return newWord;
//        }
//        String alphabet = 'abcdefghijklmnopqrstuvwxyz';
//
//        int substitution = getLevenshteinDistanceOfOne(x.charAt(i)==alphabet.charAt(j));
//        int insertion = getLevenshteinDistanceOfOne(x);
//        int deletion = getLevenshteinDistanceOfOne(x);
//        int transpose = getLevenshteinDistanceOfOne(x);
//
////        return min(substitution, insertion, deletion, transpose);
//    }
//
//    private static int costOfSubstitution(char a, char b) {
//        return a == b ? 0 : 1;
//    }
//
//    public static int min(int... numbers) {
//        return Arrays.stream(numbers)
//                .min().orElse(Integer.MAX_VALUE);
//    }
}
