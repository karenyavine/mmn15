package com.mmn15;

import java.util.Set;
import java.util.HashSet;

public class mmn15 {
    /**
     * The main function in the project
     * @param args - Input from command. Expects two args, first is text file, second is dictionary.
     * @out A list of misspelled words from the text file.
     */
    public static void main(String[] args) throws Exception {
        try {
            RBTree textTree = FileReader.readToRedBlackTree(args[0]);
            MyHashTable dictionary = FileReader.readToMap(args[1]);

            RBNode a = textTree.search("row");
            deleteNodesFromTree(textTree,dictionary);
            RBNode b = textTree.search("row");

            textTree.printInOrder(textTree.getRoot(), word -> Suggestions.getSuggestion(word, dictionary));

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Missing input files: " + ex.getMessage());
        }

    }

    private static void deleteNodesFromTree(RBTree tree, MyHashTable dictionary){
        Set<String> existingInDictionaryWords = new HashSet<>();

        //Recursive call to nodesToDelete, only when done will existingInDictionaryWords be full and then and only then we can delete words
        nodesToDelete(tree.getRoot(), dictionary, existingInDictionaryWords);

        // this section has a time complexity of O(n), n being # of words in existingInDictionaryWords to be deleted.
        // searching and deletion in Red Black tree has a time complexity of O(log n), thus not affecting the time complexity.
        for (String word : existingInDictionaryWords) {
            RBNode node = tree.search(word);
            tree.RBDelete(node);
        }
    }

    /**
     * nodesToDelete, Given a tree node, dictionary and a Set(list) of nodes, recursively checks if node value exists in
     * dictionary.
     * If it does not exist in dictionary, it is meant to remain in the tree.
     * If it does exist in dictionary, then the word is added to the todel param. This is because deleting a node in the tree changes the whole tree,
     * and this way we make sure we iterate ofer the whole tree once and collect all correctly spelled words and delete them once
     * (deletion happens in main function.)
     * @param rootNode - The current node from which we are descending from.
     * @param dictionary - The dictionary map.
     * @param todel - a list of nodes containing correctly spelled words, pending to deletion.
     */
    private static void nodesToDelete(RBNode rootNode, MyHashTable dictionary, Set<String> todel) {
        String key = rootNode.getKey();

        boolean contains = dictionary.contains(key);
        if (contains) {
            todel.add(rootNode.getKey());
        }

        if (rootNode.getRightSon() != null && !rootNode.getRightSon().getKey().equals("")) {
            nodesToDelete(rootNode.getRightSon(), dictionary, todel);
        }

        if (rootNode.getLeftSon() != null && !rootNode.getLeftSon().getKey().equals("")) {
            nodesToDelete(rootNode.getLeftSon(), dictionary, todel);
        }
    }
}
