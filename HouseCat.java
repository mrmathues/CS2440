package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public class HouseCat extends Feline implements Pet 
{
    
    /**
     * 
     * @param name name
     */
    public HouseCat(String name) 
    {
        super(name);
    }

    /**
     * 
     */
    @Override
    public void eat() 
    {
        System.out.println("crunch crunch...");
        hungerLevel -= 3;
    }

    /**
     * 
     */
    @Override
    public void beFriendly() 
    {
        System.out.println("purr...");
    }

    /**
     * 
     */
    @Override
    public void play() 
    {
        System.out.println("frolic...");
    }
}
