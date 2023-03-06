
/**
 * Write a description of class Person here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Person
{
    // instance variables - replace the example below with your own
    private String lastName;
    private String firstName;
    

    /**
     * Constructor for objects of class Person
     */
    public Person(String firstName, String lastName)
    {
        // initialise instance variables
        this.firstName = firstName;
        this.lastName= lastName;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public String getLastName()
    {
        // put your code here
        return lastName;
    }
    
    public String getFirstName()
    {
        // put your code here
        return firstName;
    }
}
