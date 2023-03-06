import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class RandomTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RandomTester
{
    Random randomNum;
    
    public RandomTester()
    {
        randomNum = new Random();
    }
    
    public void printOne()
    {
        System.out.println(randomNum.nextInt());
    }
    
    public void printMultiple(int howMany)
    {
        for(int i = 0; i < howMany; i++) System.out.println(randomNum.nextInt());
    }
    
    public int throwDie()
    {
        return 1 + randomNum.nextInt(6);
    }
    
    public String getResponse()
    {
        ArrayList<String> responses = new ArrayList<>();
        responses.add("yes");
        responses.add("no");
        responses.add("maybe");
        responses.add("perhaps");
        responses.add("no chance");
        return responses.get(randomNum.nextInt(responses.size()));
    }
    
    public int randomRangedNum(int max)
    {
        return 1 + randomNum.nextInt(max);
    }
    
    public int randomInRange(int min, int max)
    {
        return min + randomNum.nextInt(max);
    }
}

