package com.aueb.assignment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SpaceSearcher {

    private ArrayList<State> states;
    private HashSet<State> closedSet;

    SpaceSearcher() {
        this.states = null;
        this.closedSet = null;
    }

    public State aStar(State initialState, int heuristicNum) {
        System.out.println("A* Start");
        this.states = new ArrayList<>();
        this.states.add(initialState);
        while(this.states.size() > 0) {
            State currentState = this.states.remove(0);
            currentState.print();
            if(currentState.isTerminal()) {
                return currentState;
            }
            this.states.addAll(currentState.getChildren(heuristicNum));
            if(!this.states.isEmpty()){
            int min= this.states.get(0).GH_sum();
            for(State state: this.states){
                if(state.GH_sum()<min){ min=state.GH_sum(); }
            }
            //Minimum Cost Found
            
        }
            //Collections.sort(this.states);
        }
        return null;
    }

    public State aStarClosedSet(State initialState, int heuristicNum) {
        this.states = new ArrayList<State>();
        this.closedSet = new HashSet<State>();
        this.states.add(initialState);
        while(this.states.size() > 0) {
            State currentState = this.states.remove(0);
            if(currentState.isTerminal()) {
                return currentState;
            }
            if(!closedSet.contains(currentState)) {
                this.closedSet.add(currentState);
                this.states.addAll(currentState.getChildren(heuristicNum));
            }
           //Collections.sort(this.states);
        }
        return null;
    }
}
