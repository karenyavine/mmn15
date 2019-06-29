package com.mmn15;

import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class mmn15 {
    public static void main(String[] args) throws Exception {
        try {
            RBTree textTree = FileReader.readToRedBlackTree(args[0]);
            Map<Integer, String> dictionary = FileReader.readChars(args[1]);

            Set<RBNode> existingInDictionaryWords = new HashSet<>();
            ///*FIX:*/ deleteExistingWords(textTree.getRoot(), textTree, dictionary, incorrectWords);
            /*FIX: hee? */
            ToDelete(textTree.getRoot(), dictionary, existingInDictionaryWords);

            for (RBNode word : existingInDictionaryWords) {
                textTree.RBDelete(word);
            }

            System.out.println("****************************************************** Start Remaining ***********************************************************");
            textTree.printInOrder(textTree.getRoot(), word -> getSuggestion(word, dictionary));
            System.out.println("****************************************************** End ***********************************************************");
            System.out.println("you got 100%");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Missing input files: " + ex.getMessage());
        }

    }

    private static String getSuggestion(String word, Map<Integer, String> dictionary) {
        Integer min = Integer.MAX_VALUE;
        String s = "";

        for (String suggestion : dictionary.values()) {
            int score = calculateLevenshteinDistance(word, suggestion);
            if (score == 1) {
                return  suggestion;
            } else if (score < min) {
                min = score;
                s = suggestion;
            }
        }
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

    public static int costOfSubstitution(char a, char b) {
        return a == b ? 0 : 1;
    }

    public static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

    private static void deleteExistingWords(RBNode textNode, RBTree textTree, Set<String> dictionary) {
        // for each node in tree, see if not exists in dictionary, if not, delete node
        if (dictionary.contains(textNode.getKey())) {
            textTree.RBDelete(textNode);
        }

        if (textNode.getRightSon() != null && !textNode.getRightSon().getKey().equals("")) {
            deleteExistingWords(textNode.getRightSon(), textTree, dictionary);
        }
        if (textNode.getLeftSon() != null && !textNode.getLeftSon().getKey().equals("")) {
            deleteExistingWords(textNode.getLeftSon(), textTree,dictionary);
        }
    }

    private static void ToDelete(RBNode rootNode, Map<Integer, String> dictionary, Set<RBNode> todel) {
        String key = rootNode.getKey();
        String leftKey = rootNode.getLeftSon().getKey();
        String rightKet = rootNode.getRightSon().getKey();

        Boolean contains = dictionary.containsValue(key);
        if (contains) {
            todel.add(rootNode);
        }

        if (rootNode.getRightSon() != null && !rootNode.getRightSon().getKey().equals("")) {
            ToDelete(rootNode.getRightSon(), dictionary, todel);
        }

        if (rootNode.getLeftSon() != null && !rootNode.getLeftSon().getKey().equals("")) {
            ToDelete(rootNode.getLeftSon(), dictionary, todel);
        }
    }
}
