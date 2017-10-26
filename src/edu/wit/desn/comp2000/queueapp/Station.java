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
	private TrainRoute trainRoute;
	private static int nextID = 0;
	
	private ArrayQueue<Passenger> inboundPlatform;
	private ArrayQueue<Passenger> outboundPlatform;
	
	/**
	 * initialize platforms to capacity of 50. 
	 * @param ID
	 * @param location1
	 */
	public Station(int location, TrainRoute route)
	{
		stationID = nextID++;
		this.location = location;
		trainRoute = route;
		inboundPlatform = new ArrayQueue<Passenger>(50);
		outboundPlatform = new ArrayQueue<Passenger>(50);
	}
	
	
	/**
	 * add passenger to the correct platform. 
	 * @param a Passenger
	 */
	public void addToPlatform(Passenger pass)
	{
		//which platform to add it to (inbound or outbound)
		Direction platformNeeded = trainRoute.whichDirection(pass.getDestinationID(), pass.getArrivalID());
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
		//Logger.write(pass + " has entered " + station);
		addToPlatform(pass);
	}
	
	/**
	 * Passenger exits the station and goes outside into the real world
	 * 
	 * @param pass
	 */
	public void exit(Passenger pass)
	{
		//Logger.write(pass + " has left "+station+" and gone outside");
	}
	
	//When a Train arrives at a Station, two sets of interactions occur: 
	//(1) Passengers disembark() the Train and simultaneously/immediately arrive() at the Station 
	/**
	 * Passenger arrives at station after getting off a train
	 * 
	 * @param pass
	 */
	public void arrive(Passenger pass)
	{
		if(pass.getDestinationID() == stationID)
		{
			//Logger.write(pass + " has arrived at his/her destination, "+ station);
			exit(pass);
		}
		else
		{
			//Logger.write(pass + " has arrived at " + station + ", but this is not the desired destination!");
			addToPlatform(pass);
		}
		 
	}
	
	//(2), while there are Passengers waiting on the platform 
	//and the Train has room for more Passengers, a Passenger leave()s the Station (platform) and board()s the Train
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
	
	@Override
	public String toString()
	{
		return "Station #" + stationID;
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
