/**
 * @author Karen Yavine (308428804)
 * @author Ohad Shultz (201319563)
 * @duedate 30/07/2019
 */

package com.mmn15;

import java.io.*;

public class FileReader {
    /**
     * readToMap, Reads from file, line by line, and inserts full line into hashmap.
     * This happens in O(n) based on reading n lines from the file. Inserting to the hashmap occurs in o(1) so does not
     * affect run time.
     * @param filepath - The dictionary file.
     * @return A map containing the dictionary.
     */
    static public MyHashTable readToMap(String filepath) {
        try {
            InputStream is = new FileInputStream(filepath);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            MyHashTable hashTable = new MyHashTable();

            String currentLineReaded = buf.readLine();
            while (currentLineReaded != null) {
                hashTable.insert(currentLineReaded);
                currentLineReaded = buf.readLine();
            }
            buf.close();
            return hashTable;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found! Exception: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
        return null;
    }

    /**
     * readToRedBlackTree, Reads from file, char by char, and when it encounters a space, inserts full word into RB Tree.
     * This happens in O(n) based on reading n chars from the file. Inserting to the RB tree occurs in o(1) so does not
     * affect run time.
     * @param filepath - The text file. May contain misspelled words.
     * @return A RB Tree containing words from the text file.
     */
    static public RBTree readToRedBlackTree(String filepath) {
        RBTree tree = new RBTree();
        try {
            InputStream is = new FileInputStream(filepath);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            StringBuilder sb = new StringBuilder();

            int currentByteReaded = buf.read();
            while (currentByteReaded != -1) {
                char currentByteReadedAsChar = (char)currentByteReaded;
                if (currentByteReadedAsChar == ' ') {
                    RBNode node = new RBNode(sb.toString());
                    tree.RBInsert(node);
                    sb.setLength(0);
                } else {
                    sb.append(currentByteReadedAsChar);
                }
                currentByteReaded = buf.read();
            }
            RBNode node = new RBNode(sb.toString());
            tree.RBInsert(node);

            buf.close();
            return tree;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found! Exception: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
        return tree;
    }
}
