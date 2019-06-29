package com.mmn15;

import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class mmn15 {
    public static void main(String[] args) throws Exception {
        try {
            String text = readFile(args[0]);
            String dictionary = readFile(args[1]);

            Map map = hashDict(dictionary);
            System.out.println(map.containsKey("kilo"));

            String[] incorrectWords = getIncorrectWords(map, text);
            for (String word : incorrectWords) {
                if (!map.containsKey(word)) {
                    System.out.println(word);
                }
            }

            System.out.println("you got 100%");

        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Missing input files: " + ex.getMessage());
        }

    }

    private static String readFile(String arg) {
        try {
            InputStream is = new FileInputStream(arg);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));

            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line);
                line = buf.readLine();
            }
            buf.close();
            String fileAsString = sb.toString();
            return fileAsString;

        } catch (FileNotFoundException ex) {
            System.out.println("File not found! Exception: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("IOException: " + ex.getMessage());
        }
        return null;
    }

    private static Map hashDict(String dictionary) {
        Map<String, Object> map = new HashMap<String, Object>();
        // FIXME: do this the hard way, otherwise we get 0
        // Currently this is O(n)
        String words[] = dictionary.split(" ");
        for (String word : words) {
            map.put(word.toLowerCase(), null);
        }

        return map;
    }

    private static String[] getIncorrectWords(Map map, String text) {
        String[] textBreakdown = text.split(" ");
        // new String[] incorrectWords;
        return textBreakdown;
    }
}
