package edu.wit.desn.comp2000.queueapp;

/**
 * @author Sonia Omwenga
 * @version	1.0.0	first pass
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.pearson.carrano.ArrayQueue;

import edu.wit.desn.comp2000.queueapp.Configuration.TrainSpec;

public class TrainRoute
{
	private ArrayList<Train> trainTracks = new ArrayList<Train>(20);

	private  ArrayList<Station> stations;
	
	private int trackLength;

	/*
	 * private int locationInbound; private int locationOutbound; private int
	 * destinationID; private int arrivalID;
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
				stations.add(new Station(stationLocations[i], this));
			}
			
			TrainSpec[] trainSpecs = config.getTrains();
			for (int i = 0; i < trainTracks.size(); i++)
			{
				trainTracks.add(new Train(trainSpecs[i].location, trainSpecs[i].direction, trainSpecs[i].capacity));
			}
			
			trackLength  = config.getRoute().length;
			
		}
		catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getTrackLength()
	{
		return trackLength;
	}
	/**
	 * Determines which direction the Passenger should travel 
	 * @param Passenger
	 * @return either INBOUND or OUTBOUND
	 */
	public Direction whichDirection(int destStationID, int arrStationID)
	{
		int destStationLocation = -1;
		 int arrStationLocation = -1;
		 	for(Station s:stations)
		 		{
		 			//find the location of the passenger's destination station 
		 			//
		 			if(s.getStationID() == destStationID)
		 			{
		 				destStationLocation = s.getLocation();
					}
		 			if(s.getStationID() == arrStationID)
		 			{
		 				arrStationLocation = s.getLocation();
		 			}
		 		}
		if (destStationLocation < arrStationLocation)
		{
			return Direction.INBOUND;
		}
		else
		{
			return Direction.OUTBOUND;
		}
	}

	/**
	 * checking if there is a train at the given location. if there is, 
	 * return the train and all its contents. 
	 * @param location
	 * @return
	 */
	private Train getTrainAt(int location)
	{
		for (int i = 0; i < trainTracks.size(); i++)
		{
			if (trainTracks.get(i).getLocation() == location)
			{
				return trainTracks.get(i);
			}
		}
		return null;
	}
	/*
	 * checks if the current train is at a station 
	 * based on it's location and the known locations of the stations 
	 * 
	 */
	private boolean ifAtStation(Train train)
	{
		for(int i = 0; i < stations.size(); i++)
		{
			if(train.getLocation() == stations.get(i).getLocation())
			{
				return true;
			}
		}
		return false;
	}
	/*
	 * passes to Train.java 
	 */
	public void moveTrains()
	{
		for(Train train:trainTracks)
		{
			if(ifAtStation(train))
			{
				Station stationArrivedAt = getStationAt(train.getLocation());
				for(Passenger p:train.getTrain())
				{
					if(p.getDestinationID() == stationArrivedAt.getStationID())
					{
						train.disembark(p);
						stationArrivedAt.arrive(p);
					}
				}
				
				//while there are passengers on the platform waiting to get on the train
				while (!stationArrivedAt.getPlatform(train.getDirection()).isEmpty()) 
				{
					while(train.board(stationArrivedAt.depart(train.getDirection())));
				}
			}
			else 
				train.move();
		}
	}
	private Station getStationAt (int location)
	{
		for (int i = 0; i < stations.size(); i++)
		{
			if (stations.get(i).getLocation() == location)
			{
				return stations.get(i);
			}
		}
		return null;
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
	public String testTrainRoute(ArrayList<Object> stations)
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
