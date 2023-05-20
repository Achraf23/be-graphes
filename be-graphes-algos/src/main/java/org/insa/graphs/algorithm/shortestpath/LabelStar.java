package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;


public class LabelStar extends Label {

    Double totalCost;

    public LabelStar(Node currentNode, boolean marked, Double currentCost, Arc father,Node destination) {
        //double cost =getCost(destination);
        super(currentNode,marked,currentCost,father);
        totalCost=this.getCurrentNode().getPoint().distanceTo(destination.getPoint());
    }

   
    @Override
    public Double getTotalCost(){

        return getCurrentCost()+this.totalCost;
    }
    
}
