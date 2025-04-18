package Movement;
import java.util.List;
import java.util.Arrays;

public class MainRun{

	public static void main(String[] args) {
		// Example usage
		Position pos = new Position(0, 0, Direction.NORTH);
		pos.Move(GridChange.Xpos, 5);
		System.out.println("New Position: (" + pos.xPos + ", " + pos.yPos + ")");
		
		pos.Rotate(Rotation.CLOCKWISE, 1);
		System.out.println("New Direction: " + pos.direction);
	}
}

class Position{

	public int xPos;
	public int yPos;
	public Direction direction;

    // Define direction list as a static field of the class
	private static final List<Direction> directionList = Arrays.asList(
		Direction.NORTH, Direction.EAST, 
		Direction.SOUTH, Direction.WEST
	);
	
	Position(int xPos, int yPos, Direction direction)
	{
		this.xPos = xPos;
		this.yPos = yPos;
		this.direction = direction;
	}
	
	Position()
	{
		this(0, 0, Direction.NORTH);
	}
	
	public void Move(GridChange movement, int quantity)
	{
		this.xPos += movement.xUnitChange * quantity;
		this.yPos += movement.yUnitChange * quantity;
	}
	
	public void Rotate(Rotation movement, int quantity) throws IllegalArgumentException
	{
		//  Get current index of current direction in List of Directions. And size of Direction list.
		
		int index = directionList.indexOf(direction);
		int sizeList = directionList.size();
		
		// Get the remainder of mod(N, a + k), where N is the length of direction list, a is the current index and k is 'quantity' of rotation.
		// The remainder is the index of final direction if rotation is right else invert final index for Left.
		
		int newIndex;
		if (movement == Rotation.CLOCKWISE) {
			newIndex = (index + quantity) % sizeList; // Rotate RIGHT
		} else if (movement == Rotation.ANTICLOCKWISE) {
			newIndex = (index - quantity + sizeList) % sizeList; // Rotate LEFT
		} else {
			throw new IllegalArgumentException("Invalid movement for rotation");
		}
		
		this.direction = directionList.get(newIndex);
	}
}

enum GridChange {
    Xpos(1, 0), Xneg(-1, 0), Ypos(0, 1), Yneg(0, -1);
    
    public int xUnitChange;
    public int yUnitChange;
    
    GridChange(int xUnitChange, int yUnitChange) // Fixed: added int type
    {
        this.xUnitChange = xUnitChange;
        this.yUnitChange = yUnitChange;
    }
}

enum Rotation{

	CLOCKWISE, ANTICLOCKWISE;
}

enum Direction {
    // Enums with the gridchanges that correspond to Forward, Backward, Right, Left.
    NORTH(GridChange.Ypos, GridChange.Yneg, GridChange.Xpos, GridChange.Xneg), 
    SOUTH(GridChange.Yneg, GridChange.Ypos, GridChange.Xneg, GridChange.Xpos),
    WEST(GridChange.Xneg, GridChange.Xpos, GridChange.Yneg, GridChange.Ypos), 
    EAST(GridChange.Xpos, GridChange.Xneg, GridChange.Ypos, GridChange.Yneg);
    
    public final GridChange FORWARD;
	public final GridChange BACKWARD; 
    public final GridChange RIGHT; 
    public final GridChange LEFT;
    
    Direction(GridChange forward, GridChange backward, GridChange right, GridChange left)
    {
        this.FORWARD = forward;
        this.BACKWARD = backward;
        this.RIGHT = right;
        this.LEFT = left;
    }
}

