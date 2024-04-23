package storage;

import java.util.Arrays;

/**
 * DoubleArraySeq
 */
public class DoubleArraySeq implements Cloneable{

    public static final int DEFAULT_CAPACITY = 10;
    private double[] data;
    private int manyItems;
    private int currentIndex;
 
    public DoubleArraySeq(int initialCapacity){
        this.data = new double[initialCapacity];
        this.manyItems = 0;
        this.currentIndex = 0;
    }

    public DoubleArraySeq(){
        this(DEFAULT_CAPACITY);
    }


    public void addAfter(double element){
	
        ensureCapacity(manyItems + 1);
                if (isCurrent())
                {
                    for (int i = manyItems; i > currentIndex; i--)
                    {
                        data[i] = data[i - 1];
                    }
                    data[currentIndex + 1] = element;
                    currentIndex++;
        
                } 
                else 
                {
                    data[manyItems] = element;
                    currentIndex = manyItems;
                }
        
                manyItems++;
    }

    public void addBefore(double element){
    ensureCapacity(manyItems + 1);
    if (isCurrent())
    {
        for (int i = manyItems; i > currentIndex; i--)
            {
                data[i] = data[i - 1];
            }
        data[currentIndex] = element;
    } 
    else 
    {
        for (int i = manyItems; i > 0; i--)
            {
                data[i] = data[i - 1];
            }
        data[0] = element;
    }
    manyItems++;
    }

    public void addAll(DoubleArraySeq addend){
        ensureCapacity(manyItems + addend.manyItems);        
        System.arraycopy(addend.data, 0, data, manyItems, addend.manyItems);
        manyItems += addend.manyItems;
    }

    public void trimToSize(){
        double[ ] trimmedArray;
        if (data.length != manyItems)
        {
            trimmedArray = new double[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }
    }

    public void ensureCapacity(int minimumCapacity)
    {
        if (data.length < minimumCapacity)
        {
            int newCapacity = Math.max(data.length * 2, minimumCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    public void start()
    {
        currentIndex = 0;
    }

    public void advance(){
        if(isCurrent()){
            currentIndex++;
        }
        else{
			throw new IllegalStateException
			("There is no current element");
        }
        
    }

    public double getCurrent(){
        if(isCurrent()){
      	    return data[currentIndex];
        }
		else{
			throw new IllegalStateException
			("There is no current element");
        }
    }
    
    public void removeCurrent()
    {
        if (!isCurrent())
            {
                throw new IllegalStateException("No current element");
            }
        for (int i = currentIndex; i < manyItems - 1; i++)
            {
                data[i] = data[i + 1];
            }
        manyItems--;
        if (manyItems == currentIndex)
        {
            currentIndex = manyItems;
        }
    }

    public boolean isCurrent(){
        return (currentIndex < manyItems);
    }

    public int getCapacity(){
        int cap = data.length;
        return cap;
    }

    public int size(){
        return this.manyItems;
    }

    public DoubleArraySeq clone()
    {
        DoubleArraySeq clone = new DoubleArraySeq(data.length);
        clone.manyItems = this.manyItems;
        clone.currentIndex = this.currentIndex;
        System.arraycopy(this.data, 0, clone.data, 0, manyItems);
        return clone;
    }

    public String toString()
    {
        String str = "<";
        for (int i = 0; i < manyItems; i++)
        {
            if (i == currentIndex)
            {
                str += "[" + data[i] + "]";
            } 
            else 
            {
                str += data[i];
            }
            if (i < manyItems - 1)
            {
                str += ", ";
            }
        }
        str += ">";
        return str;
    }

    public boolean equals(Object other)
    {
        if (this == other) 
        {
            return true;
        }
        if (other == null || getClass() != other.getClass()) 
        {
            return false;
        }

        DoubleArraySeq DA1 = (DoubleArraySeq) other;
        if (manyItems != DA1.manyItems) 
        {
            return false;
        }
        for (int i = 0; i < manyItems; i++)
        {
            if (data[i] != DA1.data[i]) 
            {
                return false;
            }
        }
        return currentIndex == DA1.currentIndex;
        
    }
    

    public static DoubleArraySeq concatenation(DoubleArraySeq s1,
        DoubleArraySeq s2)
    {
        DoubleArraySeq s3 = new DoubleArraySeq(s1.size() + s2.size());
        System.arraycopy(s1.data, 0, s3.data, 0, s1.size());
        System.arraycopy(s2.data, 0, s3.data, s1.size(), s2.size());
        s3.manyItems = s1.manyItems + s2.manyItems;
        s3.currentIndex = s3.size();
        return s3;
    }
    
}