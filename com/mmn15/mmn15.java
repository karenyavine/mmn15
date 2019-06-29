package com.mmn15;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class mmn15 {
    public static void main(String[] args) throws Exception {
        try {
            RBTree textTree = FileReader.readToRedBlackTree(args[0]);
            String[] dictionary = FileReader.readChars(args[1]);

//            String[] incorrectWords = deleteExistingWords(map, text);
//            for (String word : incorrectWords) {
//                if (!map.containsKey(word)) {
//                    System.out.println(word);
//                }
//            }

            System.out.println("you got 100%");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Missing input files: " + ex.getMessage());
        }

    }

    private static String[] deleteExistingWords(RBTree textTree, String[] dictionary) {
        // for each node in tree, see if not exists in dictionary, if not, delete node
        return dictionary;
    }
}
