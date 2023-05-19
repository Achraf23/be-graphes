package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import org.insa.graphs.model.Arc;
import java.util.Collections;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Path;

import org.insa.graphs.algorithm.utils.BinaryHeap;

import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.Node;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public long countNodeReached;

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
        this.countNodeReached=0;
    }

    void incrementNodeReached(){this.countNodeReached++;}



    public Label createLabel(Node currentNode, boolean marked, Double currentCost, Arc father){
        return new Label(currentNode, marked,currentCost,father);
    }       

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
        ArrayList<Label> labels = new ArrayList<>();
        BinaryHeap<Label> binaryHeap = new BinaryHeap<>();
        for (Node n : data.getGraph().getNodes()){
            Label labelNode;
            if (n.equals(data.getOrigin())){
                labelNode = createLabel(n, false, 0.0, null);
                binaryHeap.insert(labelNode); 
            }
            else
                labelNode = createLabel(n, false, Double.POSITIVE_INFINITY, null);
            
            labels.add(labelNode);
        }


        // mn*log n
        while (!binaryHeap.isEmpty() && !labels.get(data.getDestination().getId()).isMarked()){
            //boucle while en n

            Label labelNode = binaryHeap.deleteMin();
            labelNode.setMarked(true);
            notifyNodeMarked(labelNode.getCurrentNode());

            //Vérification de l'augmentation croissante des coûts des labels marqués
            //System.out.println("coût label marqué: "+labelNode.getCost());         
            
            for (Arc arc : labelNode.getCurrentNode().getSuccessors()){
                //boucle for en n

                if (data.isAllowed(arc)){
                    int successorId = arc.getDestination().getId();
                    if (!labels.get(successorId).isMarked()){
                        double newCost = labelNode.getCurrentCost()+data.getCost(arc); 
                        if (labels.get(successorId).getCurrentCost() > newCost){
                            try {
                                //log n
                                binaryHeap.remove(labels.get(successorId)); 
                            }
                            catch (ElementNotFoundException e){
                                notifyNodeReached(arc.getDestination());
                                incrementNodeReached();
                            }
                            labels.get(successorId).setCurrentCost(newCost);
                            binaryHeap.insert(labels.get(successorId));
                            labels.get(arc.getDestination().getId()).setFather(arc);
                            
                        }
                        
                    }
                }
                
            }

        }

        ShortestPathSolution solution = null;

        if(labels.get(data.getDestination().getId()).getFather()==null)
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        else{

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
