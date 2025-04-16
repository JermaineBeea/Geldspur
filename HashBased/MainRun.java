package HashBased;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Arrays;

class MainRun{
    
    public static Random randInstance = new Random();

    public static void main(String[] args)
    {
        Map<Integer, Integer[]> randMap = new HashMap<>();

        Integer[] mapKeys = MainRun.generateRand(true, 10, 10, 0).toArray(new Integer[0]);

        System.out.println("Map Keys: " + Arrays.toString(mapKeys) + "\n\n");

        for(Integer quantity : mapKeys)
        {
            randMap.put(quantity, MainRun.generateRand(true, quantity, 27, 11).toArray(new Integer[0]));
        }

        for (Map.Entry<Integer, Integer[]> entry : randMap.entrySet())
        {
            System.out.println("Key: " + entry.getKey() + "\nValue: " + Arrays.toString(entry.getValue()) + "\n\n");
        }
    }

    /**
     * This method generates a list of random integers.
     * @param UniqueNumbers boolean indicating if the numbers should be unique  
     * @param quanity the number of random integers to generate
     * @param max the maximum value for the random integers
     * @param min the minimum value for the random integers
     * @return a list of random integers
     * @throws IllegalArgumentException if the quantity exceeds the range of unique numbers
     */
    public static List<Integer> generateRand(boolean UniqueNumbers, int quanity, int max, int min)

    {
       return UniqueNumbers ? MainRun.Unique(quanity, max, min) : MainRun.notUnique(quanity, max, min);
    }

    private static  List<Integer> Unique(int quanity, int  max, int  min)
    {
        if(quanity > max - min + 1)
        {
            throw new IllegalArgumentException("Quantity: " + quanity + ", Can not exceed " + (max - min + 1));
        }

        Set<Integer> randSet = new HashSet<>();

        while(randSet.size() < quanity)
        {
            randSet.add(randInstance.nextInt(max - min + 1) + min);
        }
        
        List<Integer> randList = new ArrayList<>(randSet);
        Collections.shuffle(randList);

        return randList;
    }

    private static List<Integer> notUnique(int quanity, int max, int min)
    {
        List<Integer> randList = new ArrayList<>(quanity);

        for(int n = 0; n < quanity; n++)
        {
            randList.add(randInstance.nextInt(max - min + 1) + min);
        }
        return randList;
    }
}