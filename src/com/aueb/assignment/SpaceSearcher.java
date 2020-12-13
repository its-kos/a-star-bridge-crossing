package com.aueb.assignment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SpaceSearcher {

    private ArrayList<State> searchFront;
    private int maxTorchTime =100;
    private int currentTime;
    private HashSet<State> closedSet;

    SpaceSearcher() {
        this.currentTime = 0;
        this.searchFront = null;
        this.closedSet = null;
    }

    public State aStar(State initialState, int heuristicNum) {
        System.out.println("A* Start");
        this.searchFront = new ArrayList<>();
        this.searchFront.add(initialState);
        while(!(this.searchFront.size() == 0)) {
            State currentState = this.searchFront.remove(0);
            State minState=null;
            currentState.print();
            if(currentTime>maxTorchTime){
                System.out.println("No Torch Time");
                return currentState;
            }
            if(currentState.isTerminal()) { return currentState; }
            
            this.searchFront.addAll(currentState.getChildren(heuristicNum));//get all children
            if(!this.searchFront.isEmpty()) {//not empty
                int min = this.searchFront.get(0).GH_sum();
                int index=0;
                
                for (State state : this.searchFront) {
                    if (state.GH_sum() < min){
                     min = state.GH_sum();
                     minState=state;
                    }
                }
                //removes everything except the minimum
                for (int i = 0; i < this.searchFront.size(); i++) {
                    if(this.searchFront.get(i)!=minState){
                        this.searchFront.remove(i);
                    }
                }
                

                
            }//finds min
        }
        return null;
    }
}
