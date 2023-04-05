package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.lang.Math;

import org.insa.graphs.algorithm.utils.BinaryHeap;
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
            for (Arc successor : n.getSuccessors()){
                int successorId = successor.getDestination().getId();
                if (!labels.get(successorId).isMarked()){
                    double cost = Math.min(labels.get(successorId).getCurrentCost(), labels.get(n.getId()).getCost()+successor.getLength());
                    labels.get(successorId).setCurrentCost(cost);
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
