package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public class Wolf extends Canine 
{
    
    /**
     * 
     * @param name name
     */
    public Wolf(String name) 
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
        System.out.println("growl...");
    }
}
