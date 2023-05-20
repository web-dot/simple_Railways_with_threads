package com.railway.trains;

import java.util.LinkedList;

import com.railway.other_components.Segment;

public class LocalTrain extends Train  {
	
	//local train constructor that takes in railway linked list
	// to pass to abstract train class and sets train speed to 10.
	public LocalTrain(LinkedList<Segment> railway) {
		super(railway);
		trainSpeed = 100;
			
	}
	
	
	
}