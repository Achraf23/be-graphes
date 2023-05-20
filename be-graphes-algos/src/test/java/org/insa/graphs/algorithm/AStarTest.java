package org.insa.graphs.algorithm;

import org.junit.BeforeClass;
import org.junit.Test;
import java.io.FileWriter;   // Import the FileWriter class


import static org.junit.Assert.assertEquals;

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
            algoInsa1.get(i).run();
            writer.write("\n"+String.valueOf(algoInsa1.get(i).countNodeReached+"\n"));
        }

        writer.write("algoInsa2\n");
        for(int i=0;i<algoInsa2.size();i++)
        {
            // writer.write(String.valueOf(algoInsa2.get(i).run().getSolvingTime().getNano()));
            algoInsa2.get(i).run();
            writer.write("\n"+String.valueOf(algoInsa2.get(i).countNodeReached+"\n"));

        }

        writer.write("algoToulouse\n");
        for(int i=0;i<algoToulouse.size();i++)
        {
            // writer.write(String.valueOf(algoToulouse.get(i).run().getSolvingTime().getNano()));
            algoToulouse.get(i).run();
            writer.write("\n"+String.valueOf(algoToulouse.get(i).countNodeReached+"\n"));

        }

        writer.write("algoBretagne\n");
        for(int i=0;i<algoBretagne.size();i++)
        {
            // writer.write(String.valueOf(algoBretagne.get(i).run().getSolvingTime().getNano()));
            algoBretagne.get(i).run();
            writer.write("\n"+String.valueOf(algoBretagne.get(i).countNodeReached+"\n"));

            // writer.write("\n");
        }
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
