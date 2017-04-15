package com.ai.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("==== Missionaries and Cannibals Problem ====");
		System.out.println("Choose the search method: ");
		System.out.println("\t 1. Breadth-first search");
		System.out.println("\t 2. Depth-limited search");
		System.out.print("\nType your choice and press ENTER: ");

		String optionStr = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			optionStr = in.readLine();
		} catch (IOException e) {
			System.out.println("[ERROR] Fail to read the typed option.");
			e.printStackTrace();
		}

		int option = Integer.parseInt(optionStr);
		State initialState = new State (3, 3, Position.LEFT, 0, 0);
		switch(option) {
		case 1:
			executeBFS(initialState);
			break;
		case 2:
			executeDLS(initialState);
			break;
		default:
			System.out.println("[ERROR] Invalid search option.");
		}
	}

	private static void executeBFS(State initialState) {
		BFSDFS search = new BFSDFS();
		State solution = search.exec(initialState);
		printSolution(solution);
	}

	private static void executeDLS(State initialState) {
		BFSDFS search = new BFSDFS();
		State solution = search.execDFS(initialState);
		printSolution(solution);
	}

	private static void printSolution(State solution) {
		if (null == solution) {
			System.out.print("\n No solution found.");
		} else {
			List<State> path = new ArrayList<State>();
			State state = solution;
			while(null!=state) {
				path.add(state);
				state = state.getParentState();
			}

			int depth = path.size() - 1;
			for (int i = depth; i >= 0; i--) {
				state = path.get(i);
				if (state.isGoal()) {
					System.out.print("\n" + state.toString());
				} else {
					System.out.print("\n" + state.toString());
				}
			}
			System.out.println("\nDepth: " + depth);
		}
	}
}
