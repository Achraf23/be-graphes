package org.insa.graphs.algorithm;

import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.FileWriter;   // Import the FileWriter class

import java.io.IOException;


public class AStarTest extends DijkstraTest{
    final static String mapBelgium = "/mnt/commetud/3emeAnneMIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
    final static String path="/home/bensebaa/be-graphes/be-graphes/be-graphes-algos/src/test/java/org/insa/graphs/algorithm/path_be_254725_600594.path";
   
    @BeforeClass
    public static void initAll() throws IOException {

        PccTest p=new PccTest(false);
        p.initTest(algoInsa1,algoInsa2,algoToulouse,algoBretagne,algoBellman);

    }

    // @Test
    // public void testGetSamePathForAllFilters() {
    //     assertEquals(algoInsa2.get(0).run().getPath().getLength(), algoInsa2.get(1).run().getPath().getLength(), 0.01);
    //     assertEquals(algoInsa2.get(2).run().getPath().getLength(), algoInsa2.get(3).run().getPath().getLength(), 0.01);
    // }

    void writeResults(FileWriter writer) throws IOException{
        writer.write("algoInsa1\n");
        for(int i=0;i<algoInsa1.size();i++)
        {
            // writer.write(String.valueOf(algoInsa1.get(i).run().getSolvingTime().getNano())+"\n");
            // algoInsa1.get(i).run();
            writer.write(String.valueOf(calculateTime(algoInsa1.get(i)))+"\n");
         
        }

        writer.write("algoInsa2\n");
        for(int i=0;i<algoInsa2.size();i++)
        {
            // writer.write(String.valueOf(algoInsa2.get(i).run().getSolvingTime().getNano())+"\n");
            // algoInsa2.get(i).run();
            writer.write(String.valueOf(calculateTime(algoInsa2.get(i)))+"\n");

        }

        writer.write("algoToulouse\n");
        for(int i=0;i<algoToulouse.size();i++)
        {
            
            // algoToulouse.get(i).run();
            // writer.write(String.valueOf(algoToulouse.get(i).run().getSolvingTime().getNano())+"\n");
            writer.write(String.valueOf(calculateTime(algoToulouse.get(i)))+"\n");

        }

        writer.write("algoBretagne\n");
        for(int i=0;i<algoBretagne.size();i++)
        {
            // writer.write(String.valueOf(algoBretagne.get(i).run().getSolvingTime().getNano())+"\n");
            // algoBretagne.get(i).run();
            writer.write(String.valueOf(calculateTime(algoBretagne.get(i)))+"\n");

            // writer.write("\n");
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

   

    @Test
    public void compareDijkstraAStar() throws IOException{
        
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
    }
 


}
