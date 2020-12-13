package com.aueb.assignment;
import java.util.ArrayList;
import java.util.HashSet;

public class SpaceSearcher {

    private ArrayList<State> searchFront;
    private HashSet<State> closedSet;
    private int heuristic;
    private int maxDepth;
    private int torchTime;

    SpaceSearcher(int maxDepth, int torchTime, int heuristic) {
        this.searchFront = null;
        this.closedSet = null;
        this.heuristic = heuristic;
        this.torchTime = torchTime;
        this.maxDepth = maxDepth;
    }

    public State aStar(State initialState) {
        System.out.println("A* Start");
        this.searchFront = new ArrayList<>();
        this.searchFront.add(initialState);
        while(!(this.searchFront.size() == 0)) {
            State currentState = this.searchFront.remove(0);

            if(currentState.isTerminal()) { return currentState; }

            //Check if I have reached max depth
            if(currentState.getDepth() > maxDepth){
                System.out.println("Reached max Depth, Quitting...");
                return null;
            }

            //Check if state has exceeded the torch time
            if(currentState.getG() > torchTime){
                System.out.println("Torch extinguished, Quitting...");
                return null;
            }

            //Add all children to the search front
            this.searchFront.addAll(currentState.getChildren(heuristic));
            if(!this.searchFront.isEmpty()) {

                int min = this.searchFront.get(0).GH_sum();
                State minState = this.searchFront.get(0);

                //Find state with min G + H sum
                for (State state : this.searchFront) {
                    if (state.GH_sum() < min){
                         min = state.GH_sum();
                         minState = state;
                    }
                }

                //Remove all states except for the one with the minimum G + H sum
                this.searchFront.clear();
                searchFront.add(minState);
                minState.print();
            }
        }
        return null;
    }


    public State aStarClosedSet(State initialState) {
        System.out.println("A* Start");
        this.searchFront = new ArrayList<>();
        this.closedSet = new HashSet<>();
        this.searchFront.add(initialState);
        while(!(this.searchFront.size() == 0)) {
            State currentState = this.searchFront.remove(0);

            if (closedSet.contains(currentState)) break;
            else this.closedSet.add(currentState);

            if(currentState.isTerminal()) { return currentState; }

            //Check if I have reached max depth
            if(currentState.getDepth() > maxDepth){
                System.out.println("Reached max Depth, Quitting...");
                return null;
            }

            //Check if state has exceeded the torch time
            if(currentState.getG() > torchTime){
                System.out.println("Torch extinguished, Quitting...");
                return null;
            }

            //Add all children to the search front
            this.searchFront.addAll(currentState.getChildren(heuristic));
            if(!this.searchFront.isEmpty()) {

                int min = this.searchFront.get(0).GH_sum();
                State minState = this.searchFront.get(0);

                //Find state with min G + H sum
                for (State state : this.searchFront) {
                    if (state.GH_sum() < min){
                        min = state.GH_sum();
                        minState = state;
                    }
                }

                //Remove all states except for the one with the minimum G + H sum
                this.searchFront.clear();
                searchFront.add(minState);
                minState.print();
            }
        }
        return null;
    }
}
