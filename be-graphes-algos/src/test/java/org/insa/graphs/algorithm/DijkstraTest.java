package org.insa.graphs.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;

import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest {

    //declarations origines/dest/algos
    private static Node origin1, origin2, origin3, origin4, dest1, dest2, dest3, dest4;
    
    private static ArrayList<DijkstraAlgorithm> algoInsa1 = new ArrayList<>();
    private static ArrayList<DijkstraAlgorithm> algoInsa2 = new ArrayList<>();
    private static ArrayList<DijkstraAlgorithm> algoToulouse = new ArrayList<>();
    private static ArrayList<DijkstraAlgorithm> algoBretagne = new ArrayList<>();

    public static List<ArcInspector> allFilterInspectors = ArcInspectorFactory.getAllFilters();
    //allFilterInspectors.get(0) : Shortest path, all roads allowed
    //allFilterInspectors.get(1) : Shortest path, only roads open for cars
    //allFilterInspectors.get(2) : Fastest path, all roads allowed
    //allFilterInspectors.get(3) : Fastest path, only roads open for cars
    //allFilterInspectors.get(4) : Fastest path for pedestrian

    @BeforeClass

    public static void initAll() throws IOException {
        // Importations des maps
        final String mapInsa = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
        final String mapToulouse = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";
        final String mapBretagne = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";

        // Lecture des graphs associés
        GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));
        final Graph graphInsa = reader.read();

        reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapToulouse))));
        final Graph graphToulouse = reader.read();

        reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(mapBretagne))));
        final Graph graphBretagne = reader.read();
        

        //add origin & destination

        //all road allowed and pedestrians Insa
        origin1 = graphInsa.getNodes().get(929);
        dest1 = graphInsa.getNodes().get(240);

        //same path for everyone Insa
        origin2 = graphInsa.getNodes().get(232);
        dest2 = graphInsa.getNodes().get(214);

        //comparaison shortest et fastest en passant par le periph ou non de Toulouse(mode all roads allowed)
        origin3 = graphToulouse.getNodes().get(608);
        dest3 = graphToulouse.getNodes().get(6534);

        //chemin impossible bretagne
        origin4 = graphBretagne.getNodes().get(115405);
        dest4 = graphBretagne.getNodes().get(423742);         

        for (int i = 0; i < 5; i++){
            algoInsa1.add(i, new DijkstraAlgorithm(new ShortestPathData(graphInsa, origin1, dest1, allFilterInspectors.get(i))));
        }
        for (int i = 0; i < 5; i++){
            algoInsa2.add(i, new DijkstraAlgorithm(new ShortestPathData(graphInsa, origin2, dest2, allFilterInspectors.get(i))));
        }
        
        algoToulouse.add(new DijkstraAlgorithm(new ShortestPathData(graphToulouse, origin3, dest3, allFilterInspectors.get(0))));
        algoToulouse.add(new DijkstraAlgorithm(new ShortestPathData(graphToulouse, origin3, dest3, allFilterInspectors.get(2))));

        for (int i = 0; i < 5; i++){
            algoBretagne.add(i, new DijkstraAlgorithm(new ShortestPathData(graphBretagne, origin4, dest4, allFilterInspectors.get(i))));
        }
        
    }

    // public static void addAlgos(Graph graphInsa,Graph graphToulouse,Graph graphBretagne){
    //     for (int i = 0; i < 5; i++){
    //         algoInsa1.add(i, new DijkstraAlgorithm(new ShortestPathData(graphInsa, origin1, dest1, allFilterInspectors.get(i))));
    //     }
    //     for (int i = 0; i < 5; i++){
    //         algoInsa2.add(i, new DijkstraAlgorithm(new ShortestPathData(graphInsa, origin2, dest2, allFilterInspectors.get(i))));
    //     }
        
    //     algoToulouse.add(new DijkstraAlgorithm(new ShortestPathData(graphToulouse, origin3, dest3, allFilterInspectors.get(0))));
    //     algoToulouse.add(new DijkstraAlgorithm(new ShortestPathData(graphToulouse, origin3, dest3, allFilterInspectors.get(2))));

    //     for (int i = 0; i < 5; i++){
    //         algoBretagne.add(i, new DijkstraAlgorithm(new ShortestPathData(graphBretagne, origin4, dest4, allFilterInspectors.get(i))));
    //     }
    // }

    

    @Test
    public void testFilters() {
        assertEquals(algoInsa1.get(0).run().isFeasible(), true);
        assertEquals(algoInsa1.get(1).run().isFeasible(), false);
        assertEquals(algoInsa1.get(2).run().isFeasible(), true);
        assertEquals(algoInsa1.get(3).run().isFeasible(), false);
        assertEquals(algoInsa1.get(4).run().isFeasible(), true);
    }


    @Test
    public void testGetSamePathForAllFilters() {
        assertEquals(algoInsa2.get(0).run().getPath().getLength(), algoInsa2.get(1).run().getPath().getLength(), 0.01);
        assertEquals(algoInsa2.get(1).run().getPath().getLength(), algoInsa2.get(2).run().getPath().getLength(), 0.01);
        assertEquals(algoInsa2.get(2).run().getPath().getLength(), algoInsa2.get(3).run().getPath().getLength(), 0.01);
        assertEquals(algoInsa2.get(3).run().getPath().getLength(), algoInsa2.get(4).run().getPath().getLength(), 0.01);
    }

    @Test
    public void testIsShortestPath() {
        assertTrue(algoToulouse.get(0).run().getPath().getLength() <= algoToulouse.get(1).run().getPath().getLength());
    }

    @Test
    public void testIsFastestPath() {
        assertTrue(algoToulouse.get(0).run().getPath().getMinimumTravelTime() >= algoToulouse.get(1).run().getPath().getMinimumTravelTime());
    }

    @Test
    public void isInfeasible() {
        assertEquals(algoBretagne.get(0).run().isFeasible(), false);
        assertEquals(algoBretagne.get(1).run().isFeasible(), false);
        assertEquals(algoBretagne.get(2).run().isFeasible(), false);
        assertEquals(algoBretagne.get(3).run().isFeasible(), false);
        assertEquals(algoBretagne.get(4).run().isFeasible(), false);
    }

}
