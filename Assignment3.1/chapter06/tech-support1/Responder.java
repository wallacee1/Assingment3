import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.List;
/**
 * The responder class represents a response generator object.
 * It is used to generate an automatic response to an input string.
 * 
 * @author     Michael Kölling and David J. Barnes- Edited by ERic Wallace
 * @version    0.1 (2016.02.29)
 */
public class Responder
{
    private Random randomGenerator;
    private HashMap<String, String> responseMap; 
    private ArrayList<String> defaultResponses;
    private List<String> fillers = new ArrayList<String>();
    private String word;
    private String keywords;
    /**
     * Construct a Responder - nothing to do
     */
    public Responder()
    {
        responseMap = new HashMap<>();
        defaultResponses = new ArrayList<>();
        fillResponseMap();
        fillDefaultResponses();
        randomGenerator = new Random();
        generateFillers();
    }

    /**
     * Generate a response.
     * @return   A string that should be displayed as the response to the
     * input from the user.
     */
    public String generateResponse(HashSet<String> words) 
    {
    ArrayList<String> keywords = new ArrayList<String>();
    for (String word : words) {
        if(!fillers.contains(word)){
            keywords.add(word);
        }
    }
    String word = keywords.get(randomGenerator.nextInt(keywords.size()));
    String response = responseMap.get(word);
    if(response !=null) {
        return response;
    }
    return pickDefaultResponse(keywords);
    }

    /**
     * Creates a HashMap of responses for output based on the keyword.
     * @
     */
    private void fillResponseMap()
    {
        responseMap.put("crash", 
                        "Well, it never crashes on our system. It must have something\n" +
                        "to do with your system. Tell me more about your configuration.");
        responseMap.put("crashes", 
                        "Well, it never crashes on our system. It must have something\n" +
                        "to do with your system. Tell me more about your configuration.");
        responseMap.put("slow", 
                        "I think this has to do with your hardware. Upgrading your processor\n" +
                        "should solve all performance problems. Have you got a problem with\n" +
                        "our software?");
        responseMap.put("performance", 
                        "Performance was quite adequate in all our tests. Are you running\n" +
                        "any other processes in the background?");
        responseMap.put("bug", 
                        "Well, you know, all software has some bugs. But our software engineers\n" +
                        "are working very hard to fix them. Can you describe the problem a bit\n" +
                        "further?");
        responseMap.put("buggy", 
                        "Well, you know, all software has some bugs. But our software engineers\n" +
                        "are working very hard to fix them. Can you describe the problem a bit\n" +
                        "further?");
        responseMap.put("windows", 
                        "This is a known bug to do with the Windows operating system. Please\n" +
                        "report it to Microsoft. There is nothing we can do about this.");
        responseMap.put("mac", 
                        "This is a known bug to do with the Mac operating system. Please\n" +
                        "report it to Apple. There is nothing we can do about this.");
        responseMap.put("expensive", 
                        "The cost of our product is quite competitive. Have you looked around\n" +
                        "and really compared our features?");
        responseMap.put("installation", 
                        "The installation is really quite straight forward. We have tons of\n" +
                        "wizards that do all the work for you. Have you read the installation\n" +
                        "instructions?");
        responseMap.put("memory", 
                        "If you read the system requirements carefully, you will see that the\n" +
                        "specified memory requirements are 1.5 giga byte. You really should\n" +
                        "upgrade your memory. Anything else you want to know?");
        responseMap.put("linux", 
                        "We take Linux support very seriously. But there are some problems.\n" +
                        "Most have to do with incompatible glibc versions. Can you be a bit\n" +
                        "more precise?");
        responseMap.put("bluej", 
                        "Ahhh, BlueJ, yes. We tried to buy out those guys long ago, but\n" +
                        "they simply won't sell... Stubborn people they are. Nothing we can\n" +
                        "do about it, I'm afraid.");
    }

    /**
     * Build up a list of default responses from which we can pick one
     * if we don't know what else to say.
     */
    private void fillDefaultResponses()
    {
        defaultResponses.add("That sounds odd. Could you describe that problem in more detail?");
        defaultResponses.add("No other customer has ever complained about this before. \n" +
                             "What is your system configuration?");
        defaultResponses.add("That sounds interesting. Tell me more...");
        defaultResponses.add("I need a bit more information on that.");
        defaultResponses.add("Have you checked that you do not have a dll conflict?");
        defaultResponses.add("That is explained in the manual. Have you read the manual?");
        defaultResponses.add("Your description is a bit wishy-washy. Have you got an expert\n" +
                             "there with you who could describe this more precisely?");
        defaultResponses.add("That's not a bug, it's a feature!");
        defaultResponses.add("Could you elaborate on that?");
    }

    /**
     * Randomly select and return one of the default responses.
     * @return     A random default response
     */
    private String pickDefaultResponse(ArrayList<String> words)
    {
        if(words.isEmpty()){
            return pickDefaultResponse();
        }
        
        String word = words.get(randomGenerator.nextInt(words.size()));
        int i = randomGenerator.nextInt(3);
        
        String response = "";
        switch (i){
            case 0:
                response += "Why do you think it is " + word + "?";
                break;
            case 1:
                response += "How" + word + " is it?";
                break;
            case 2:
                response += "Who found that it is " + word + "?";
                break;
        }
        return response;
    }
    private void generateFillers()
    {
        fillers.add("and");
        fillers.add("the");
    }
}
