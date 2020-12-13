package com.aueb.assignment;
import java.util.ArrayList;

public class State {
    private int membersNum;
    private int hScore;
    private int gCost;
    private int depth;
    public int []  walkTimes;
    private int [] leftSide;
    private int [] rightSide;
    private boolean isTorchLeft= false; //False if the torch is on the right side, true if its on the left side.
    private Heuristic heuristic;

    public State() {}

   
    public State(int membersNum, int[] leftSide, int[] rightSide, int[]  walkTimes) {
        this.membersNum = membersNum;
        this.gCost = 0;
        this.isTorchLeft = false;
        this.depth = 0;
        this.rightSide = new int[membersNum];
        this.leftSide = new int[membersNum];
        this. walkTimes= new int[membersNum];
        for(int i = 0; i < membersNum; i++){
            this.rightSide[i] = rightSide[i];
            this.leftSide[i] = leftSide[i];
            this. walkTimes[i] =  walkTimes[i];
        }
    }
    

    /***
     * For copying states (Used in the getChildren method to make state copies).
     */
    public State(int[]leftSide, int[]rightSide, boolean isTorchLeft, int gCost, int depth, int[]  walkTimes) {
        this.membersNum = rightSide.length;
        this.isTorchLeft = isTorchLeft;
        this.gCost += gCost;
        this.depth = depth + 1;
        this.rightSide = new int[membersNum];
        this.leftSide = new int[membersNum];
        this. walkTimes= new int[membersNum];
        for(int i = 0; i < membersNum; i++){
            this.rightSide[i] = rightSide[i];
            this.leftSide[i] = leftSide[i];
            this. walkTimes[i] =  walkTimes[i];
        }
    }

    /***
     * Getters and setters
     */
    public int getMembersNum() { return membersNum; }

    public int getH() { return hScore; }

    public int getG() { return gCost; }

    public int GH_sum() { return gCost + hScore; }

    public int[] getLeftSide() { return leftSide; }

    public int[] getRightSide() { return rightSide; }

    public int[] getwalkTimes() { return  walkTimes; }

    public boolean getisTorchLeft(){ return isTorchLeft; }

    public void setMembersNum(int membersNum) { this.membersNum = membersNum; }

    public void setH(int h) { this.hScore = h; }

    public void setG(int g) { this.gCost = g; }

    public void setLeftSide(int[] leftSide) { this.leftSide = leftSide; }

    public void setRightSide(int[] rightSide) { this.rightSide = rightSide; }

    //public void setMember walkTimes(int[] member walkTimes) { this. walkTimes = member walkTimes; }

    public void setisTorchLeft(boolean state){ isTorchLeft = state; }

    public int getDepth() { return depth; }

    /***Method to move family members across the river*/
    public boolean moveLeft(int firstMember, int secondMember) {
        gCost += Math.max( walkTimes[firstMember],  walkTimes[secondMember]);
        leftSide[firstMember] = 1;
        leftSide[secondMember] = 1;
        rightSide[firstMember] = 0;
        rightSide[secondMember] = 0;
        isTorchLeft = true;
        return true;
    }
    /***Method to move family members across the river*/
    public boolean moveRight(int memberIndex) {
        gCost +=  walkTimes[memberIndex];
        leftSide[memberIndex] = 0;
        rightSide[memberIndex] = 1;
        isTorchLeft = false;
        return true;
    }

    /***Calculates how many people are at the side provided*/
    public int peopleSide(int[] side){
        int count = 0;
        for (int i = 0; i < side.length; i++) {
            if(side[i] == 1){
                count++;
            }
        }
        return count;
    }

    /***
     * Generates the children-states of the this state
     * Each child-state is created by moving 2 family members to the left side or
     * one member to the right side (Depending on where the torch is).
     */
    public ArrayList<State> getChildren(int heuristicNum) {
        ArrayList<State> children = new ArrayList<State>();
        ArrayList<Integer> indexes = new ArrayList<>();
        heuristic = new Heuristic(heuristicNum);

        //If torch is on the right side we move 2 people left
        if(!isTorchLeft) {
            indexes.clear();

            //Find the indexes of all the people still on the right side
            for (int i = 0; i < membersNum; i++) {
              if(rightSide[i] == 1){
                  indexes.add(i);
              }
           }

            //Create one child for every combination of people that can cross
            while (!indexes.isEmpty()) {
                int currentPerson = indexes.remove(0);
                for (int element : indexes) {
                    State child = new State(leftSide, rightSide, isTorchLeft, this.gCost, this.depth,  walkTimes);
                    if (child.moveLeft(currentPerson, element)) {
                        child.hScore = heuristic.calculate(child);
                        children.add(child);
                    }
                }
            }
        //If torch is on the left side we move 1 person right
        }else {
            indexes.clear();
           for (int i = 0; i < membersNum; i++) {
              if(leftSide[i] == 1){
                indexes.add(i);
              }
           }
            for (int elem : indexes) {
                State child = new State(leftSide, rightSide, isTorchLeft, this.gCost, this.depth, walkTimes);
                if (child.moveRight(elem)) {
                    child.hScore = heuristic.calculate(child);
                    children.add(child);
                }
            }
        }
        return children;
    }

    /***
     * Checks whether a state is terminal
     * If all the elements in the left side array are in 1s, all family members are on the left side.
     */
    public boolean isTerminal() {
        for (int i = 0; i < membersNum; i++) {
            if (leftSide[i] == 0) return false;
        }
        return true;
    }

    /***
     * Prints the state of all arrays.
     * This displays where each family member is at any given point.
     * Each family member can only be at 1 point (Either on the left or the right side).
     */
    public void print() {
        System.out.println("------------------------------------");
        System.out.println("Left Side: ");
        for(int i = 0; i < this.membersNum; i++) {
            System.out.print("Member #" + i + ": " + leftSide[i]);
            System.out.println();
        }
        System.out.println("------------------------------------");
        System.out.println("Right Side: ");
        for(int i = 0; i < this.membersNum; i++) {
            System.out.print("Member #" + i + ": " + rightSide[i]);
            System.out.println();
        }
        System.out.println("------------------------------------");
    }
}
