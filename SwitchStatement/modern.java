package SwitchStatement;

enum Day {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}


class ModernSwitch {

    public static void main(String[] args) {
        ModernSwitch modernSwitch = new ModernSwitch();
        System.out.println(modernSwitch.getDayType(Day.MONDAY));
        System.out.println(modernSwitch.getDayMessage(Day.FRIDAY));
        System.out.println(modernSwitch.describeObject("Hello"));
        System.out.println(modernSwitch.describeObject(42));
        System.out.println(modernSwitch.describeObject(3.14));
    }

public String getDayType(Day day) {
    return switch (day) {
        case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";
        case SATURDAY, SUNDAY -> "Weekend";
    };
}

// Switch expression with yield (Java 13+)
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

// Pattern matching with switch (Java 17+)
public String describeObject(Object obj) {
    return switch (obj) {
        case String s -> "String of length " + s.length();
        case Integer i -> "Integer with value " + i;
        case Long l -> "Long with value " + l;
        case Double d when d > 0 -> "Positive double: " + d;
        case Double d -> "Non-positive double: " + d;
        case null -> "It's null";
        default -> "Something else: " + obj.getClass().getName();
    };
}

}