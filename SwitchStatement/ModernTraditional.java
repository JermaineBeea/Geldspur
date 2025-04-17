package SwitchStatement;

public class ModernTraditional {

    public static void main(String[] args) {
        Switch switchStatement = new Switch();
        System.out.println(switchStatement.describeObject("Hello"));
        System.out.println(switchStatement.describeObject(42));
        System.out.println(switchStatement.describeObject(3.14));
        System.out.println(switchStatement.describeObject(null));
    }


    // Modern (Java 13+)
public String getDayMessage(Day day) {
    return switch (day) {
        case MONDAY -> "Start of work week";
        case FRIDAY -> {
            String message = "TGIF!";
            yield "End of work week - " + message;
        }
        case SATURDAY, SUNDAY -> "Weekend!";
        default -> "Midweek";
    };
}

// Traditional
public String getDayMessage2(Day day) {
    switch (day) {
        case MONDAY:
            return "Start of work week";
        case FRIDAY:
            String message = "TGIF!";
            return "End of work week - " + message;
        case SATURDAY:
        case SUNDAY:
            return "Weekend!";
        default:
            return "Midweek";
    }
}
}
