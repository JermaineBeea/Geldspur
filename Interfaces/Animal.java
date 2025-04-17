
package Interfaces;

interface Animal {
    // Abstract methods (no implementation)
    void makeSound();
    void move();
    
    // Since Java 8, interfaces can have default methods
    default void sleep() {
        System.out.println("Zzzz...");
    }
    
    // Since Java 8, interfaces can have static methods
    static boolean isAnimal(Object obj) {
        return obj instanceof Animal;
    }
    
    // Since Java 9, interfaces can have private methods
    // (Used to share code between default methods)
}

// Class implementing the interface
class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof!");
    }
    
    @Override
    public void move() {
        System.out.println("Running on four legs");
    }
}

// Another implementation
class Bird implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Tweet!");
    }
    
    @Override
    public void move() {
        System.out.println("Flying");
    }
    
    // Override the default method
    @Override
    public void sleep() {
        System.out.println("Light sleeping with one eye open");
    }
}