/**
 * @author Karen Yavine (308428804)
 * @author Ohad Shultz (201319563)
 * @duedate 30/07/2019
 */

package com.mmn15;

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

            RBTreeHelpers.deleteNodesFromTree(textTree, dictionary); // changes tree in place

            textTree.printInOrder(textTree.getRoot(), word -> Suggestions.getSuggestion(word, dictionary));

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Missing input files: " + ex.getMessage());
        }

    }

}
