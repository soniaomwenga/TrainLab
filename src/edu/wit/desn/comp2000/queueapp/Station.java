package edu.wit.desn.comp2000.queueapp;
//Wes
import com.pearson.carrano.ArrayQueue;

public class Station
{
	private TrainRoute trainRoute = new TrainRoute();
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
	 * Determines which direction the Passenger should travel 
	 * @param Passenger
	 * @return either INBOUND or OUTBOUND
	 */
	private Direction determinePlatform(Passenger pass)
	{
		int destStationLocation = -1;
		for(Station s:trainRoute.getStations())
			if(s.stationID == pass.getDestinationID())
			{
				destStationLocation = pass.getDestinationID();
			}
		if(destStationLocation < this.location)
		{
			return Direction.INBOUND;
		}
		else return Direction.OUTBOUND;

	}
	/**
	 * add passenger to the correct platform. 
	 * @param Pass
	 */
	public void addPassengerToQueue(int Pass)
	{
		
	}
	
	/**
	 * removing passengers from the train if their destination is the current location
	 * @param Pass
	 */
	public void removePassengersFromTrain(int Pass)
	{
		
	}
	
	
}
