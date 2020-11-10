package com.aueb.assignment;

import java.util.ArrayList;

public class Heuristic {

    private int heuristicNum;

    public Heuristic(int heuristicNum){
        this.heuristicNum = heuristicNum;
    }

    public int calculate(State child){
        int h = -1;
        switch (heuristicNum){
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 3;
            default:
                return 4;
        }
    }
}
