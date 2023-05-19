package org.insa.graphs.algorithm;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import java.util.ArrayList;

import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;

import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest {

    //declarations origines/dest/algos
    // protected static Node origin1, origin2, origin3, origin4, dest1, dest2, dest3, dest4;
    
    protected static ArrayList<DijkstraAlgorithm> algoInsa1 = new ArrayList<>();
    protected static ArrayList<DijkstraAlgorithm> algoInsa2 = new ArrayList<>();
    protected static ArrayList<DijkstraAlgorithm> algoToulouse = new ArrayList<>();
    protected static ArrayList<DijkstraAlgorithm> algoBretagne = new ArrayList<>();

    

    @BeforeClass
    public static void initAll() throws IOException {

        PccTest p=new PccTest(true);
        p.initTest(algoInsa1,algoInsa2,algoToulouse,algoBretagne);

        
    }
    

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
