package edu.wit.desn.comp2000.queueapp;

public class Passenger
{
private int ID;
private int destinationID;
private int arrivalID;

public Passenger (int ID1, int destinationID1, int arrivalID1)
{
ID = ID1;
destinationID = destinationID1;
arrivalID = arrivalID1;
	
}


public int getID()
{
	return ID;
}

public int getDestinationID()
{
	return destinationID;
}

public int getArrivalID()
{
	return arrivalID;
}

public boolean check(int destination, int arrival)
{
	if (destination != arrival)
		return false;
	else 
		return true;
	
}
}