// package org.insa.graphs.algorithm;

// import static org.junit.Assert.assertEquals;
// import static org.junit.Assert.assertFalse;
// import static org.junit.Assert.assertTrue;

// import java.io.IOException;
// import java.util.ArrayList;
// import java.util.Arrays;

// import org.insa.graphs.model.Arc;
// import org.insa.graphs.model.Graph;
// import org.insa.graphs.model.Node;
// import org.insa.graphs.model.Path;
// import org.insa.graphs.model.RoadInformation;
// import org.insa.graphs.model.RoadInformation.RoadType;
// import org.insa.graphs.model.io.GraphReader;
// import org.junit.BeforeClass;
// import org.junit.Test;

// public class DijkstraTest {

//         //algo ou autre attributs des tests

//     @BeforeClass
//     public static void initAll() throws IOException {
//         // Importations des maps
//         final String mapInsa = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
//         final String mapToulouse = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";
//         final String mapBretagne = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";

//         // Small graph use for tests
//         Graph graphInsa;
//         Graph graphToulouse;
//         Graph graphBretagne;
// //TODO : regler pb des import
//         GraphReader reader = new BinaryGraphReader(
//             new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));
//         final Graph graphInsa = reader.read();

//         reader = new BinaryGraphReader(
//             new DataInputStream(new BufferedInputStream(new FileInputStream(mapToulouse))));
//         final Graph graphToulouse = reader.read();

//         reader = new BinaryGraphReader(
//             new DataInputStream(new BufferedInputStream(new FileInputStream(mapBretagne))));
//         final Graph graphBretagne = reader.read();

//         // Create the drawing:
//         final Drawing drawing = createDrawing();

//         // Draw the graph on the drawing.
//         drawing.drawGraph(graphInsa);
//         drawing.drawGraph(graphToulouse);
//         drawing.drawGraph(graphBretagne);

//         // Create a PathReader.
//         final PathReader pathReader =new BinaryPathReader(
//             new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));

//         // Read the path.
//         final Path path = pathReader.readPath(graph);

//         // Draw the path.
//         drawing.drawPath(path);

//         DijkstraAlgorithm algo = new DijkstraAlgorithm(new ShortestPathData(graph, null, null, null));   

//     }

//     @Test
//     public void testConstructor() {
//         assertEquals(graph, emptyPath.getGraph());
//         assertEquals(graph, singleNodePath.getGraph());
//         assertEquals(graph, shortPath.getGraph());
//         assertEquals(graph, longPath.getGraph());
//         assertEquals(graph, loopPath.getGraph());
//         assertEquals(graph, longLoopPath.getGraph());
//         assertEquals(graph, invalidPath.getGraph());
//     }

//     @Test(expected = UnsupportedOperationException.class)
//     public void testImmutability() {
//         emptyPath.getArcs().add(a2b);
//     }

//     @Test
//     public void testIsEmpty() {
//         assertTrue(emptyPath.isEmpty());

//         assertFalse(singleNodePath.isEmpty());
//         assertFalse(shortPath.isEmpty());
//         assertFalse(longPath.isEmpty());
//         assertFalse(loopPath.isEmpty());
//         assertFalse(longLoopPath.isEmpty());
//         assertFalse(invalidPath.isEmpty());
//     }

//     @Test
//     public void testSize() {
//         assertEquals(0, emptyPath.size());
//         assertEquals(1, singleNodePath.size());
//         assertEquals(4, shortPath.size());
//         assertEquals(5, longPath.size());
//         assertEquals(5, loopPath.size());
//         assertEquals(10, longLoopPath.size());
//     }

//     @Test
//     public void testIsValid() {
//         assertTrue(emptyPath.isValid());
//         assertTrue(singleNodePath.isValid());
//         assertTrue(shortPath.isValid());
//         assertTrue(longPath.isValid());
//         assertTrue(loopPath.isValid());
//         assertTrue(longLoopPath.isValid());

//         assertFalse(invalidPath.isValid());
//     }

//     @Test
//     public void testGetLength() {
//         assertEquals(0, emptyPath.getLength(), 1e-6);
//         assertEquals(0, singleNodePath.getLength(), 1e-6);
//         assertEquals(40, shortPath.getLength(), 1e-6);
//         assertEquals(62.8, longPath.getLength(), 1e-6);
//         assertEquals(55, loopPath.getLength(), 1e-6);
//         assertEquals(120, longLoopPath.getLength(), 1e-6);
//     }

//     @Test
//     public void testGetTravelTime() {
//         // Note: 18 km/h = 5m/s
//         assertEquals(0, emptyPath.getTravelTime(18), 1e-6);
//         assertEquals(0, singleNodePath.getTravelTime(18), 1e-6);
//         assertEquals(8, shortPath.getTravelTime(18), 1e-6);
//         assertEquals(12.56, longPath.getTravelTime(18), 1e-6);
//         assertEquals(11, loopPath.getTravelTime(18), 1e-6);
//         assertEquals(24, longLoopPath.getTravelTime(18), 1e-6);

