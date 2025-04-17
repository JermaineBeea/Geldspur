class MainRun{

	public static void main(String[] args)
	{
		Position pos1 = new Position();
		Position pos2 = new Position(1, 2, Direction.EAST);

		pos1.movePosition(Move.LEFT);
		pos1.movePosition(Move.FORWARD);
		
		System.out.println("Position is " + pos1.getPosition());
		System.out.println("Direction is " + pos1.getDirection());

		System.out.println("Position2 is " + pos2.getPosition());
		System.out.println("Direction2 is " + pos2.getDirection());
	}

}

enum Direction {

	NORTH("Facing North"), SOUTH("Facing South"), EAST("Facing East"), WEST("Facing West");
	
	public String directionMessage;
	
	Direction(String directionMessage){
		this.directionMessage = directionMessage;
	}

}

enum Move{

	LEFT(-1), RIGHT(1), FORWARD(1), BACKWARD(-1);
	
	public int vector;
	
	Move(int vector)
	{
		this.vector = vector;
	}
}

class Position{

	private int xPos;
	private int yPos;
	private String direction;
	
	Position(int Xpos, int Ypos, Direction directionFacing){
		
		this.xPos = Xpos;
		this.yPos = Ypos;
		this.direction = directionFacing.directionMessage;
	}
	
	Position(){
		this(0, 0, Direction.NORTH);
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	public int[] getPosition()
	{
		return new int[]{xPos, yPos};
	}
	
	public void movePosition(Move movement){
	
		switch(movement)
		{
			case LEFT:
				this.xPos += movement.vector;
			
			case RIGHT:
				this.xPos += movement.vector;
			
			case FORWARD:
				this.yPos += movement.vector;
			
			case BACKWARD:
				this.yPos -= movement.vector;
		}
	}

}