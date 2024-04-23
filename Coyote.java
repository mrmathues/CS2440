package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public class Coyote extends Canine 
{

    /**
     * 
     * @param name name
     */
    public Coyote(String name) 
    {
        super(name);
    }

    /**
     * 
     */
    @Override
    public void eat() 
    {
        System.out.println("gnaws...");
        hungerLevel -= 2;
    }

    /**
     * 
     */
    @Override
    public void makeNoise() 
    {
        System.out.println("howl...");
    }
    
}
