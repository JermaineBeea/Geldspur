package Enums;

enum TrafficLight {
    RED, YELLOW, GREEN;
    
    // Method that returns a boolean
    public boolean shouldStop() {
        return this == RED || this == YELLOW;
    }
    
    // Method that returns a string message
    public String getMessage() {
        switch (this) {
            case RED:
                return "Stop!";
            case YELLOW:
                return "Caution!";
            case GREEN:
                return "Go!";
            default:
                return "";
        }
    }
    
    // Method that returns the next traffic light
    public TrafficLight next() {
        switch (this) {
            case RED:
                return GREEN;
            case GREEN:
                return YELLOW;
            case YELLOW:
                return RED;
            default:
                return RED;
        }
    }
}

class TrafficLightExample {
    public static void main(String[] args) {
        TrafficLight light = TrafficLight.RED;
        
        // Using the methods
        System.out.println(light + ": " + light.getMessage());
        System.out.println("Should I stop? " + light.shouldStop());
        
        // Moving to the next light
        light = light.next();
        System.out.println("Light changed to: " + light);
        System.out.println(light + ": " + light.getMessage());
        
        // Moving to the next light again
        light = light.next();
        System.out.println("Light changed to: " + light);
        System.out.println(light + ": " + light.getMessage());
    }
}