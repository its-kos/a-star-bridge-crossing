package com.aueb.assignment;

public class Main {
    public static void main(String[] args) {

        int [] leftSide = new int[]{0, 0, 0, 0, 0};
        int [] speeds = new int[]{12, 5, 2, 3, 4};
        int [] rightSide = new int[]{1, 1, 1, 1, 1};
        int maxDepth = 10;
        int torchTime = 35;
        int heuristic = 1;

        State initialState = new State(speeds.length, leftSide, rightSide, speeds);
        initialState.print();
        System.out.println("End of Initial State Printing");
        SpaceSearcher spaceSearcher = new SpaceSearcher(maxDepth, torchTime, heuristic);
        State terminalState = null;
        State terminalStateClosed = null;

        /***----------------A star without closed set----------------*/
        long start = System.currentTimeMillis();
        terminalState = spaceSearcher.aStar(initialState);
        long end = System.currentTimeMillis();

        if(terminalState == null) {
            System.out.println("Could not find solution");
        }else {
            System.out.println("----------TERMINAL STATE-----------");
            terminalState.print();
        }
        System.out.println("A star search time: " + (double)(end - start) / 1000 + " sec.");


        /*** -----------------Closed Set-------------------------*/
        long startClosed = System.currentTimeMillis();
        terminalStateClosed = spaceSearcher.aStarClosedSet(initialState);
        long endClosed = System.currentTimeMillis();

        if(terminalState == null) {
            System.out.println("Could not find solution");
        }else {
            System.out.println("----------TERMINAL STATE-----------");
            terminalState.print();
        }
        System.out.println("A star with closed set search time: " + (double)(end - start) / 1000 + " sec.");
    }
}
