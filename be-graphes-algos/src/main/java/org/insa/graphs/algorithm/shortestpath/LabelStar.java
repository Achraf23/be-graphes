package org.insa.graphs.algorithm.shortestpath;

import javax.print.attribute.standard.Destination;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;


public class LabelStar extends Label{

    private Double totalCost;
    private Node destination;

    public LabelStar(Node currentNode, boolean marked, Double currentCost, Arc father,Node destination) {
        //double cost =getCost(destination);
        super(currentNode,marked,currentCost,father);
        this.totalCost=this.getCurrentNode().getPoint().distanceTo(destination.getPoint());
        this.destination = destination;
    }


    @Override 
    public int compareTo(Label other) {
        if(Double.compare(this.getTotalCost(), other.getTotalCost()) != 0){
            return Double.compare(this.getTotalCost(), other.getTotalCost());
        } 
    
        return Double.compare(this.totalCost, other.getCurrentNode().getPoint().distanceTo(this.destination.getPoint()));  
    }
   
    public Double getCost(){
        return this.totalCost;
    } 

    @Override
    public Double getTotalCost(){

        return getCurrentCost()+this.totalCost;
    }
    
}
