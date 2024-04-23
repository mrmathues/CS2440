package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public abstract class Feline extends Animal 
{

    /**
     * 
     * @param name name
     */
    public Feline(String name) 
    {
        super(name);

    }

    /**
     * 
     */
    public void roam() 
    {
        System.out.println("felines like to roam alone...");
        hungerLevel++;
    }

    /**
     * 
     */
    public void sleep() 
    {
        System.out.println("taking a cat nap...");
        hungerLevel += 10;
    }

    /**
     * 
     */
    @Override
    public void makeNoise() 
    {
        System.out.println("meow...");
    }
    
}
