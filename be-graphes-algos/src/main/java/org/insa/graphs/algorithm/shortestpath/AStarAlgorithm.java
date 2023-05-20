package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;

import org.insa.graphs.model.Node;


public class AStarAlgorithm extends DijkstraAlgorithm {

    Node destination;

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
        this.destination=data.getDestination();
    }
    public Label createLabel(Node currentNode, boolean marked, Double currentCost, Arc father){
        return new LabelStar(currentNode, marked,currentCost,father,this.destination);
    } 


}
