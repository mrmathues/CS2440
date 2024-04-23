package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public class Ocelot extends Feline 
{
    
    /**
     * 
     * @param name name
     */
    public Ocelot(String name) 
    {
        super(name);
    }

    /**
     * 
     */
    @Override
    public void eat() 
    {
        System.out.println("pick...");
        hungerLevel -= 3;
    }
}
