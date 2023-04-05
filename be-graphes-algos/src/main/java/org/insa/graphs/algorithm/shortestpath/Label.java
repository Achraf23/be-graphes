package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class Label{

    private Node currentNode;
    private boolean marked;
    private Double smallestCost;
    private Arc father;

    public Label(Node currentNode, boolean marked, Double smallestCost, Arc father) {
        this.currentNode = currentNode;
        this.marked = marked;
        this.smallestCost = smallestCost;
        this.father = father;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public boolean isMarked() {
        return marked;
    }

    public Double getSmallestCost() {
        return smallestCost;
    }

    public Arc getFather() {
        return father;
    }
}