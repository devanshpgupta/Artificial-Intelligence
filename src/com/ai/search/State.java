package com.ai.search;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

enum Position {RIGHT, LEFT}

public class State {

	private static final Object GENETIC_SEARCH = null;
	private static final Object BEST_FIRST = null;
	private int cannibalLeft;
	private int missionaryLeft;
	private int cannibalRight;
	private int missionaryRight;
	private Position boat;

	private State parentState;


	public double binaryToInteger(String binCode) {
		double temp;
		double value = 0;
		int inLength = binCode.length();
		char bit;

		for (int i = 0; i < inLength; i++) {
			bit = binCode.charAt(i);
			if (bit == '1') {
				value += Math.pow((double) 2, (double) (inLength - 1 - i));
			}
		}
		return value;
	}

	public double computeFitness() {

		boolean fitnessComputed = false;
		double fitness = 0;
		if (fitnessComputed) {
			return fitness;
		}
		int chromosomeLength = 0;
		int size = chromosomeLength;
		double sum = 0.0;

		for (int i = 0; i < size; i++) {
			String chromosome = null;
			if (((String) chromosome).charAt(i) == '0') {
				sum = sum + 1.0;
			}
		}
		fitness = sum; 
		fitnessComputed = true;
		return fitness;
	}
	
	  void GeneticSearchRadioButtonMenuItem_actionPerformed(ActionEvent e) {
		    Object searchAlgorithm = GENETIC_SEARCH;
		    setActiveControls(); 
		    Object geneticSearch = null;
			if (geneticSearch == null) {
		    }
		  }


		  private void setActiveControls() {
		    Object searchAlgorithm = null;
			if (searchAlgorithm == GENETIC_SEARCH) {
		    } else {
		      if (searchAlgorithm == BEST_FIRST) {
		      } else {
		      }
		    }
		  }	


	public State(int cannibalLeft, int missionaryLeft, Position boat,
			int cannibalRight, int missionaryRight) {
		this.cannibalLeft = cannibalLeft;
		this.missionaryLeft = missionaryLeft;
		this.boat = boat;
		this.cannibalRight = cannibalRight;
		this.missionaryRight = missionaryRight;
	}

	public boolean isGoal() {
		return cannibalLeft == 0 && missionaryLeft == 0;
	}

	public boolean isValid() {
		if (missionaryLeft >= 0 && missionaryRight >= 0 && cannibalLeft >= 0 && cannibalRight >= 0
				&& (missionaryLeft == 0 || missionaryLeft >= cannibalLeft)
				&& (missionaryRight == 0 || missionaryRight >= cannibalRight)) {
			return true;
		}
		return false;
	}

	public List<State> generateSuccessors() {
		List<State> successors = new ArrayList<State>();
		if (boat == Position.LEFT) {
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 2, Position.RIGHT,
					cannibalRight, missionaryRight + 2)); 
			testAndAdd(successors, new State(cannibalLeft - 2, missionaryLeft, Position.RIGHT,
					cannibalRight + 2, missionaryRight)); 
			testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft - 1, Position.RIGHT,
					cannibalRight + 1, missionaryRight + 1));
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft - 1, Position.RIGHT,
					cannibalRight, missionaryRight + 1)); 
			testAndAdd(successors, new State(cannibalLeft - 1, missionaryLeft, Position.RIGHT,
					cannibalRight + 1, missionaryRight)); 
		} else {
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 2, Position.LEFT,
					cannibalRight, missionaryRight - 2)); 
			testAndAdd(successors, new State(cannibalLeft + 2, missionaryLeft, Position.LEFT,
					cannibalRight - 2, missionaryRight)); 
			testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft + 1, Position.LEFT,
					cannibalRight - 1, missionaryRight - 1));
			testAndAdd(successors, new State(cannibalLeft, missionaryLeft + 1, Position.LEFT,
					cannibalRight, missionaryRight - 1)); 
			testAndAdd(successors, new State(cannibalLeft + 1, missionaryLeft, Position.LEFT,
					cannibalRight - 1, missionaryRight));
		}
		return successors;
	}

	private void testAndAdd(List<State> successors, State newState) {
		if (newState.isValid()) {
			newState.setParentState(this);
			successors.add(newState);
		}
	}


	public State getParentState() {
		return parentState;
	}

	public void setParentState(State parentState) {
		this.parentState = parentState;
	}

	@Override
	public String toString() {
		if (boat == Position.LEFT) {
			return "(" + cannibalLeft + "," + missionaryLeft + ",Boat on Left Side,"
					+ cannibalRight + "," + missionaryRight + ")";
		} else {
			return "(" + cannibalLeft + "," + missionaryLeft + ",Boat on Right Side,"
					+ cannibalRight + "," + missionaryRight + ")";
		}
	}


}
