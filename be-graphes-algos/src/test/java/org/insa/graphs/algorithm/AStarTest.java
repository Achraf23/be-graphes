package org.insa.graphs.algorithm;

import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

import java.io.IOException;


public class AStarTest extends DijkstraTest{
    final static String mapBelgium = "/mnt/commetud/3emeAnneMIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
    final static String path="/home/bensebaa/be-graphes/be-graphes/be-graphes-algos/src/test/java/org/insa/graphs/algorithm/path_be_254725_600594.path";
   
    @BeforeClass
    public static void initAll() throws IOException {

        PccTest p=new PccTest(false);
        p.initTest(algoInsa1,algoInsa2,algoToulouse,algoBretagne);

    }

    @Test
    public void testGetSamePathForAllFilters() {
        assertEquals(algoInsa2.get(0).run().getPath().getLength(), algoInsa2.get(1).run().getPath().getLength(), 0.01);
        assertEquals(algoInsa2.get(2).run().getPath().getLength(), algoInsa2.get(3).run().getPath().getLength(), 0.01);
    }
    

    //@Test
    // public void testBelgium() throws IOException{

    //     GraphReader reader = new BinaryGraphReader(
    //         new DataInputStream(new BufferedInputStream(new FileInputStream(mapBelgium))));
    //     final Graph graphBelgium = reader.read();

    //     PathReader readerPath = new BinaryPathReader(
    //         new DataInputStream(new BufferedInputStream(new FileInputStream(path))));
        
    //     Path pathTest = readerPath.readPath(graphBelgium);

    //     ShortestPathData data=new ShortestPathData(graphBelgium, pathTest.getOrigin(), pathTest.getDestination(), allFilterInspectors.get(0));
        
    //     DijkstraAlgorithm algo=new DijkstraAlgorithm(data);
    //     AStarAlgorithm algo2 = new AStarAlgorithm(data);

    //     ShortestPathSolution sol1,sol2;
 
    //     sol1=algo.run();    
    //     sol2=algo2.run();

    //     assertEquals(sol1.getPath().getLength(),sol2.getPath().getLength(),0.01);

    //     assertTrue(sol1.getSolvingTime().getSeconds()>= sol2.getSolvingTime().getSeconds());
    //     assertTrue(algo.countNodeReached>algo2.countNodeReached);


  
        
    // }


 


}
