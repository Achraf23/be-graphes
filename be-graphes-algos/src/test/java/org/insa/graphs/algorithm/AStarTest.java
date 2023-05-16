package org.insa.graphs.algorithm;

import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.io.PathReader;
import org.junit.Test;
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
    final static String mapBelgium = "/mnt/commetud/3emeAnneMIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
    final static String path="/home/bensebaa/be-graphes/be-graphes/be-graphes-algos/src/test/java/org/insa/graphs/algorithm/path_be_254725_600594.path";

    
    @Test
    public void checkIfSamePathLength() throws IOException{

        GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
        final Graph graphBelgium = reader.read();

        PathReader readerPath = new BinaryPathReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(path))));
        
        Path pathTest = readerPath.readPath(graphBelgium);

        ShortestPathData data=new ShortestPathData(graphBelgium, pathTest.getOrigin(), pathTest.getDestination(), allFilterInspectors.get(0));
        
        DijkstraAlgorithm algo=new DijkstraAlgorithm(data);
        AStarAlgorithm algo2 = new AStarAlgorithm(data);

        ShortestPathSolution sol1,sol2;
 
        sol1=algo.run();    
        sol2=algo2.run();


        assertTrue(sol1.getPath().getLength()==sol2.getPath().getLength());
        
        
    }

    // public void compareDijkstraAStar() throws IOException{

    //     GraphReader reader = new BinaryGraphReader(
    //         new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
    //     final Graph graphBelgium = reader.read();

    //     PathReader readerPath = new BinaryPathReader(
    //         new DataInputStream(new BufferedInputStream(new FileInputStream(path))));
        
    //     Path pathTest = readerPath.readPath(graphBelgium);

    //     ShortestPathData data=new ShortestPathData(graphBelgium, pathTest.getOrigin(), pathTest.getDestination(), allFilterInspectors.get(0));
        
    //     DijkstraAlgorithm algo=new DijkstraAlgorithm(data);
    //     AStarAlgorithm algo2 = new AStarAlgorithm(data);
        
    //     //test Dijkstra time
    //     long startTime = System.nanoTime();
    //     algo.run();    
    //     // ... the code being measured ...    
    //     long estimatedTimeDijkstra = System.nanoTime() - startTime;

    //     //test AStar Time
    //     startTime = System.nanoTime();
    //     algo2.run();
    //     long estimatedTimeAStar = System.nanoTime() - startTime;

      

    //     assertTrue(estimatedTimeAStar < estimatedTimeDijkstra);
    //    /*  Duration d=algo.run().getSolvingTime();
    //     Duration d2=algo2.run().getSolvingTime(); */
        
    // }

 


}
