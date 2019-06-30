package com.mmn15;

import java.io.*;

public class FileReader {
    static public MyHashTable readToMap(String filepath) {
        try {
            InputStream is = new FileInputStream(filepath);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            //PerfectHashMap.Builder<Integer, String> mapBuilder = PerfectHashMap.newBuilder();

            String currentLineReaded = buf.readLine();

            MyHashTable o = new MyHashTable();

            while (currentLineReaded != null) {
                //mapBuilder.add(hash, currentLineReaded);
                if (currentLineReaded == "man") {
                    System.out.println(currentLineReaded);
                }
                o.insert(currentLineReaded);
                currentLineReaded = buf.readLine();
            }
            buf.close();
            //Map a = mapBuilder.build();
            return o;
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
