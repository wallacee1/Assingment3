
/**
 * Write a description of class MethodTester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MethodTester
{
    // instance variables - replace the example below with your own

    /**
     * Constructor for objects of class MethodTester
     */
    public MethodTester()
    {
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public static void staticMethod()
    {
        System.out.println("Static method");
    }
    
    public void instanceMethod()
    {
        System.out.println("Instance Method");
        staticMethod();
    }
    
    public static void anotherStaticMethod()
    {
        System.out.println("Another static method");
        staticMethod();
    }
}
