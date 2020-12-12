package com.aueb.assignment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SpaceSearcher {

    private ArrayList<State> searchFront;
    private HashSet<State> closedSet;

    SpaceSearcher() {
        this.searchFront = null;
        this.closedSet = null;
    }

    public State aStar(State initialState, int heuristicNum) {
        System.out.println("A* Start");
        this.searchFront = new ArrayList<>();
        this.searchFront.add(initialState);
        while(!(this.searchFront.size() == 0)) {
            State currentState = this.searchFront.remove(0);
            currentState.print();
            if(currentState.isTerminal()) { return currentState; }
            this.searchFront.addAll(currentState.getChildren(heuristicNum));
            if(!this.searchFront.isEmpty()) {
                int min = this.searchFront.get(0).GH_sum();
                for (State state : this.searchFront) {
                    if (state.GH_sum() < min) min = state.GH_sum();
                }
            }
        }
        return null;
    }
}
