package edu.wit.desn.comp2000.queueapp;
//Wes
import java.util.ArrayList;
import  edu.wit.dcsn.rosenbergd.queueapp.*;

/**
 * @author Wes Brimeyer
 * @version	1.0.0	first pass
 */
public class Train
{
/*Inbound & outbound tracks. 
 * Use ArrayList for the two moving trains per route 
 *  This 
 * 
 */
	
	private ArrayList<Passenger> train;
	private int trainID; 
	private int location; 	
	private Direction direction; 	
	private int maxPassengers;	
	private static int nextID = 1;
	
	public Train(int initialLocation, Direction initialDirection, int capacity)
	{
		trainID = nextID++;
		location = initialLocation;
		direction = initialDirection;
		maxPassengers = capacity;
		train = new ArrayList<Passenger> (capacity);
	}
	
	public void move()
	{
		Logger.write("Moving "+this);
		if(direction == Direction.INBOUND)
		{
			location++;
		}
		else location--;
	}
	
	/**
	 * Passenger gets on the train
	 * @param pass
	 */
	public boolean board(Passenger pass)
	{
		if(train.size() < maxPassengers)
		{
			train.add(pass);
			Logger.write(pass + " has boarded "+this);
			return true;
		}
		else 
		{
			Logger.write(this + "is now full");
			return false;
		}
	
		
	}
	
	/**
	 * Passenger gets off the train, removes from train array
	 * @param pass
	 */
	public void disembark(Passenger pass)
	{
		train.remove(pass);
	}
	
	@Override
	public String toString()
	{
		return "Train #"+trainID;// +"  Location: "+location;
	}
	
//Accessor methods below
	/**
	 * @return the train
	 */
	public ArrayList<Passenger> getTrain() 
	{
		return train;
	}

	/**
	 * @return the trainID
	 */
	public int getTrainID() 
	{
		return trainID;
	}

	/**
	 * @return the location
	 */
	public int getLocation() 
	{
		return location;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	public boolean isFull()
	{
		if(train.size() < maxPassengers)
		{
			return false;
		}
		else return true;
	}
	
//just to test
	public static void main(String[] args)
	{
		Logger.Create();
		System.out.println("---------------\nTests (Very basic and rough)...\n");
		
		testConstructorAndGetters();
		testMove();
		testBoardDisembark();
	}
//below are private tester methods
	private static void testConstructorAndGetters()
	{
		Train test = new Train(5, Direction.INBOUND, 30 );
		
		if(test.getDirection() == Direction.INBOUND && test.getLocation() == 5
				&& test.getTrain().size() == 0 && test.getTrainID() == 1)
		{
			System.out.println("Constructors and Getters test Succeeded\n");					
		}
		else System.out.println("Constructors and Getters test Failed\n");		
	}
	private static void testMove()
	{
		System.out.println("Testing move()...");
		Train test = new Train(5, Direction.INBOUND, 20);
		
		System.out.println("Expected: (Tick, Location)...(1,6)(2,7)(3,8)...(15,20)(16,19)(17,18)(18,17)(19,16)(20,15)");
		System.out.print("Actual: ");
		for(int i = 1; i <= 20 ; i++) //tests move for 20 ticks
		{
			test.move();
			System.out.print("("+i+","+test.location+")");
		}
			
	
	}
	private static void testBoardDisembark()
	{
		System.out.println("\n\nTesting Board AND Disembark...\n");
		
		Passenger bob = new Passenger(3,1);
		Train test = new Train(7, Direction.OUTBOUND, 20);
		boolean didItWork = true;
		
		test.board(bob);
		if(test.getTrain().size() != 1 || test.getTrain().get(0) == bob)
			didItWork = false;
		
		test.disembark(new Passenger(1,2)); //this Passenger shouldn't be on the train
		if(test.getTrain().size() != 1 || test.getTrain().get(0) == bob)
			didItWork = false;
		
		test.disembark(bob);
		if(test.getTrain().size() != 0)
			didItWork = false;
		
		if(didItWork)
		{
			System.out.println("Test for board() and disembark() SUCCEEDED\n");
		}
		else System.out.println("Test for board() and disembark() SUCCEEDED\n");
	}

}
