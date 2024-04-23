package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public class SiameseCat extends HouseCat 
{
    
    /**
     * 
     * @param name name
     */
    public SiameseCat(String name) 
    {
        super(name);
    }

    /**
     * 
     */
    @Override
    public void makeNoise() 
    {
        System.out.println("mrrooowwww...");
    }

    /**
     * 
     */
    @Override
    public void play() 
    {
        System.out.println("zoom zoom zoom...");
    }
}
