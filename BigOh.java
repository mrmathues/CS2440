package algorithms;
import java.util.*;
import java.lang.Math;



public class BigOh
{

    private static final double MILLISECONDS_PER_SECOND = 1000;
    private static final int NUM_TRIALS = 5;
    private Random rand;

    public BigOh(){
        this.rand = new Random();
    }

    public BigOh(Random rand){
        this.rand = rand;
    }

    public int runAlgorithm(int choice, int numElements){
        switch(choice) {
            case 1:
                int sum1 = MysteryAlgorithms.alg1(numElements, rand);
                return sum1;
            case 2:
                int sum2 = MysteryAlgorithms.alg2(numElements, rand);
                return sum2;
            case 3:
                int sum3 = MysteryAlgorithms.alg3(numElements, rand);
                return sum3;
            case 4:
                int sum4 = MysteryAlgorithms.alg4(numElements, rand);
                return sum4;
            case 5:
                int sum5 = MysteryAlgorithms.alg5(numElements, rand);
                return sum5;
            case 6:
                int sum6 = MysteryAlgorithms.alg6(numElements, rand);
                return sum6;
            default:
                return -1;
            }
    }

    public double bigOhFunc(int choice, double n){
        switch (choice) {
            case 1:
                return n;
            case 2:
                return Math.pow(n, 3);
            case 3:
                return Math.pow(n, 2);
            case 4:
                return Math.pow(n, 2);
            case 5:
                return Math.pow(n, 5);

            case 6:
                return Math.pow(n, 4);
            default:
                return -1;
        }
    }

    public double timeAlgorithm(int choice, int n){
        System.gc();
        double time1 = System.currentTimeMillis();
        runAlgorithm(choice, n);
        double time2 = System.currentTimeMillis();
        double totalTime = (time2 - time1)/1000;
        return totalTime;
    }

    public double robustTimeAlgorithm(int choice, int n){
        double lowestTime = 999999;
        for(int i=0; i < NUM_TRIALS; i++){
            double t = timeAlgorithm(choice,n);
            if(t < lowestTime)
            lowestTime = t;
            }
    	return lowestTime;
    }

    public double estimateTiming(int choice, int n1, double t1, int n2){
        double f1 = bigOhFunc(choice, n1);
        double f2 = bigOhFunc(choice, n2);
        return (t1 * f2) / f1;
    }
    
    public double percentError(double correct, double estimate){
        double error = ((estimate-correct)/correct);
        return error;
    }

    public double computePercentError(int choice, int n1, int n2){
        double t1 = timeAlgorithm(choice, n1);
        double t2 = timeAlgorithm(choice, n2);
        double estim_t2 = estimateTiming(choice, n1, t1, n2);
        return percentError(t2, estim_t2);
    }
}