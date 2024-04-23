package nature;

import java.util.ArrayList;

/**
 * @author Max Mathues
 * @version 1
 * 
 */
public class Habitat 
{
    
    private String name;
    private double latitude;
    private double longitude;

    private ArrayList<Animal> animals = new ArrayList<Animal>();

    /**
     * 
     * @param name name
     * @param lat lat
     * @param lon long
     */
    public Habitat(String name, double lat, double lon) 
    {
        this.name = name;
        this.latitude = lat;
        this.longitude = lon;
    }

    /** 
     * @return double
     */
    public double getLatitude() 
    {
        return latitude;
    }
    
    /** 
     * @return double
     */
    public double getLongitude() 
    {
        return longitude;
    }
    
    /** 
     * @return String
     */
    public String getName() 
    {
        return name;
    }
    
    /** 
     * @param lat lat
     */
    public void setLatitude(double lat) 
    {
        this.latitude = lat;
    }
    
    /** 
     * @param lon long
     */
    public void setLongitude(double lon) 
    {
        this.longitude = lon;
    }
    
    /** 
     * @param name name
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /** 
     * @return int
     */
    public int getNumOfAnimals() 
    {
        return animals.size();
    }

    /** 
     * @param animal animal
     */
    public void addAnimal(Animal animal) 
    {
        animals.add(animal);
    }

    /**
     * 
     */
    public void testAnimals() 
    {
        System.out.println(name);
        System.out.println(latitude);
        System.out.println(longitude);
        System.out.println(animals.size());
    }

}
