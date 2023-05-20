package com.railway.trains;

import java.util.LinkedList;

import com.railway.other_components.Segment;
import com.railway.other_components.Station;

public abstract class Train implements Runnable {
	protected int trainSpeed;
	protected int trainNumber;
	// each train has an incremented train nummber
	protected static int nextTrainNumber = 1;
	
	protected Segment currentSegment;
	protected Segment nextSegment;
	protected LinkedList<Segment> railway;
	
	// constructor creates a train object and is added to the first segment
	public Train(LinkedList<Segment> railwayToAdd) {
		this.railway = railwayToAdd;
		trainNumber = nextTrainNumber;
		nextTrainNumber++;
		currentSegment = railway.getFirst();
		nextSegment = currentSegment.getNextSegment();
		currentSegment.enter(this);
	}
	
	
	public Segment getNextSegment() {
		return nextSegment;
	}
	
	public void moveTrain() {
		if(nextSegment != null) {
			nextSegment.enter(this);
			currentSegment.depart(this);
			currentSegment = nextSegment;
			nextSegment = currentSegment.getNextSegment();
		}
	}
	
	
	/**
	 * override run() method that calls train to move if there is a next segment
	 * or to relinquish the train if it finished at the last station 
	 * 
	 *  An `express` train which has a speed of 500 m/s will get through a track in 2 
	 *  seconds and a station in 0.2 seconds
	 *  A `local` train which has a speed of 10m/s will get through a track in 100 seconds
	 *  and a station in 10 seconds
	 * 
	 * */
	
	@Override
	public void run() {
		int timeAtSegment;
		while(true) {
			try {
				//time a train is on a segment in milliseconds
				timeAtSegment = (currentSegment.getLength() / trainSpeed) * 1000;
				if(currentSegment instanceof Station) {
					timeAtSegment += 5000;
				}
				Thread.sleep(timeAtSegment);
				moveTrain();
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public String toString() {
		return "" + trainNumber;
	}
}
