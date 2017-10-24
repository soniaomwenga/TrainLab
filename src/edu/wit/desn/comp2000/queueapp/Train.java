package edu.wit.desn.comp2000.queueapp;
//Wes
import java.util.ArrayList;

public class Train
{
/*Inbound & outbound tracks. 
 * Use ArrayList for the two moving trains per route 
 *  This 
 * 
 */
	private ArrayList<Passenger> train = new ArrayList<Passenger> (30); 
	
	private int trainID; 
	
	private int location; 
	
	private Direction direction; 
	
	public Train(int ID, int initialLocation, Direction initialDirection)
	{
		trainID = ID;
		location = initialLocation;
		direction = initialDirection;
	}
	
	public void board(Passenger pass)
	{
		train.add(pass);
		//STUB
		
	}
	
	public void disembark(Passenger pass)
	{
		train.remove(pass);
	}
	
	@Override
	public String toString()
	{
		return "Train "+trainID+"  Location: "+location;
	}
	
//Accessor methods below
	/**
	 * @return the train
	 */
	public ArrayList<Passenger> getTrain() {
		return train;
	}

	/**
	 * @return the trainID
	 */
	public int getTrainID() {
		return trainID;
	}

	/**
	 * @return the location
	 */
	public int getLocation() {
		return location;
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}
	
	public static void main(String[] args)
	{
		Train test = new Train(1,10, Direction.INBOUND);
		System.out.println(test.getTrainID());
		System.out.println(test.getLocation());
		System.out.println(test.getDirection());
		
	}
}
