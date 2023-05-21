package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;

import org.insa.graphs.model.Node;
import org.insa.graphs.algorithm.AbstractInputData.Mode;


public class AStarAlgorithm extends DijkstraAlgorithm {

    Node destination;
    Mode m;
    int maximumSpeed;

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
        this.destination=data.getDestination();
        this.m=data.getMode();
        this.maximumSpeed = data.getGraph().getGraphInformation().hasMaximumSpeed() ? data.getGraph().getGraphInformation().getMaximumSpeed() : 180;
    }

    public Label createLabel(Node currentNode, boolean marked, Double currentCost, Arc father){
        return new LabelStar(currentNode, marked,currentCost,father,this.destination,this.m,maximumSpeed);
    } 
}


