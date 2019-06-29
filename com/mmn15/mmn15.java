package com.mmn15;

import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class mmn15 {
    public static void main(String[] args) throws Exception {
        try {
            RBTree textTree = FileReader.readToRedBlackTree(args[0]);
            Map<Integer, String> dictionary = FileReader.readToMap(args[1]);

            Set<RBNode> existingInDictionaryWords = new HashSet<>();
            /*FIX: hee? */
            nodesToDelete(textTree.getRoot(), dictionary, existingInDictionaryWords);

            for (RBNode word : existingInDictionaryWords) {
                textTree.RBDelete(word);
            }

            System.out.println("****************************************************** Start Remaining ***********************************************************");
            textTree.printInOrder(textTree.getRoot(), word -> Suggestions.getSuggestion(word, dictionary));
            System.out.println("****************************************************** End ***********************************************************");
            System.out.println("you got 100%");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Missing input files: " + ex.getMessage());
        }

    }

    private static void nodesToDelete(RBNode rootNode, Map<Integer, String> dictionary, Set<RBNode> todel) {
        String key = rootNode.getKey();

        boolean contains = dictionary.containsValue(key);
        if (contains) {
            todel.add(rootNode);
        }

        if (rootNode.getRightSon() != null && !rootNode.getRightSon().getKey().equals("")) {
            nodesToDelete(rootNode.getRightSon(), dictionary, todel);
        }

        if (rootNode.getLeftSon() != null && !rootNode.getLeftSon().getKey().equals("")) {
            nodesToDelete(rootNode.getLeftSon(), dictionary, todel);
        }
    }
}
