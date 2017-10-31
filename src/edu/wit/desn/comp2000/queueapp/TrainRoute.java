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
	/**
	 * constructor that set the values of trainTracks, stations, 
	 * and trackLength based on the configuration file. 
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
			
			//getting the location, direction, and capacity of each train from the configuration file.
			TrainSpec[] trainSpecs = config.getTrains();
			trainTracks =  new ArrayList <Train>(trainSpecs.length);
			for (int i = 0; i < trainSpecs.length; i++)
			{
				trainTracks.add(new Train(trainSpecs[i].location, trainSpecs[i].direction, trainSpecs[i].capacity, this));
			}
			
			//getting the track length from the configuration file
			trackLength  = config.getRoute().length;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * returns the length of the tracks
	 * @return the length of the route as specified in the configuration file.
	 */
	public int getTrackLength()
	{
		return trackLength;
	}
	/**
	 * Determines which direction the Passenger should travel 
	 * based on their arrival station and destination station
	 * @param destStationID  is the destination station
	 * @param arrStationID is the arrival station
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
		 	// if the location of the destination station is lower than 
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
	 * checks if the current train is at a station 
	 * based on it's location and the known locations of the stations 
	 * @param train
	 * @return whether or not the current location is a station
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
	/**
	 * checks if each train on the tracks is at a station. 
	 * if it is at a station, it will decide whether to board/ disembark passengers based on their route
	 * if it is not at a station, it will move the train again. 
	 */
	public void moveTrains()
	{
		// for each train within trainTracks
		for(Train train:trainTracks)
		{
			train.move();
			//if this respective train when passed to ifAtStation returns true
			if(ifAtStation(train))
			{
				Station stationArrivedAt = getStationAt(train.getLocation());
				for(int i = 0; i < train.getTrain().size(); i++)
				{
					Passenger p = train.getTrain().get(i);
					if(p.getDestinationID() == stationArrivedAt.getStationID())
					{
						train.disembark(p);
						stationArrivedAt.arrive(p);
						i--;
					}
				}
				/*	
				 * while there are passengers on the platform waiting to get on the train
				 * & the train present is going the direction the passenger needs from their 
				 * arrival station
				*/
				while (!stationArrivedAt.getPlatform(train.getDirection()).isEmpty() && 
					  (train.board(stationArrivedAt.depart(train.getDirection()))));
			}
			
		}
	}
	
	/**
	 * check if the current location is also 
	 * the location of a station along the route 
	 * @param location
	 * @return the location if there is a station there. if not it returns null
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
	 * @return the entire array list of stations 
	 */
	
	public ArrayList<Station> getStations()
	{
		return stations;
	}
	
	public ArrayList<Train> getTrains()
	{
		return trainTracks;
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
	private static void testIfAtStation(int location)
	{
		TrainRoute tr = new TrainRoute();
		for(int i = 0; i < tr.stations.size();i++)
		{
			if (location == tr.stations.get(i).getLocation())
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
	private static void testTrainRoute(ArrayList<Station> stations)
	{
		if (stations.size() > 1)
			System.out.println("There are enough stations present to begin the simulation");
		else
			System.err.println("There's an insufficient number of stations.");
	}
	/**
	 * testing if getTrainAt works by passing in a train and 
	 * and searching for it along the entire tracks.
	 * @param train
	 * @param location
	 */
	private static void testGetTrainAt (Train train)
	{
		TrainRoute tr = new TrainRoute();
		for (int i = 0; i < tr.trainTracks.size(); i ++)
		{
			if(train.getLocation() == i)
			{
				System.out.println("Test for getTrainAt has passed");
				break;
			}
		}
	}
	/**
	 * testing the moveTrains method by testing if the 
	 * location after one move is not equal to the location after another. 
	 * 
	 */
	private static void testMoveTrains(Train train)
	{
		Logger.Create();
		TrainRoute tr = new TrainRoute();
		int firstLoc = train.getLocation();
		tr.moveTrains();
		if(firstLoc == train.getLocation() && !tr.ifAtStation(train))
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
	private static void testWhichDirection()
	{
		int arr = 4; 
		int dest = 3; 
		TrainRoute train = new TrainRoute();
		if(train.whichDirection(dest,arr).equals(Direction.OUTBOUND))
		{
			System.out.println("Test for whichDirection has passed");
		}
		else 
		{
			System.err.println("Test for whichDirection has not passed");
		}
	}
	public static void main(String [] args)
	{
		
		testWhichDirection();
		TrainRoute tr = new TrainRoute();
		Train train = new Train(1, Direction.INBOUND, 20, tr);
		testIfAtStation(1);
		testMoveTrains(train);
		testGetTrainAt(train);
		testTrainRoute(tr.stations);
	}
	
}
