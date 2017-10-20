package edu.wit.desn.comp2000.queueapp;
//Wes
import com.pearson.carrano.ArrayQueue;

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
