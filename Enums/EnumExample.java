package Enums;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

// Usage example
public class EnumExample {
    public static void main(String[] args) {
        Day today = Day.FRIDAY;
        
        // Switch with enum
        switch (today) {
            case MONDAY:
                System.out.println("Start of work week");
                break;
            case FRIDAY:
                System.out.println("End of work week");
                break;
            case SATURDAY:
            case SUNDAY:
                System.out.println("Weekend!");
                break;
            default:
                System.out.println("Midweek");
                break;
        }

        // Enum methods
        System.out.println("Enum name: " + today.name());
        System.out.println("Enum ordinal: " + today.ordinal());
    }
} 
    

