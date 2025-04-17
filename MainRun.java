enum Absolute {
    Ypositive(0, 1), 
    Ynegative(0, -1), 
    Xnegative(-1, 0), 
    Xpositive(1, 0);
    
    public final int xUnitChange;
    public final int yUnitChange;
    
    Absolute(int xChange, int yChange) {
        this.xUnitChange = xChange;
        this.yUnitChange = yChange;
    }
}

enum Direction {
    NORTH(Absolute.Ypositive, Absolute.Ynegative, Absolute.Xnegative, Absolute.Xpositive),
    SOUTH(Absolute.Ynegative, Absolute.Ypositive, Absolute.Xpositive, Absolute.Xnegative),
    WEST(Absolute.Xnegative, Absolute.Xpositive, Absolute.Ynegative, Absolute.Ypositive),
    EAST(Absolute.Xpositive, Absolute.Xnegative, Absolute.Ypositive, Absolute.Ynegative);
    
    public final Absolute forward;
    public final Absolute backward;
    public final Absolute left;
    public final Absolute right;
    
    Direction(Absolute forward, Absolute backward, Absolute left, Absolute right) {
        this.forward = forward;
        this.backward = backward;
        this.left = left;
        this.right = right;
    }
    
    // Helper method to get the appropriate Absolute direction
    public Absolute getAbsolute(Movement movement) {
        switch(movement) {
            case FORWARD: return forward;
            case BACKWARD: return backward;
            case LEFT: return left;
            case RIGHT: return right;
            default: throw new IllegalArgumentException("Unknown movement: " + movement);
        }
    }
}

enum Movement {
    FORWARD, BACKWARD, LEFT, RIGHT
}

class Position {
    private Direction direction;
    private int xPos;
    private int yPos;
    
    public Position(int xPos, int yPos, Direction direction) {
        this.direction = direction;
        this.xPos = xPos;
        this.yPos = yPos;
    }
    
    public int getXPos() {
        return xPos;
    }
    
    public int getYPos() {
        return yPos;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public void setDirection(Direction newDirection) {
        this.direction = newDirection;
    }
    
    public void move(Movement movement, int motionScale) {
        Absolute absoluteDirection = direction.getAbsolute(movement);
        this.xPos += absoluteDirection.xUnitChange * motionScale;
        this.yPos += absoluteDirection.yUnitChange * motionScale;
    }
    
    @Override
    public String toString() {
        return "Position: (" + xPos + ", " + yPos + "), Facing: " + direction;
    }
}