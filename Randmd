To reshuffle the array and get a truly random order, you can use `Collections.shuffle()`. Here's how to modify the `listRandGenerator2` method:

```java
public static List<Integer> listRandGenerator2(int numRandNumbers) {
    Set<Integer> setRandInt = new HashSet<>();

    while (setRandInt.size() < numRandNumbers) {
        Integer randint = rand.nextInt(20);
        setRandInt.add(randint);
    }
    
    List<Integer> randomList = new ArrayList<>(setRandInt);
    Collections.shuffle(randomList);
    
    return randomList;
}
```

This will require adding these imports:
```java
import java.util.ArrayList;
import java.util.Collections;
```

Alternatively, if you want to implement it without reshuffling (getting truly random elements in a random order from the start), you could replace the HashSet approach with an array-based implementation:

```java
public static List<Integer> listRandGenerator2(int numRandNumbers) {
    List<Integer> result = new ArrayList<>();
    List<Integer> availableNumbers = new ArrayList<>();
    
    // Add all possible numbers to the available pool
    for (int i = 0; i < 20; i++) {
        availableNumbers.add(i);
    }
    
    // Take random numbers from the pool until we have enough
    for (int i = 0; i < Math.min(numRandNumbers, 20); i++) {
        int randomIndex = rand.nextInt(availableNumbers.size());
        result.add(availableNumbers.remove(randomIndex));
    }
    
    return result;
}
```

This implementation:
1. Creates a pool of all possible numbers (0-19)
2. Randomly selects and removes numbers from that pool
3. Adds the selected numbers to the result list

This approach naturally gives you random numbers in a random order, without needing to shuffle afterward.