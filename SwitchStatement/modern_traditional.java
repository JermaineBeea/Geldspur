package SwitchStatement;

class modern_traditional {

    public static void main(String[] args) {
        Switch switchStatement = new Switch();
        System.out.println(switchStatement.describeObject("Hello"));
        System.out.println(switchStatement.describeObject(42));
        System.out.println(switchStatement.describeObject(3.14));
        System.out.println(switchStatement.describeObject(null));
    }
}


class Switch{ // Pattern matching with switch (Java 17+)


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

// Traditional (pre-Java 17)
public String describeObjectTraditional(Object obj) {
    if (obj == null) {
        return "It's null";
    } else if (obj instanceof String) {
        String s = (String) obj;
        return "String of length " + s.length();
    } else if (obj instanceof Integer) {
        Integer i = (Integer) obj;
        return "Integer with value " + i;
    } else if (obj instanceof Long) {
        Long l = (Long) obj;
        return "Long with value " + l;
    } else if (obj instanceof Double) {
        Double d = (Double) obj;
        if (d > 0) {
            return "Positive double: " + d;
        } else {
            return "Non-positive double: " + d;
        }
    } else {
        return "Something else: " + obj.getClass().getName();
    }
} 

}



    

