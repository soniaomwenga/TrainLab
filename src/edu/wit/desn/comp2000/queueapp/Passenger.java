package edu.wit.desn.comp2000.queueapp;

public class Passenger
{
	private int ID;
	private int destinationID;
	private int arrivalID;

	public Passenger(int ID1, int destinationID1, int arrivalID1)
	{
		ID = ID1;
		destinationID = destinationID1;
		arrivalID = arrivalID1;

	}

	/**
	 * this method will get the ID passengers ID to use it in other classes 
	 * such as station 
	 * @return
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * This method is to get the destination station number so that 
	 * the station class will know where to let passengers off 
	 * @return
	 */
	public int getDestinationID()
	{
		return destinationID;
	}

	/**
	 *  This method is to get the arrival station number so that 
	 * the station class will know how to plan the route of the passenger
	 * and which platform (queue) to add them to. 
	 * @return
	 */
	public int getArrivalID()
	{
		return arrivalID;
	}

	/**
	 * boolean method to make sure the random passenger generator 
	 * does not accidentally set the passengers arrival station equal 
	 * to their destination on but checking this before inserting the 
	 * passenger into the simulation. 
	 * @param destination
	 * @param arrival
	 * @return
	 */
	public boolean check(int destination, int arrival)
	{
		if (destination != arrival)
			return false;
		else
			return true;
	}

	////////////////////// TESTER METHODS BELOW//////////////////////////////
	/**
	 * Test that the ID assigned to a passenger has not 
	 * 	-already been assigned to another passenger 
	 * 	- is not less than 1 
	 * 	- is not greater than the maximum number of passengers 
	 * 		outlined in the config file 
	 * @return
	 */
	public boolean testGetID()
	{
		return true;
	}

	/**
	 * Test to make sure DestinationID is 
	 * 	- an index of the ArrayList which has a station there  
	 * @return
	 */
	public boolean testGetDestinationID()
	{
		return true;
	}
	/**
	 * Test to make sure ArrivalID is 
	 * 	- an index of the ArrayList which has a station there  
	 * @return
	 */
	public boolean testGetArrivalID()
	{
		
		return true;
	}
	public String toString ()
	{
		return "ID: " + ID + " Arrival Station: " + arrivalID + " Destination Station: " + destinationID;
	}
	///////// main method to test use all the test methods////////////
	public static void main (String [] args)
	{
		
	}
}