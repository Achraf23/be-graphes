package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import org.insa.graphs.model.Arc;
import java.util.Collections;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Path;

import java.lang.Math;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
        ArrayList<Label> labels = new ArrayList<>();
        
        BinaryHeap<Node> binaryHeap = new BinaryHeap<>();
        for (Node n : data.getGraph().getNodes()){
            Label labelNode;
            if (n.equals(data.getOrigin())){
                labelNode = new Label(n, false, 0.0, null);
                binaryHeap.insert(n);
            }
            else{
                labelNode = new Label(n, false, Double.POSITIVE_INFINITY, null);
            }
            labels.add(labelNode);
        }

        while (!isAllNodesMarked(labels)){
            Node n = binaryHeap.deleteMin();
            labels.get(n.getId()).setMarked(true);
            for (Arc successor : n.getSuccessors()){
                int successorId = successor.getDestination().getId();
                if (!labels.get(successorId).isMarked()){
                    double cost = Math.min(labels.get(successorId).getCurrentCost(), labels.get(n.getId()).getCost()+successor.getLength());
                    labels.get(successorId).setCurrentCost(cost);
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


    private Boolean isAllNodesMarked(ArrayList<Label> labels){
        for (Label l : labels){
            if (!l.isMarked()){
                return false;
            }
        }
        return true;
    }

}
