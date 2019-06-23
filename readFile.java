
import java.io.*;
import java.util.Map;
import java.util.HashMap;
/* 

TODO:
 [x] Read files
    [x] Dictionary must contain at least 50 words
 [x] Place dictionary in hashmap
    [] Explain which hashmap type chosen, and explain why this one specifically
    [] Analyze the time complexity of the hashmap algorithm
 [] Place text file in red-black tree
 [] Remove from tree any word that is in the dictionary
 [] Print remaining words from tree (these are incorrectly spelled words)
*/

public class readFile {
    public static void main(String[] args) throws Exception {
        String text = readFile(args[0]);
        String dictionary = readFile(args[1]);
        hashDict(dictionary);

        System.out.println("you got 100%");
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

    private static void hashDict(String dictionary) {
        Map<String, Object> map = new HashMap<String, Object>();
        // FIXME: do this the hard way, otherwise we get 0
        // Currently this is O(n)
        String words[] = dictionary.split(" ");
        for (String word : words) {
            map.put(word, null);
        }

        System.out.println(map.containsKey("kilo"));
    }

}
