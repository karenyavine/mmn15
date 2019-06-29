package com.mmn15;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    static public String[] readChars(String filepath) {
        try {
            InputStream is = new FileInputStream(filepath);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            ArrayList<String> ls = new ArrayList<>();

            String currentLineReaded = buf.readLine();
            while (currentLineReaded != null) {
                ls.add(currentLineReaded);
                currentLineReaded = buf.readLine();
            }
            buf.close();
            String[] array = ls.toArray(new String[0]);
            return array;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found! Exception: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
        return null;

    }

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
