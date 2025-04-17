package Enums;
class SeasonTest {
    public static void main(String[] args) {
        Season season = Season.SPRING;
        System.out.println("Season: " + season);
        System.out.println("Value: " + season.getValue());
        System.out.println("Is warm: " + season.isWarm());
    }
}

enum Season {
    WINTER(0), SPRING(1), SUMMER(2), FALL(3);
    
    private final int value;
    
    // Constructor must be private or package-private
    Season(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public boolean isWarm() {
        return this == SPRING || this == SUMMER;
    }
}