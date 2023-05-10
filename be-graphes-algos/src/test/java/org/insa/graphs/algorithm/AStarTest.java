package org;

import org.insa.graphs.algorithm.DijkstraTest;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.io.PathReader;
import org.insa.graphs.model.io.GraphReader;

import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.Graph;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

import org.insa.graphs.model.Path;


public class AStarTest extends DijkstraTest{
    final static String mapBelgium = "C:/Users/tgben/OneDrive/Bureau/3micS2/Maps/belgium.mapgr";
    final static String path="C:/Users/tgben/OneDrive/Bureau/3micS2/Paths/path_be_254725_600594.path";

    
    
    public void compareDijkstraAStar() throws IOException{

        GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
        final Graph graphBelgium = reader.read();

        PathReader readerPath = new BinaryPathReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(path))));
        
        Path pathTest = readerPath.readPath(graphBelgium);

        ShortestPathData data=new ShortestPathData(graphBelgium, pathTest.getOrigin(), pathTest.getDestination(), allFilterInspectors.get(0));
        
        DijkstraAlgorithm algo=new DijkstraAlgorithm(data);
        AStarAlgorithm algo2 = new AStarAlgorithm(data);
        
        //test Dijkstra time
        long startTime = System.nanoTime();
        algo.run();    
        // ... the code being measured ...    
        long estimatedTimeDijkstra = System.nanoTime() - startTime;

        //test AStar Time
        startTime = System.nanoTime();
        algo2.run();
        long estimatedTimeAStar = System.nanoTime() - startTime;

      

        assertTrue(estimatedTimeAStar < estimatedTimeDijkstra);
       /*  Duration d=algo.run().getSolvingTime();
        Duration d2=algo2.run().getSolvingTime(); */
        
    }


}
