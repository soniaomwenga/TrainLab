package edu.wit.desn.comp2000.queueapp;
//Wes
import com.pearson.carrano.ArrayQueue;

/**
 * @author Wes Brimeyer
 * @version	1.0.0	first pass
 */
public class Station
{
	
	private int stationID; 
	private int location; 
	
	private ArrayQueue<Passenger> inboundPlatform;
	private ArrayQueue<Passenger> outboundPlatform;
	
	/**
	 * initialize platforms to capacity of 50. 
	 * @param ID
	 * @param location1
	 */
	public Station(int ID, int location1)
	{
		stationID = ID;
		location = location1;
		inboundPlatform = new ArrayQueue<Passenger>(50);
		outboundPlatform = new ArrayQueue<Passenger>(50);
	}
	
	
	/**
	 * add passenger to the correct platform. 
	 * @param a Passenger, which platform to add it to (inbound or outbound)
	 */
	public void addPassengerToPlatform(Passenger pass, Direction platformNeeded)
	{
		if(platformNeeded == Direction.INBOUND)
		{
			inboundPlatform.enqueue(pass);
		}
		else
		{
			outboundPlatform.enqueue(pass);
		}
	}
	
	/**
	 * Passenger enters the station from outside
	 * 
	 * @param pass
	 */
	public void enter(Passenger pass)
	{
		
	}
	
	/**
	 * Passenger exits the station and goes outside into the real world
	 * 
	 * @param pass
	 */
	public void exit(Passenger pass)
	{
		
	}
	
	/**
	 * Passenger arrives at station after getting off a train
	 * 
	 * @param pass
	 */
	public void arrive(Passenger pass)
	{
		
	}
	
	/**
	 * Passenger departs the station and enters a train,
	 * removes passenger from the platform queue
	 * @param a Passenger, the Direction of the platform 
	 */
	public void depart(Passenger pass, Direction platformDeparted)
	{
		if(platformDeparted == Direction.INBOUND)
		{
			inboundPlatform.dequeue();
		}
		else
		{
			outboundPlatform.enqueue(pass);
		}
		
	}
	
//Accessor Methods below---
	/**
	 * @return the stationID
	 */
	public int getStationID() 
	{
		return stationID;
	}


	/**
	 * @return the location
	 */
	public int getLocation() 
	{
		return location;
	}


	/**
	 * @return either inboundPlatform or outboundPlatform Queue
	 */
	public ArrayQueue<Passenger> getPlatform(Direction direction) 
	{
		if(direction == Direction.INBOUND)
			return inboundPlatform;
		else 
		{
			return outboundPlatform;
		}
	}


}
