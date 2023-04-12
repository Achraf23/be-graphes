package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import org.insa.graphs.model.Arc;
import java.util.Collections;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Path;

import java.lang.Math;

import org.insa.graphs.algorithm.utils.BinaryHeap;

import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
        ArrayList<Label> labels = new ArrayList<>();
        BinaryHeap<Label> binaryHeap = new BinaryHeap<>();
        for (Node n : data.getGraph().getNodes()){
            Label labelNode;
            if (n.equals(data.getOrigin())){
                labelNode = new Label(n, false, 0.0, null);
                binaryHeap.insert(labelNode);
            }
            else{
                labelNode = new Label(n, false, Double.POSITIVE_INFINITY, null);
            }
            labels.add(labelNode);
        }

        while (!binaryHeap.isEmpty() && !labels.get(data.getDestination().getId()).isMarked()){
            Label labelNode = binaryHeap.deleteMin();
            labelNode.setMarked(true);
            
            
            for (Arc arc : labelNode.getCurrentNode().getSuccessors()){
                int successorId = arc.getDestination().getId();
                if (!labels.get(successorId).isMarked()){
                    double newCost = labelNode.getCost()+arc.getLength(); 
                    if (labels.get(successorId).getCurrentCost() > newCost){
                        try {
                           binaryHeap.remove(labels.get(successorId)); 
                        }
                        catch (ElementNotFoundException e){}
                        labels.get(successorId).setCurrentCost(newCost);
                        binaryHeap.insert(labels.get(successorId));
                        labels.get(arc.getDestination().getId()).setFather(arc);
                        
                    }
                    
                }
            }

        }

        ShortestPathSolution solution = null;

        if(labels.get(data.getDestination().getId()).getFather()==null){
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }else{
            // The destination has been found, notify the observers.
            notifyDestinationReached(data.getDestination());

            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc=labels.get(data.getDestination().getId()).getFather();

            while(arc!=null){
                arcs.add(arc);
                arc=labels.get(arc.getOrigin().getId()).getFather();
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), arcs));


        }

        return solution;
    }


    

}
