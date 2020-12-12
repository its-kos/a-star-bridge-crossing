package com.aueb.assignment;

public class Heuristic {

    private int heuristicNum;

    public Heuristic(int heuristicNum){ this.heuristicNum = heuristicNum; }

    public int calculate(State child){
        switch (heuristicNum){
            case 1:
                return child.peopleSide(child.getRightSide());
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 4;
        }
    }
}
