package com.railway.other_components;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import com.railway.trains.Train;


public abstract class Segment {
	protected Segment nextSegment;
	protected int length;
	protected boolean isFull;
	protected ArrayList<Train> capacity = new ArrayList<Train>();
	protected ReentrantLock lock = new ReentrantLock();
	protected Condition cond = lock.newCondition();
	
	public Segment(int lenght) {
		this.length = lenght;
	}
	
	public boolean getIsFull() {
		return isFull;
	}
	
	//return the arraylist that contains the trains in a particular segment
	public ArrayList<Train> getCapacity(){
		return capacity;
	}
	
	public Segment getNextSegment() {
		return nextSegment;
	}
	
	public void setNextSegment(Segment nextSegment) {
		this.nextSegment = nextSegment;
	}
	
	public int getLength() {
		return length;
	}
	
	
	//method for train to enter station, if station is not full, checks if current segment is full
	//if it is it waits, if not enters the next segment;
	public void enter(Train train) {
		isFull = isFull();
		lock.lock();
		try {
			while(isFull == true) {
				cond.await();
			}
			isFull = false;
			capacity.add(train);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
	
	
	// method for train to depart station if next segment is not full checks to see
	// if next segment is full, if not it departs the last station
	public void depart(Train train) {
		boolean isNextFull = false;
		if(nextSegment != null) {
			isNextFull = nextSegment.getIsFull();
		}
		lock.lock();
		try {
			while(isNextFull == true) {
				cond.await();
			}
			capacity.remove(train);
			isFull = false;
			cond.signal();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
	
	public String createSegment() {
		String track = "|----" + getStatus() + "----|";
		return track;
	}
	
	public abstract String getStatus();
	public abstract boolean isFull();
	
}
