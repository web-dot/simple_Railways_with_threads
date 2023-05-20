package com.railway.other_components;

public class Track extends Segment {
	final private String segmentName = "Track";
	final private int trackSize = 1;
	
	public Track(int length) {
		super(length);
		this.length = length;
	}
	
	public boolean isFull() {
		if(capacity.size() == trackSize) {
			isFull = true;
		}
		else if(capacity.size() < trackSize) {
			isFull = false;
		}
		return isFull;
	}
	
	public String getStatus() {
		String status = null;
		if (capacity != null) {
			status = segmentName + " " + capacity.toString();
		} else {
			status = segmentName;
		}
		return status;
	}
}