//         // Note: 28.8 km/h = 8m/s
//         assertEquals(0, emptyPath.getTravelTime(28.8), 1e-6);
//         assertEquals(0, singleNodePath.getTravelTime(28.8), 1e-6);
//         assertEquals(5, shortPath.getTravelTime(28.8), 1e-6);
//         assertEquals(7.85, longPath.getTravelTime(28.8), 1e-6);
//         assertEquals(6.875, loopPath.getTravelTime(28.8), 1e-6);
//         assertEquals(15, longLoopPath.getTravelTime(28.8), 1e-6);
//     }

//     @Test
//     public void testGetMinimumTravelTime() {
//         assertEquals(0, emptyPath.getMinimumTravelTime(), 1e-4);
//         assertEquals(0, singleNodePath.getLength(), 1e-4);
//         assertEquals(4, shortPath.getMinimumTravelTime(), 1e-4);
//         assertEquals(5.14, longPath.getMinimumTravelTime(), 1e-4);
//         assertEquals(5.5, loopPath.getMinimumTravelTime(), 1e-4);
//         assertEquals(11.25, longLoopPath.getMinimumTravelTime(), 1e-4);
//     }

//     @Test
//     public void testCreateFastestPathFromNodes() {
//         Path path;
//         Arc[] expected;

//         // Simple construction
//         path = Path.createFastestPathFromNodes(graph,
//                 Arrays.asList(new Node[] { nodes[0], nodes[1], nodes[2] }));
//         expected = new Arc[] { a2b, b2c };
//         assertEquals(expected.length, path.getArcs().size());
//         for (int i = 0; i < expected.length; ++i) {
//             assertEquals(expected[i], path.getArcs().get(i));
//         }

//         // Not so simple construction
//         path = Path.createFastestPathFromNodes(graph,
//                 Arrays.asList(new Node[] { nodes[0], nodes[1], nodes[2], nodes[3] }));
//         expected = new Arc[] { a2b, b2c, c2d_3 };
//         assertEquals(expected.length, path.getArcs().size());
//         for (int i = 0; i < expected.length; ++i) {
//             assertEquals(expected[i], path.getArcs().get(i));
//         }

//         // Trap construction!
//         path = Path.createFastestPathFromNodes(graph, Arrays.asList(new Node[] { nodes[1] }));
//         assertEquals(nodes[1], path.getOrigin());
//         assertEquals(0, path.getArcs().size());

//         // Trap construction - The return!
//         path = Path.createFastestPathFromNodes(graph, Arrays.asList(new Node[0]));
//         assertEquals(null, path.getOrigin());
//         assertEquals(0, path.getArcs().size());
//         assertTrue(path.isEmpty());
//     }

//     @Test
//     public void testCreateShortestPathFromNodes() {
//         Path path;
//         Arc[] expected;

//         // Simple construction
//         path = Path.createShortestPathFromNodes(graph,
//                 Arrays.asList(new Node[] { nodes[0], nodes[1], nodes[2] }));
//         expected = new Arc[] { a2b, b2c };
//         assertEquals(expected.length, path.getArcs().size());
//         for (int i = 0; i < expected.length; ++i) {
//             assertEquals(expected[i], path.getArcs().get(i));
//         }

//         // Not so simple construction
//         path = Path.createShortestPathFromNodes(graph,
//                 Arrays.asList(new Node[] { nodes[0], nodes[1], nodes[2], nodes[3] }));
//         expected = new Arc[] { a2b, b2c, c2d_2 };
//         assertEquals(expected.length, path.getArcs().size());
//         for (int i = 0; i < expected.length; ++i) {
//             assertEquals(expected[i], path.getArcs().get(i));
//         }

//         // Trap construction!
//         path = Path.createShortestPathFromNodes(graph, Arrays.asList(new Node[] { nodes[1] }));
//         assertEquals(nodes[1], path.getOrigin());
//         assertEquals(0, path.getArcs().size());

//         // Trap construction - The return!
//         path = Path.createShortestPathFromNodes(graph, Arrays.asList(new Node[0]));
//         assertEquals(null, path.getOrigin());
//         assertEquals(0, path.getArcs().size());
//         assertTrue(path.isEmpty());
//     }

//     @Test(expected = IllegalArgumentException.class)
//     public void testCreateFastestPathFromNodesException() {
//         Path.createFastestPathFromNodes(graph, Arrays.asList(new Node[] { nodes[1], nodes[0] }));
//     }

//     @Test(expected = IllegalArgumentException.class)
//     public void testCreateShortestPathFromNodesException() {
//         Path.createShortestPathFromNodes(graph, Arrays.asList(new Node[] { nodes[1], nodes[0] }));
//     }

// }
