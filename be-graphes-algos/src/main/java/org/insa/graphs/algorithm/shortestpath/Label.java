package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label implements Comparable<Label>{

    private Node currentNode;
    private boolean marked;
    private Double currentCost;
    private Arc father;

    public Label(Node currentNode, boolean marked, Double currentCost, Arc father) {
        this.currentNode = currentNode;
        this.marked = marked;
        this.currentCost = currentCost;
        this.father = father;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked){
        this.marked = marked;
    }

    public Double getCurrentCost() {
        return this.currentCost;
    }

    public void setCurrentCost(double cost){
        this.currentCost = cost;
    }

    public Arc getFather() {
        return father;
    }

    public void setFather(Arc father){
        this.father = father;
    }

    public Double getTotalCost() {
        return getCurrentCost();
    }

    @Override
    public int compareTo(Label other) {
        // return Double.compare(getTotalCost(), other.getTotalCost());
        System.out.println(this.getCurrentNode().getId());
        System.out.println(other.getCurrentNode().getId());
        return Double.compare(this.getTotalCost(), other.getTotalCost());
        
    }

    public String toString(){
        return this.currentCost+"\n";
    }
}