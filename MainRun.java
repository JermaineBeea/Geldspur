import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Map;

class MainRun {

    public static void main(String[] args) 
    {
        Map<Integer, Integer[]> dictRandNumbers = new HashMap<>();

        List<Integer> intList = RandFunctions.listRandGenerator1(10);

        for (Integer num : intList) 
        {
            Integer[] arrayNum = RandFunctions.listRandGenerator2(num).toArray(new Integer[0]);
            dictRandNumbers.put(num, arrayNum);
        }

        for (Map.Entry<Integer, Integer[]> entry : dictRandNumbers.entrySet()) 
        {
            System.out.println("Key: " + entry.getKey() + " Value: " + Arrays.toString(entry.getValue()));
        }
    }

    static class RandFunctions 
    {

        public static Random rand = new Random();

        public static List<Integer> listRandGenerator1(int numRandNumbers) 
        {
            Integer[] intArray = new Integer[numRandNumbers];
            
            for (int index = 0; index < numRandNumbers; index++) 
            {
                intArray[index] = rand.nextInt(20);
            }
            return Arrays.asList(intArray);
        }


        public static List<Integer> listRandGenerator2(int numRandNumbers) 
        {
            Set<Integer> setRandInt = new HashSet<>();

            while (setRandInt.size() < numRandNumbers) 
            {
                Integer randint = rand.nextInt(20);
                setRandInt.add(randint);
            }
            return Arrays.asList(setRandInt.toArray(new Integer[0]));
        }
    }
}