package org.insa.graphs.algorithm;

import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.FileWriter;   // Import the FileWriter class

import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.Graph;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.io.PathReader;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.insa.graphs.model.Node;


import java.io.IOException;


public class AStarTest extends DijkstraTest{
    final static String mapBelgium = "/mnt/commetud/3emeAnneMIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
    final static String path="/home/bensebaa/be-graphes/be-graphes/be-graphes-algos/src/test/java/org/insa/graphs/algorithm/AStarTestPaths/path_be_254725_600594.path";

    public class chemin{
        public Node origin,dest;
        public Graph graph;
        public chemin(Graph graph,int origin,int dest){

            this.graph=graph;
            this.origin=graph.getNodes().get(origin);
            this.dest=graph.getNodes().get(dest);

        }
    }
    
    // @BeforeClass
    // public static void initAll() throws IOException {

    //     PccTest p=new PccTest(false);
    //     p.initTest(algoInsa1,algoInsa2,algoToulouse,algoBretagne,algoBellman);

    // }

    // @Test
    // public void testGetSamePathForAllFilters() {
    //     assertEquals(algoInsa2.get(0).run().getPath().getLength(), algoInsa2.get(1).run().getPath().getLength(), 0.01);
    //     assertEquals(algoInsa2.get(2).run().getPath().getLength(), algoInsa2.get(3).run().getPath().getLength(), 0.01);
    // }

    void writeResults(FileWriter writer,ArrayList<ShortestPathSolution> solutions) throws IOException{
        for(ShortestPathSolution sol : solutions){
            writer.write(String.valueOf(sol.getSolvingTime().getNano())+"\n");
        }
    }   

    public long calculateTime(DijkstraAlgorithm algo){
             //test Dijkstra time
        long startTime = System.nanoTime();
        algo.run();    
        // ... the code being measured ...    
        long estimatedTimeDijkstra = System.nanoTime() - startTime;

        return estimatedTimeDijkstra;
    }


    public ArrayList<ShortestPathSolution> lancerAlgoComparaison() throws IOException{
        ArrayList<chemin> points=new ArrayList<>();
        PccTest p=new PccTest(false);
        // // points.add(new chemin(p.readGraph(p.mapInsa), 384, 1304));
        // // points.add(new chemin(p.readGraph(p.mapToulouse), 608, 1309));
        // points.add(new chemin(p.readGraph(p.mapBretagne), 324623, 326794));
        // // points.add(new chemin(p.readGraph(p.mapBordeaux), 12554, 7648));
        points.add(new chemin(p.readGraph(p.mapParis), 34623, 16019));
        // points.add(new chemin(p.readGraph(p.mapBelgium), 991531, 316069));


        ArrayList<ShortestPathSolution> solutions=new ArrayList<>();
        ArrayList<DijkstraAlgorithm> algos=new ArrayList<>();

        for(int i=0;i<points.size();i++){
            for(int j=0;j<2;j++){
                algos.add(new DijkstraAlgorithm(new ShortestPathData(points.get(i).graph, points.get(i).origin, points.get(i).dest, p.allFilterInspectors.get(j))));
                solutions.add(algos.get(i).run());  
            }
            
        }

        for(int i=0;i<points.size();i++){
            for(int j=0;j<2;j++){
                algos.add(new AStarAlgorithm(new ShortestPathData(points.get(i).graph, points.get(i).origin, points.get(i).dest, p.allFilterInspectors.get(j))));
                solutions.add(algos.get(i).run());
            }
        }


        return solutions;

    }

   

    // @Test
    /*public void compareDijkstraAStar() throws IOException{
        
        // PccTest p2=new PccTest(false);        
        FileWriter myWriter = new FileWriter("C:\\Users\\tgben\\OneDrive\\Bureau\\3micS2\\file.txt");

        // p1.initTest(algoInsa1,algoInsa2,algoToulouse,algoBretagne);

        writeResults(myWriter);
        System.out.println(algoInsa1.size());


        algoInsa1.clear();
        algoInsa2.clear();
        algoToulouse.clear();
        algoBretagne.clear();


        PccTest p=new PccTest(true);
        p.initTest(algoInsa1, algoInsa2, algoToulouse, algoBretagne,algoBellman);
        writeResults(myWriter);

        myWriter.close();
    }*/

    @Test
    public void checkIfSamePathLength() throws IOException{

        FileWriter myWriter = new FileWriter("/home/bensebaa/file.txt");
        writeResults(myWriter, lancerAlgoComparaison());

        myWriter.close();
        

    }
 


}
