package com.mmn15;

import java.util.HashSet;
import java.util.Set;

public class RBTreeHelpers {

    /**
     * deleteNodesFromTree, gets a list of words to delete from the tree and calls nodesToDelete to perform deletion from the tree
     * @param tree - The tree containing all of the words from the text file
     * @param dictionary - The dictionary map.
     */
    static void deleteNodesFromTree(RBTree tree, MyHashTable dictionary){
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
     * and this way we make sure we iterate over the whole tree once and collect all correctly spelled words and delete them once
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
