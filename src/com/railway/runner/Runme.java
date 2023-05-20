package com.railway.runner;

import java.util.LinkedList;

import com.railway.other_components.Segment;
import com.railway.other_components.Station;
import com.railway.other_components.Track;
import com.railway.other_components.TrackStatus;
import com.railway.other_components.TrainMaker;

public class Runme {

	public static void main(String[] args) {
		
		
		// creating segment object, can be station or track
		Station station1 = new Station("Jabalpur", 3, 100);
		Track track1 = new Track(1000);
		
		Station station2 = new Station("Raipur", 2, 100);
		Track track2= new Track(1000);
		
		Station station3 = new Station("Kharagpur", 2, 100);
		
		// segments are added to a railway linked list
		LinkedList<Segment> railway = new LinkedList<>();
		
		// segments are connected to each other
		railway.add(station1);
		station1.setNextSegment(track1);
		
		railway.add(track1);
		track1.setNextSegment(station2);
		
		railway.add(station2);
		station2.setNextSegment(track2);
		
		railway.add(track2);
		track2.setNextSegment(station3);
		
		railway.add(station3);
		station3.setNextSegment(null);
		
		
		// trainmaker object that makes trains
		TrainMaker trainMaker1 = new TrainMaker(railway);
		
		
		
		Thread t1 = new Thread(trainMaker1);
		t1.start();
		
		// tracstatus object responsible for printing status
		TrackStatus trackStatus1 = new TrackStatus(railway);
		
		
		boolean isRunning = true;
		
		
		while(isRunning) {
			try {
				Thread.sleep(1000);
				trackStatus1.printStatus();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
		
		
		
		
	}

}
