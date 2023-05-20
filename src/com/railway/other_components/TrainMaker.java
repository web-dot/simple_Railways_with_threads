package com.railway.other_components;

import java.util.LinkedList;
import java.util.Random;

import com.railway.trains.ExpressTrain;
import com.railway.trains.LocalTrain;
import com.railway.trains.Train;

//class that creates train objects
public class TrainMaker implements Runnable {
	private LinkedList<Segment> railway;

	public TrainMaker(LinkedList<Segment> railway) {
		this.railway = railway;

	}

	/**
	 * Method that makes a local train or an express train randomly
	 *
	 * @return Train
	 */
	public Train makeTrain() {

		Train newTrain = null;

		Random random = new Random();
		int randomTrain = random.nextInt(100);
		if (randomTrain % 2 == 0) {

			newTrain = new ExpressTrain(railway);

		} else {

			newTrain = new LocalTrain(railway);

		}

		return newTrain;
	}


	/**
	 * Run method that makes trains indefinitely. Sleeps for random time between 0
	 * and 3 seconds to give delay between each train creation. Each train object is
	 * set to a new thread.
	 * 
	 */
	@Override
	public void run() {
		Random random = new Random();
		int randomInt = random.nextInt(3000);
		while (true) {
			try {
				Thread.sleep(randomInt);
				Train train = makeTrain();
				Thread t = new Thread(train);
				t.start();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
