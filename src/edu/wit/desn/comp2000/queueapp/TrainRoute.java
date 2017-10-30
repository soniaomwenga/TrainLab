package edu.wit.desn.comp2000.queueapp;

import  edu.wit.dcsn.rosenbergd.queueapp.*;
/**
 * @author Sonia Omwenga
 * @version	1.0.0	first pass
 */
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.pearson.carrano.ArrayQueue;

import edu.wit.dcsn.rosenbergd.queueapp.Configuration.TrainSpec;

public class TrainRoute
{
	private ArrayList<Train> trainTracks;

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
			stations = new ArrayList <Station> (stationLocations.length);
			for (int i = 0; i < stationLocations.length; i++)
			{
				stations.add(new Station(stationLocations[i], this));
			}
			
			TrainSpec[] trainSpecs = config.getTrains();
			trainTracks =  new ArrayList <Train>(trainSpecs.length);
			for (int i = 0; i < trainSpecs.length; i++)
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
			return Direction.OUTBOUND;
		}
		else
		{
			return Direction.INBOUND;
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
			train.move();
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
				while (!stationArrivedAt.getPlatform(train.getDirection()).isEmpty() && 
					  (train.board(stationArrivedAt.depart(train.getDirection()))));
			}
		}
	}
	/**
	 * check if the current location is also 
	 * the location of a station along the route 
	 * @param location
	 * @return
	 */
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
	 * passes the arraylist of stations
	 * @return
	 */
	
	public ArrayList<Station> getStations()
	{
		return stations;
	}
	////////////// Test methods Below/////////////
	/**
	 * test location of the train to make sure it is actually on the 
	 * route and if it is removing and adding passengers at actual 
	 * indexes that are stations. 
	 * true if the given location is valid. 
	 * @param location
	 * @return
	 */
	private void testIfAtStation(int location)
	{
		for(int i = 0; i < stations.size();i++)
		{
			if (location == stations.get(i).getLocation())
			{
				System.out.println("Test for ifAtStation does pass.");
				break;
			}
			break;
		}
	}

	/**
	 * test to make sure there are at least 2 stations along 
	 * the train route at the beginning of the simulation
	 * @param stations
	 * @return
	 */
	private void testTrainRoute(ArrayList<Station> stations)
	{
		if (stations.size() > 1)
			System.out.println("theres are enough stations present to begin the simulation");
		else
			System.err.println("there's an insufficient number of stations.");
	}
	/**
	 * testing if getTrainAt works by passing in a train and 
	 * and searching for it along the entire tracks.
	 * @param train
	 * @param location
	 */
	private void testGetTrainAt (Train train, int location)
	{
		for (int i = 0; i < trainTracks.size(); i ++)
		{
			if(train.getLocation() == i)
			{
				System.out.println("Test for getTrainAt has pased");
			}
			else
			{
				System.err.println("Test for getTrainAt has not passed");
			}
		}
	}
	/**
	 * testing the moveTrains method by testing if the 
	 * location after one move is not equal to the location after another. 
	 * 
	 */
	private void testMoveTrains(Train train)
	{
		int firstLoc = train.getLocation();
		moveTrains();
		if(firstLoc == train.getLocation())
		{
			System.err.println("Test for moveTrains has not passed");
		}
		else
		{
			System.out.println("Test for moveTrains has passed.");
		}
	}
	/**
	 * tester method for whichDirection that tests by 
	 * passing the ID of two stations into whichDirection 
	 * and checking if the output is correct.
	 * 
	 */
	private void testWhichDirection()
	{
		int arr = 5; 
		int dest = 3; 
		
		if(whichDirection(arr,dest).equals("OUTBOUND"))
		{
			System.out.println("Test for whichDirection has passed");
		}
		else 
		{
			System.err.println("Test for whichDirection has not passed");
		}
	}
	
}
