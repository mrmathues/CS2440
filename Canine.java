package nature;

/**
 * @author Max Mathues
 * @version 1
 */
public abstract class Canine extends Animal 
{

    /**
     * 
     * @param name name
     */
    public Canine(String name) 
    {
        super(name);

    }
    
    /**
     * 
     */
    public void roam() 
    {
        System.out.println("like canines roam in packs...");
        hungerLevel++;
    }

}
