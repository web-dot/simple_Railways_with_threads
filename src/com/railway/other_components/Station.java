package com.railway.other_components;

public class Station extends Segment {
	private String stationName;
	private int stationSize;
	
	public Station(String stationName, int stationSize, int length) {
		super(length);
		this.stationName = stationName;
		this.stationSize = stationSize;
	}
	
	public int getSize() {
		return stationSize;
	}
	
	public String getStatus() {
		String status = null;
		if(capacity != null) {
			status = stationName + " " + capacity.toString();
		}
		else {
			status = stationName;
		}
		return status;
	}
	
	public boolean isFull() {
		if(capacity.size() == stationSize) {
			isFull = true;
		}
		else if(capacity.size() < stationSize) {
			isFull = false;
		}
		return isFull;
	}
}







