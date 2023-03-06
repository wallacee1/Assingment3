import java.util.Random;
import java.util.Scanner;
/**
 * Write a description of class DiceRoller here.
 *
 * @author Eric Wallace
 * @version 1
 */
public class DiceRoller
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt for input
        System.out.print("Enter die type (4, 6, 8, 10, 12, 20, 100): ");
        int dieType = scanner.nextInt();
        
        System.out.print("Enter number of dice (1-10): ");
        int numDice = scanner.nextInt();
        
        System.out.print("Enter taget number (5-30): ");
        int targetNum = scanner.nextInt();
        
        // Validation
        if(!isValidInput(dieType, numDice, targetNum)) {
            System.out.println("Invalid input!");
            return;
        }
    
        // Roll the dice
        Random random = new Random();
        int numOnes = 0;
        int total = 0;
        for (int i = 0; i < numDice; i++) {
            int result = random.nextInt(dieType) + 1;
            total += result;
            if (result == 1) {
                numOnes++;
            }else if (result == dieType) {
                total += rollOpenEndedDie(dieType, random);
            }
        }
    
        // Check for bust
        if ((double) numOnes / numDice > 0.5) {
            System.out.println("Bust! More than 50% of the dice are ones.");
            return;
        }
    
    // Display result
        if (total >= targetNum) {
            System.out.println("Success! Total roll: " + total);
        } else {
            System.out.println("Failure. Total roll: " + total);
        }
    }

    private static boolean isValidInput(int dieType, int numDice, int targetNum)
    {
        if(dieType != 4 && dieType != 6 && dieType != 8 && dieType != 10 
            && dieType != 12 && dieType != 20 && dieType != 100) {
                return false;
        }
        if (numDice < 1 || numDice > 10) {
            return false;
        }
        if (targetNum < 5 || targetNum > 30) {
            return false;
        }
        return true;
    }

    private static int rollOpenEndedDie(int dieType, Random random) {
        int result = random.nextInt(dieType) +1;
        if (result == dieType) {
            return dieType + rollOpenEndedDie(dieType, random);
        } else {
            return result;
        }
    }
}
