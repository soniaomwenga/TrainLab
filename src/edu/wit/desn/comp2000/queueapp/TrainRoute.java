package edu.wit.desn.comp2000.queueapp;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.pearson.carrano.ArrayQueue;

public class TrainRoute
{
	private ArrayList<Train> inboundTracks = new ArrayList<Train>(30);
	private ArrayList<Train> outboundTracks = new ArrayList<Train>(30);
	
	private ArrayList<Station> stations;

	/*
	 * private int locationInbound; 
	 * private int locationOutbound; 
	 * private int
	 * destinationID; 
	 * private int arrivalID;
	 */
	public TrainRoute()
	{
		try
		{
			Configuration config = new Configuration();
			// getting location of each station from config file
			int[] stationLocations = config.getStations();
			for (int i = 0; i < stationLocations.length; i++)
			{
				//stations.add(stationLocations[i]);
				stations.add(new Station (i, stationLocations[i]));	
			}
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	/**
	 * Determines which direction the Passenger should travel 
	 * @param Passenger
	 * @return either INBOUND or OUTBOUND
	 */
	private Direction whichDirection(Passenger pass)
	{
		int destStationLocation = -1;
		int arrStationLocation = -1;
		for(Station s:stations)
		{
			if(s.getStationID() == pass.getDestinationID())
			{
				destStationLocation = s.getLocation();
			}
			if(s.getStationID() == pass.getArrivalID())
			{
				arrStationLocation = s.getLocation();
			}
		}
		if(destStationLocation < arrStationLocation)
		{
			return Direction.INBOUND;
		}
		else return Direction.OUTBOUND;

	}

	/**
	 * this method will reverse the direction of 
	 * any train which has reached the end of the route. 
	 * @param location
	 * @return
	 **/
	/*
	 * public void reverseDirection(int location) { //inboundTrain Tracks begin
	 * at index 0 and move to index 29 then reverses if (locationInbound ==
	 * (inboundTracks.size()-1)) { for (int i = locationInbound; i > 0; i--) { }
	 * } //Outbound train track if (locationOutbound ==
	 * (outboundTracks.size()-(outboundTracks.size()-1))) { } }
	 */
	////////////// Test methods Below/////////////
	/**
	 * test location of the train to make sure it is actually on the 
	 * route and if it is removing and adding passengers at actual 
	 * indexes that are stations. 
	 * true if the given location is valid. 
	 * @param location
	 * @return
	 */
	public boolean testLocation(int locationInbound)
	{
		if (locationInbound >= 0 && locationInbound < 30)
			return true;
		else
			return false;
	}
	/**
	 * test to make sure there are at least 2 stations along 
	 * the train route at the beginning of the simulation
	 * @param stations
	 * @return
	 */
	public String testTrainRoute (ArrayList<Object> stations)
	{
		if (stations.size() > 1)
			return "theres are enough stations present to begin the simulation";
		else 
			return "theres an insufficient number of stations.";
	}

	//////////// main method to use test methods //////
	public static void main(String[] args)
	{

	}
}
