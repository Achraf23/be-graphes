package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;


public class LabelStar extends Label{

    public LabelStar(Node currentNode, boolean marked, Double currentCost, Arc father) {
        super(currentNode,marked,currentCost,father);
    }
    

    public int compareTo(Label other) {
        return Double.compare(getCost(), other.getCost());
    }

    public Double getTotalCost(Node destinationNode){

        
        return getCurrentCost();
    }
}
