import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/**
 * Keep a record of how many times each word was
 * entered by users.
 * 
 * @author     Michael Kölling and David J. Barnes
 * @version    1.0 (2016.02.29)
 */
public class WordCounter
{
    // Associate each word with a count.
    private HashMap<String, Integer> counts;
    private Responder responder;

    /**
     * Create a WordCounter
     */
    public WordCounter()
    {
        counts = new HashMap<>();
    }
    
    public void printUsageCount()
    {
        Set<String> keywords = responder.getResponses().keySet();
        for(String word : counts.keySet()){
            if(!keywords.contains(word))
                System.out.println(word + ", counts: " + counts.get(word));
        }
    }
    
    /**
     * Update the usage count of all words in input.
     * @param input A set of words entered by the user.
     */
    public void addWords(HashSet<String> input)
    {
        for(String word : input) {
            int counter = counts.getOrDefault(word, 0);
            counts.put(word, counter + 1);
        }
    }
}
