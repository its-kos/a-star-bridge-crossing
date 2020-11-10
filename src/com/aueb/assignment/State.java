package com.aueb.assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class State {

    private int membersNum;
    private int hScore;
    private int gCost;
    private State father = null;
    private int [] memberSpeeds;
    private int [] leftSide;
    private int [] rightSide;
    private boolean torchState = false; //False if the torch is on the right side, true if its on the left side.
    private Heuristic heuristic;

    public State() {}

    /***
     * For initialization of the problem
     * @param membersNum : The number of the family members
     */
    public State(int membersNum) {
        this.membersNum = membersNum;
        this.torchState = false;
        this.memberSpeeds = new int[this.membersNum];
        this.rightSide = new int[this.membersNum];
        this.leftSide = new int[this.membersNum];
        for(int i = 0; i < membersNum; i++){
            this.rightSide[i] = i + 1;
        }
        Arrays.fill(this.leftSide, 0);
    }

    /***
     * For copying states (Used in the getChildren method to make state copies).
     */
    public State(int[]leftSide, int[]rightSide, boolean torchState, int gCost) {
        this.membersNum = leftSide.length;
        this.torchState = torchState;
        int gCost = gCost +
        this.hScore = -1;
        this.rightSide = new int[leftSide.length];
        this.leftSide = new int[leftSide.length];
        for(int i = 0; i < leftSide.length; i++){
            this.rightSide[i] = rightSide[i];
            this.leftSide[i] = leftSide[i];
        }
    }

    /***
     * Getters and setters
     */
    public int getMembersNum() { return membersNum; }

    public int getH() { return hScore; }

    public int getG() { return gCost; }

    public State getFather(){ return father; }

    public int[] getLeftSide() { return leftSide; }

    public int[] getRightSide() { return rightSide; }

    public int[] getMemberSpeeds() { return memberSpeeds; }

    public boolean getTorchState(){ return torchState; }

    public void setMembersNum(int membersNum) { this.membersNum = membersNum; }

    public void setH(int h) { this.hScore = h; }

    public void setG(int g) { this.gCost = g; }

    public void setFather(State father) { this.father = father; }

    public void setLeftSide(int[] leftSide) { this.leftSide = leftSide; }

    public void setRightSide(int[] rightSide) { this.rightSide = rightSide; }

    public void setMemberSpeeds(int[] memberSpeeds) { this.memberSpeeds = memberSpeeds; }

    public void setTorchState(boolean state){ torchState = state; }

    /***Methods to move family members across the river*/
    public boolean moveLeft(int firstMember, int secondMember) {
        leftSide[firstMember] = firstMember;
        leftSide[secondMember] = secondMember;
        rightSide[firstMember] = 0;
        rightSide[secondMember] = 0;
        torchState = true;
        return true;
    }

    public boolean moveRight(int memberIndex) {
        leftSide[memberIndex] = 0;
        rightSide[memberIndex] = memberIndex;
        torchState = false;
        return true;
    }

    /***
     * Generates the children-states of the this state
     * Each child-state is created by moving 2 family members over to the left side or
     * one member to the right side (Depending on where the torch is).
     */
    public ArrayList<State> getChildren(int heuristicNum) {
        ArrayList<State> children = new ArrayList<State>();
        ArrayList<Integer> list = new ArrayList<>();
        heuristic = new Heuristic(heuristicNum);
        State child;

        //If torch is on the right side we move 2 people left
        if(!torchState) {

            list.clear();
            for (int element : rightSide) {
                list.add(element);
            }
            while (!list.isEmpty()) {
                int currentPerson = list.remove(0);
                for (int element : list) {
                    child = new State(leftSide, rightSide, torchState);
                    if (child.moveLeft(currentPerson, element)) {
                        this.hScore = heuristic.calculate(child);
                        child.setFather(this);
                        children.add(child);
                    }
                }
            }
        //If torch is on the left side we move 1 person right
        }else {
            for (int elem : leftSide) {
                child = new State(leftSide, rightSide, torchState);
                if (child.moveRight(elem)) {
                    child.hScore = heuristic.calculate(child);
                    child.setFather(this);
                    children.add(child);
                }
            }
        }
        return children;
    }

    /***
     * Checks whether a state is terminal
     * If all the elements in the left side array are in their positions, all family members are on the left side.
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
