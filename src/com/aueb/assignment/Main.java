package com.aueb.assignment;

public class Main {
    public static void main(String[] args) {

        int [] leftSide = new int[]{1, 1, 1, 0};
        int [] rightSide = new int[]{0, 0, 0, 1};
        State initialState = new State(4, leftSide, rightSide);
        initialState.print();
        System.out.println("End of Initial State Printing");
        SpaceSearcher spaceSearcher = new SpaceSearcher();
        State terminalState = null;

        //A star without closed set
        long start = System.currentTimeMillis();
        terminalState = spaceSearcher.aStar(initialState, 1);
        long end = System.currentTimeMillis();

        if(terminalState == null) {
            System.out.println("Could not find solution");
        }else {
            terminalState.print();
        }
        System.out.println("A star search time: " + (double)(end - start) / 1000 + " sec.");

        //A star with closed set
        start = System.currentTimeMillis();
        terminalState = spaceSearcher.aStarClosedSet(initialState, 1);
        end = System.currentTimeMillis();

        if(terminalState == null) {
            System.out.println("Could not find solution");
        }else {
            terminalState.print();
        }
        System.out.println("A star search time: " + (double)(end - start) / 1000 + " sec.");
    }
}
