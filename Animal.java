package nature;

/**
 * @author Max Mathues
 * @version 1
 * Animal abstract class
 */
public abstract class Animal 
{

    protected String name;
    protected int hungerLevel = 0;

    /**
     * 
     * @param name one arg constructor
     */
    public Animal(String name) 
    {
        this.name = name;
    }

    
    /** 
     * @return int
     */
    public int getHungerLevel() 
    {
        return hungerLevel;
    }
    
    /** 
     * @return String 
     */
    public String getName() 
    {
        return name;
    }

    
    /** 
     * @param hungerLevel sets or changes hungerLevel
     */
    public void setHungerLevel(int hungerLevel) 
    {
        if (hungerLevel > 10) 
        {
            hungerLevel = 10;
        }
        else if (hungerLevel < 0) 
        {
            hungerLevel = 0;
        }

        this.hungerLevel = hungerLevel;
    }

    
    /** 
     * @param name sets or changes name
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**.
     * Sleep method
     */
    public void sleep() 
    {
        hungerLevel += 10;
        System.out.println("sleeping...");
    }

    /**.
     * Roam method
     */
    public void roam() 
    {
        hungerLevel++;
        System.out.println("moving around...");
    }

    /**.
     * abstract Eat method, will be changed later
     */
    public abstract void eat();

    /**.
     * abstract makeNoise method, will be changed later
     */
    public abstract void makeNoise();

}