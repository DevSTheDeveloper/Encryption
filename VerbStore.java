import java.io.IOException;

public class VerbStore extends WordStore {

    public VerbStore() {
        super(); //super as it extends parent class WordStore
    }

    public VerbStore(String filename) throws IOException {
        super(filename); //load words from file here
    }

    public void add(Character key, String word) {
        if (word.endsWith("e")) {
            word = word.substring(0, word.length() - 1); //removes the end e letter on the verb ie. Dance ---> Danc 
        }
        word += "ing"; //adds 'ing' to make present cont. - Ie. Danc --> Dancing 
        super.add(key, word);
    }

}
