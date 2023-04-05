package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;

import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        
        ArrayList<Label> labels = new ArrayList<>();
        BinaryHeap<Node> binaryHeap = new BinaryHeap<>();
        for (Node n : this.data.getGraph().getNodes()){
            Label labelNode;
            if (n.equals(this.data.getGraph().getNodes().get(0))){
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
            for (Arc arc : n.getSuccessors()){
                int successorId = arc.getDestination().getId();
                if (!labels.get(successorId).isMarked()){
                    double newCost = labels.get(n.getId()).getCost()+arc.getLength();
                    if (labels.get(successorId).getCurrentCost() > newCost){
                        labels.get(successorId).setCurrentCost(newCost);
                        try {
                           binaryHeap.remove(arc.getDestination()); 
                        }
                        catch (ElementNotFoundException e){}
                        binaryHeap.insert(arc.getDestination());
                        labels.get(arc.getDestination().getId()).setFather(arc);
                    }
                    
                }
            }

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
