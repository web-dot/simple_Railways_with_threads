package com.railway.trains;

import java.util.LinkedList;

import com.railway.other_components.Segment;

public class ExpressTrain extends Train {
	//Express train constructor that takes in railway linked list
		// to pass to abstract train class and sets train speed to 500.
		public ExpressTrain(LinkedList<Segment> railway) {
			super(railway);
			trainSpeed = 500;	
				
		}
}
