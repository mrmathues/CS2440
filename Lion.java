package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public class Lion extends Feline 
{
    
    /**
     * 
     * @param name name
     */
    public Lion(String name) 
    {
        super(name);
    }

    /**
     * 
     */
    @Override
    public void eat() 
    {
        System.out.println("rip with teeth...");
        hungerLevel -= 2;
    }

    /**
     * 
     */
    @Override
    public void makeNoise() 
    {
        System.out.println("roar...");
    }
}
