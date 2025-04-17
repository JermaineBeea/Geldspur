class MainRun {

    public static void main(String[] args) {
        Position pos1 = new Position();
        Position pos2 = new Position(1, 2, Direction.EAST);

        System.out.println("Initial pos1: " + pos1);
        pos1.movePosition(Move.LEFT);
        System.out.println("After LEFT: " + pos1);
        pos1.movePosition(Move.FORWARD);
        System.out.println("After FORWARD: " + pos1);
        
        System.out.println("\nInitial pos2: " + pos2);
        pos2.movePosition(Move.FORWARD);
        System.out.println("After FORWARD: " + pos2);
    }
}

enum Direction {
    NORTH("Facing North", 0, 1),
    EAST("Facing East", 1, 0),
    SOUTH("Facing South", 0, -1),
    WEST("Facing West", -1, 0);
    
    public final String directionMessage;
    public final int xVectorChange;
    public final int yVectorChange;
    
    Direction(String directionMessage, int xVectorChange, int yVectorChange) {
        this.directionMessage = directionMessage;
        this.xVectorChange = xVectorChange;
        this.yVectorChange = yVectorChange;
    }
    
    // Get the next direction when turning left
    public Direction turnLeft() {
        switch(this) {
            case NORTH: return WEST;
            case WEST: return SOUTH;
            case SOUTH: return EAST;
            case EAST: return NORTH;
            default: return this; // Should never happen
        }
    }
    
    // Get the next direction when turning right
    public Direction turnRight() {
        switch(this) {
            case NORTH: return EAST;
            case EAST: return SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            default: return this; // Should never happen
        }
    }
}

enum Move {
    LEFT, RIGHT, FORWARD, BACKWARD;
}

class Position {
    private int xPos;
    private int yPos;
    private Direction direction;
    
    Position(int xPos, int yPos, Direction direction) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.direction = direction;
    }
    
    Position() {
        this(0, 0, Direction.NORTH);
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public int[] getPosition() {
        return new int[]{xPos, yPos};
    }
    
    public void movePosition(Move movement) {
        switch(movement) {
            case LEFT:
                direction = direction.turnLeft();
                break;
            case RIGHT:
                direction = direction.turnRight();
                break;
            case FORWARD:
                xPos += direction.xVectorChange;
                yPos += direction.yVectorChange;
                break;
            case BACKWARD:
                xPos -= direction.xVectorChange;
                yPos -= direction.yVectorChange;
                break;
        }
    }
    
    @Override
    public String toString() {
        return "(" + xPos + ", " + yPos + ") " + direction.directionMessage;
    }
}