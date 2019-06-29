package com.mmn15;

import java.io.*;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class mmn15 {
    public static void main(String[] args) throws Exception {
        try {
            RBTree textTree = FileReader.readToRedBlackTree(args[0]);
            Map<Integer, String> dictionary = FileReader.readChars(args[1]);


            Set<String> incorrectWords = new HashSet<>();
            /*FIX:*/ deleteExistingWords(textTree.getRoot(), textTree, dictionary, incorrectWords);

            for (String word : incorrectWords) {
                System.out.println(word);
            }

            System.out.println("you got 100%");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Missing input files: " + ex.getMessage());
        }

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
    }}
