package edu.wit.desn.comp2000.queueapp;
//Wes
import java.util.ArrayList;

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
	
	private static int nextID = 0;
	
	public Train(int initialLocation, Direction initialDirection, int capacity)
	{
		trainID = nextID++;
		location = initialLocation;
		direction = initialDirection;
		maxPassengers = capacity;
		train = new ArrayList<Passenger> (capacity);
	}
	
	/**
	 * Passenger gets on the train
	 * @param pass
	 */
	public void board(Passenger pass)
	{
		if(train.size() < maxPassengers)
			train.add(pass);
			//Logger.write(pass + " has boarded "+train
		//else Logger.write(train + "is now full");
		
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
	
//just to test
	public static void main(String[] args)
	{
		//Train test = new Train(1,10, Direction.INBOUND);
		//System.out.println(test.getTrainID());
		//System.out.println(test.getLocation());
		//System.out.println(test.getDirection());
		
	}
}
