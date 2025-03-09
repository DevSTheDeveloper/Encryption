import java.util.Random;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordStore {
    private HashMap<Character, ArrayList<String>> store; //ArrayList is used so we do not override existing mappings but add to them
    private HashMap<String, Character> wordMap = new HashMap<>(); //Mapping from word to character

    public WordStore() {
        store = new HashMap<>();
    }

    public WordStore(String filename) throws IOException {
        store = new HashMap<>();
        BufferedReader lineReader = null;
        try {
            lineReader = new BufferedReader(new FileReader(filename));
            String current;
            String div = ",";
            while ((current = lineReader.readLine()) != null) {
                String[] myString = current.split(div);
                if (myString.length == 2) { //ensure line in file only has 2 characters (Word and Letter) 
                    String word = myString[0]; //assign the word variable
                    char key = myString[1].toLowerCase().charAt(0); //selected letter
                    add(key, word);  
                }
            }
        } finally {
            if (lineReader != null) {
                lineReader.close();
            }
        }
    }
    

    public void add(char key, String item) {
        key = Character.toLowerCase(key);   //ensures case insensitivity - automatically store as lowercase to prevent errors   
        store.putIfAbsent(key, new ArrayList<>());
        store.get(key).add(item.toLowerCase());  
        wordMap.put(item.toLowerCase(), key); // Map the word to the key
    }

    public String getRandomItem(char key) {
        Random rand = new Random();
        key = Character.toLowerCase(key); 
        ArrayList<String> items = store.get(key);

        if (items == null || items.isEmpty()) {
            return null;
        }
        return items.get(rand.nextInt(items.size()));
    }

    public static void main(String[] args) {
        WordStore store = new WordStore();  
        store.add('A', "Chair");
        store.add('a', "Table");

        System.out.println(store.getRandomItem('A'));
    }

    //THIS SNIPPET WAS ADDED FOR QUESTION 4: DECRYPTION - THIS IS TO RETRIEVE THE ORIGINAL CHARACTER 
    
    public String getOriginalChar(String word) {
      Character letter = wordMap.get(word.toLowerCase());
      
      if (letter != null) {
          return letter.toString();  
      } else {
          return "Not Found";  
      }
  }
}