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
        while (!isAllNodesMarked(labels)){
            Label labelNode = binaryHeap.deleteMin();
            labelNode.setMarked(true);
            
            int indexNode = labels.indexOf(labelNode);
            for (Arc arc : this.data.getGraph().getNodes().get(indexNode).getSuccessors()){
                int successorId = arc.getDestination().getId();
                if (!labels.get(successorId).isMarked()){
                    double newCost = labels.get(indexNode).getCost()+arc.getLength(); 
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
