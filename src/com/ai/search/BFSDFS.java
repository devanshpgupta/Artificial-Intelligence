package com.ai.search;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTextArea;


public class BFSDFS {


	protected String label;                  
	protected Object state;                  
	protected Object oper;                   
	protected Vector links;                  
	protected int depth;                     
	protected boolean expanded;              
	protected boolean tested;                
	protected float cost = 0;                
	private static JTextArea traceTextArea;  
	public static final int FRONT = 0;
	public static final int BACK = 1;
	public static final int INSERT = 2;

	public boolean leaf() {
		return (links.size() == 0);
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public void setOperator(Object oper) {
		this.oper = oper;
	}

	public void setExpanded() {
		expanded = true;
	}

	public void setExpanded(boolean state) {
		expanded = state;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setTested() {
		tested = true;
	}

	public void setTested(boolean state) {
		tested = state;
	}

	public boolean isTested() {
		return tested;
	}

	static public void setDisplay(JTextArea textArea) {
		traceTextArea = textArea;
	}

	public Object getState() {
		return state;
	}

	public void reset() {
		depth = 0;
		expanded = false;
		tested = false;
	}

	public void trace() {
		String indent = new String();

		for (int i = 0; i < depth; i++) {
			indent += "  ";
		}
		traceTextArea.append(indent + "Searching " + depth + ": " + label + " with state = " + state + "\n");
	}

	public void expand(Vector queue, int position) {
		setExpanded();
		for (int j = 0; j < links.size(); j++) {
			BFSDFS nextNode = null;
			if (!nextNode.tested) {
				nextNode.setTested(true);
				nextNode.setDepth(depth + 1);
				switch (position) {
				case FRONT :
					queue.insertElementAt(nextNode, 0);
					break;
				case BACK :
					queue.addElement(nextNode);
					break;
				case INSERT :
					boolean inserted = false;
					float nextCost = nextNode.cost;

					for (int k = 0; k < queue.size(); k++) { 
					}
					if (!inserted) {
						queue.addElement(nextNode);
					}
					break;
				}
			}
		}
	}

	public State exec(State initialState) {
		if (initialState.isGoal()) {
			return initialState;
		}
		Queue<State> frontier = new LinkedList<State>();	// FIFO queue
		Set<State> explored = new HashSet<State>();
		frontier.add(initialState);
		while (true) {
			if (frontier.isEmpty()) {
				return null;	// failure
			}
			State state = frontier.poll();
			explored.add(state);
			List<State> successors = state.generateSuccessors();
			for (State child : successors) {
				if (!explored.contains(child) || !frontier.contains(child)) {
					if (child.isGoal()) {
						return child;
					}
					frontier.add(child);
				}
			}
		}
	}

	public State execDFS(State initialState) {
		int limit = 20;
		return recursiveDLS(initialState, limit);
	}

	private State recursiveDLS(State state, int limit) {
		if (state.isGoal()) {
			return state;
		} else if (limit == 0) {
			return null;
		} else {
			List<State> successors = state.generateSuccessors();
			for (State child : successors) {
				State result = recursiveDLS(child, limit - 1);
				if (null != result) {
					return result;
				}
			}
			return null;
		}
	}
}

