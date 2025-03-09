
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Encrypt {
    //encapsulation used below to prevent cross-referencing of the txt files including its equiv letter wihtout decryption
    private WordStore nouns;
    private WordStore adjectives;
    private VerbStore verbs;
    private WordStore adverbs;

    public Encrypt() throws IOException {
        nouns = new WordStore("nouns.txt");
        adjectives = new WordStore("adjectives.txt");
        verbs = new VerbStore("verbs.txt");
        adverbs = new WordStore("adverbs.txt");
    }

    List<String> encrypt(String input) {
        String[] letters = input.split(""); //splits the input string into a list we can analyse one by one
        List<String> output = new ArrayList<>();
        boolean shouldUseVerb = false;

        for (int i = 0; i < letters.length; i++) {
            char charAt = letters[i].charAt(0); //current character, first letter by default (index = 0)

            String word = null;

            //FOLLOW RULE: Verb? (Adverb Verb)* Adjective Noun (VERB IS OPTIONAL)
            if (i == 0) {
                //Verb (optional) - can be skipped
                word = verbs.getRandomItem(charAt);
            } else if (i == 1) {
                //Adjective
                word = adjectives.getRandomItem(charAt);
            } else if (i == letters.length - 1) {
                //Noun (Final Letter)
                word = nouns.getRandomItem(charAt);
            } else {
                //Adverbs and Verbs (Alternating)
                if (shouldUseVerb) {
                    word = verbs.getRandomItem(charAt);
                    shouldUseVerb = false;
                } else {
                    word = adverbs.getRandomItem(charAt);
                    shouldUseVerb = true;
                }
            }

            if (word != null) {
                output.add(word); //Add word at the end of the list
            }
        }

        return output;  //encrypted words list
    }

    public static void main(String[] args) {
        try {
            Encrypt encrypt = new Encrypt();
            for (String s : encrypt.encrypt(args[0])) {
                System.out.println(s);
            }
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}