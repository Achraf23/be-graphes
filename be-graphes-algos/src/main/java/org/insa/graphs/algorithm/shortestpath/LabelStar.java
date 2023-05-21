package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.AbstractInputData.Mode;


import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;


public class LabelStar extends Label{

    private Double totalCost;

    public LabelStar(Node currentNode, boolean marked, Double currentCost, Arc father,Node destination,Mode m,int maximumSpeed) {
        //double cost =getCost(destination);
        super(currentNode,marked,currentCost,father);

        if(m==Mode.LENGTH)
        totalCost=this.getCurrentNode().getPoint().distanceTo(destination.getPoint());
        else
        totalCost=this.getCurrentNode().getPoint().distanceTo(destination.getPoint()) / maximumSpeed;

    }
    
    @Override
    public Double getTotalCost(){
        return getCurrentCost()+this.totalCost;
    }
    
}
