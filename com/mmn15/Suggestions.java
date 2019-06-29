package com.mmn15;

import java.util.Arrays;
import java.util.Map;

public class Suggestions {
    static String getSuggestion(String word, Map<Integer, String> dictionary) {
        Integer min = Integer.MAX_VALUE;
        String s = "suggest";

//        for (String suggestion : dictionary.values()) {
//            int score = calculateLevenshteinDistance(word, suggestion);
//            if (score == 1) {
//                return  suggestion;
//            } else if (score < min) {
//                min = score;
//                s = suggestion;
//            }
//        }
        return s;
    }

    static int calculateLevenshteinDistance(String x, String y) {
        if (x.isEmpty()) {
            return y.length();
        }

        if (y.isEmpty()) {
            return x.length();
        }

        int substitution = calculateLevenshteinDistance(x.substring(1), y.substring(1))
                + costOfSubstitution(x.charAt(0), y.charAt(0));
        int insertion = calculateLevenshteinDistance(x, y.substring(1)) + 1;
        int deletion = calculateLevenshteinDistance(x.substring(1), y) + 1;

        return min(substitution, insertion, deletion);
    }

    private static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }
}
