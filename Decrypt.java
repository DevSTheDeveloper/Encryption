import java.io.IOException;
import java.util.List;

public class Decrypt {
    //encapsulation used below to prevent cross-referencing of the txt files including its equiv letter wihtout decryption
    private WordStore nouns;
    private WordStore adjectives;
    private VerbStore verbs;
    private WordStore adverbs;


    public Decrypt() throws IOException {
        nouns = new WordStore("nouns.txt");
        adjectives = new WordStore("adjectives.txt");
        verbs = new VerbStore("verbs.txt");
        adverbs = new WordStore("adverbs.txt");
    }

    public String decrypt(List<String> input) {
        StringBuilder output = new StringBuilder();
        boolean shouldUseVerb = false;
        for (int i = 0; i < input.size(); i++) {
            int index = input.size() - i - 1;
            if (i == 0) {
                output.insert(0, nouns.getOriginalChar(input.get(index)));
            }
            else if (i == 1) {
                output.insert(0, adjectives.getOriginalChar(input.get(index)));
            }
            else if (i == input.size() - 1) {
                output.insert(0, verbs.getOriginalChar(input.get(index)));
            }
            else if (shouldUseVerb) {
                output.insert(0, verbs.getOriginalChar(input.get(index)));
                shouldUseVerb = false;
            }
            else {
                output.insert(0, adverbs.getOriginalChar(input.get(index)));
                shouldUseVerb = true;
            }
        }
        return output.toString();
    }

    public static void main(String[] args) {
        try {
            Decrypt decrypt = new Decrypt();
            System.out.println(decrypt.decrypt(List.of(args)));
        }
        catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
