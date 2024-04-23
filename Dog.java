package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public class Dog extends Canine implements Pet 
{
    
    /**
     * 
     * @param name name
     */
    public Dog(String name) 
    {
        super(name);
    }

    /**
     * 
     */
    @Override
    public void eat() 
    {
        System.out.println("slop...");
        hungerLevel -= 3;
    }

    /**
     * 
     */
    @Override
    public void makeNoise() 
    {
        System.out.println("bark...");
    }

    /**
     * 
     */
    public void beFriendly() 
    {
        System.out.println("nuzzles...");
    }

    /**
     * 
     */
    public void play() 
    {
        System.out.println("runs...");
    }
}
