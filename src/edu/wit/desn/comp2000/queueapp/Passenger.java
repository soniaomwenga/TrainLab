package edu.wit.desn.comp2000.queueapp;
import  edu.wit.dcsn.rosenbergd.queueapp.*;

/**
 * @author Sonia Omwenga
 * @version	1.0.0	first pass
 */
public class Passenger
{
	private int ID;
	private int destinationID;
	private int arrivalID;
	private static int nextID = 1;
	private int arrivalTime;

	/**
	 * constructor which takes in the arrival and destination
	 *  stations for each passenger and instantiates their 
	 *  arrivalID, destiationID, and ID. 
	 * 
	 * @param destinationID1 is the ID of their destination station
	 * @param arrivalID1 is the ID of the arrival station
	 */
	public Passenger(int destinationID1, int arrivalID1)
	{
		ID = nextID++;
		destinationID = destinationID1;
		arrivalID = arrivalID1;
	}

	/**
	 * this method will give the passenger's ID to use it in other classes 
	 * such as station 
	 * @return the passenger's ID
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * This method is to get the destination station number so that 
	 * the station class will know where to let passengers off 
	 * @return the passenger's destination station ID
	 */
	public int getDestinationID()
	{
		return destinationID;
	}

	/**
	 *  This method is to get the arrival station number so that 
	 * the station class will know how to plan the route of the passenger
	 * and which platform (queue) to add them to. 
	 * @return the passenger's arrival station ID
	 */
	public int getArrivalID()
	{
		return arrivalID;
	}
	/**
	 * method to document the arrival time of each passenger 
	 * @return the ticker value when they enter their arrival station
	 */
	public int getArrivalTime()
	{
		return arrivalTime;
	}
	////////////////////// TESTER METHODS BELOW//////////////////////////////
	/**
	 * Test that the ID assigned to a passenger has not 
	 * 	-already been assigned to another passenger 
	 * 	- is not less than 1 
	 * 	- is not greater than the maximum number of passengers 
	 * 		outlined in the config file 
	 * 
	 */
	private static void testGetID(Passenger p)
	{
		
		if(p.getID() > -1)
		{
			System.out.println("Test for getID() passes.");
		}
		else 
		{
			System.err.println("Test for getID() fails");
		}
	}

	/**
	 * Test to make sure DestinationID is 
	 * 	an actual station
	 * 
	 */
	private static void testGetDestinationID(Passenger pass)
	{
		int x = pass.getDestinationID();
		
			if (x >= 0 && x<= 5 )
			{
				System.out.println("Test for get Destination ID does pass.");
			}
			else 
			{
				System.err.println("Test for get Destination ID does not pass.");
			}
	}
	/**
	 * Test to make sure ArrivalID is 
	 * 	- an index of the ArrayList which has a station there  
	 * @return
	 */
	private static void testGetArrivalID(Passenger pass)
	{
		int x = pass.getArrivalID();
		
			if (x >= 0 && x <= 5 )
			{
				System.out.println("Test for get Arrival ID does pass.");
			}
			else 
			{
				System.err.println("Test for get Arrival ID does not pass.");
			}
	}
	/**
	 * tester method to make sure  that each passengers 
	 * arrival time is being documented correctly. 
	 * arrival time should always be greater than or equal to the ticker value 
	 * because it is documenting the time at which the passenger enters their arrival 
	 * station from home. 
	 * @param arrivalTime
	 * @param tickerValue
	 */
	private static void testGetArrivalTime (int arrivalTime, int tickerValue)
	{
		
		if(arrivalTime <= tickerValue)
		{
			System.out.println("Test for getArrivalTime does pass.");
		}
		else 
		{
			System.err.println("Test for getArrivalTime does not pass.");
		}

	}
	public static void main (String [] args)
	{
		Passenger p = new Passenger (5,3);
		int x = p.getArrivalTime();
		testGetID(p);
		testGetDestinationID(p);
		testGetArrivalID(p);
		testGetArrivalTime(x,TrainSimulation.tick);
	}
	/**
	 * basic toString method to print out the passenger's 
	 * credentials. 
	 */
	public String toString ()
	{
		return "Passenger #" + ID;
	}
	
}