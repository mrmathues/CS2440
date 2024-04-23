package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public class Hippo extends Animal 
{

    /**
     * 
     * @param name name
     */
    public Hippo(String name) 
    {
        super(name);
    }

    /**
     * 
     */
    @Override
    public void eat() 
    {
        System.out.println("slurp...");
        hungerLevel--;
    }

    /**
     * 
     */
    @Override
    public void makeNoise() 
    {
        System.out.println("blub...");
    }
}
