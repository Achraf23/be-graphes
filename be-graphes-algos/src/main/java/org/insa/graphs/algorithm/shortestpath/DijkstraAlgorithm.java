package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import org.insa.graphs.model.Arc;
import java.util.Collections;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.model.Path;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
        ArrayList<Label> labels = new ArrayList<>();
        
        
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
