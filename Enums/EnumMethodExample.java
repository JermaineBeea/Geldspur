package Enums;

enum Planet {
    MERCURY(3.303e+23, 2.4397e6),
    VENUS(4.869e+24, 6.0518e6),
    EARTH(5.976e+24, 6.37814e6),
    MARS(6.421e+23, 3.3972e6),
    JUPITER(1.9e+27, 7.1492e7),
    SATURN(5.688e+26, 6.0268e7),
    URANUS(8.686e+25, 2.5559e7),
    NEPTUNE(1.024e+26, 2.4746e7);

    private final double mass;   // in kilograms
    private final double radius; // in meters
    
    // Constructor
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    
    // Method to get mass
    public double getMass() {
        return mass;
    }
    
    // Method to get radius
    public double getRadius() {
        return radius;
    }
    
    // Method that performs a calculation
    public double surfaceGravity() {
        double G = 6.67300E-11; // universal gravitational constant
        return G * mass / (radius * radius);
    }
    
    // Method that uses the calculated value
    public double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
    
    // Static method that works with the enum
    public static Planet findLargest() {
        Planet largest = MERCURY;
        for (Planet p : Planet.values()) {
            if (p.getMass() > largest.getMass()) {
                largest = p;
            }
        }
        return largest;
    }
}

public class EnumMethodExample {
    public static void main(String[] args) {
        // Using instance methods
        double earthWeight = 70.0; // kg
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        
        for (Planet p : Planet.values()) {
            System.out.printf("Your weight on %s is %.2f N%n", 
                             p, p.surfaceWeight(mass));
        }
        
        // Using the static method
        Planet largest = Planet.findLargest();
        System.out.println("\nThe largest planet is: " + largest);
        System.out.println("Its mass is: " + largest.getMass() + " kg");
        
        // Using built-in enum methods
        System.out.println("\nEnum constants in order:");
        for (Planet p : Planet.values()) {
            System.out.println(p.name() + " (ordinal: " + p.ordinal() + ")");
        }
        
        // Converting a string to enum constant
        String planetName = "MARS";
        Planet mars = Planet.valueOf(planetName);
        System.out.println("\nConverted " + planetName + " to: " + mars);
    }
}