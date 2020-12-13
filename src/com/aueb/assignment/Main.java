package com.aueb.assignment;

public class Main {
    public static void main(String[] args) {

        int [] leftSide = new int[]{1, 0, 0, 0, 0};
        int [] speeds = new int[]{1, 3, 6, 8, 12};
        int [] rightSide = new int[]{0, 1, 1, 1, 1};
        State initialState = new State(speeds.length, leftSide, rightSide, speeds);
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
    }
}
