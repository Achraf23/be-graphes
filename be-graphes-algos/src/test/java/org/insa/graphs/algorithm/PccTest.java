package org.insa.graphs.algorithm;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import java.util.ArrayList;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import java.util.List;


public class PccTest {
    boolean isDijkstra;
    public static Node origin1, origin2, origin3, origin4, dest1, dest2, dest3, dest4;
    public static List<ArcInspector> allFilterInspectors = ArcInspectorFactory.getAllFilters();
    
    // cartes.add(new String("/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr"));

    public final String mapInsa = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/insa.mapgr";
    public final String mapToulouse = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/toulouse.mapgr";
    public final String mapBretagne = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bretagne.mapgr";
    public final String mapBelgium ="/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/belgium.mapgr";
    public final String mapBordeaux ="/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/bordeaux.mapgr";
    public final String mapParis="/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/paris.mapgr";

    
    
    
    public PccTest(boolean isDijkstra){
        this.isDijkstra=isDijkstra;
    }

    public Graph readGraph(String path) throws IOException{
        GraphReader reader = new BinaryGraphReader(
            new DataInputStream(new BufferedInputStream(new FileInputStream(path))));
        return reader.read();
    }




    public void initTest(ArrayList<DijkstraAlgorithm> algoInsa1,ArrayList<DijkstraAlgorithm> algoInsa2,
    ArrayList<DijkstraAlgorithm> algoToulouse,ArrayList<DijkstraAlgorithm> algoBretagne, ArrayList<BellmanFordAlgorithm> algoBellman) throws IOException{
        
        // final String mapInsa = "C:/Users/tgben/OneDrive/Bureau/3micS2/Maps/insa.mapgr";
        // final String mapToulouse = "C:/Users/tgben/OneDrive/Bureau/3micS2/Maps/toulouse.mapgr";
        // final String mapBretagne = "C:/Users/tgben/OneDrive/Bureau/3micS2/Maps/bretagne.mapgr";

        ;

        // Lecture des graphs associés
        
        final Graph graphInsa = readGraph(mapInsa);
        final Graph graphToulouse = readGraph(mapToulouse);
        final Graph graphBretagne = readGraph(mapBretagne);

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
        
        for(int i=0;i<5;i++){
            addAlgoPourChaqueFiltre(algoInsa1,graphInsa,origin1,dest1,i);
            addAlgoPourChaqueFiltre(algoInsa2,graphInsa,origin2,dest2,i);
            addAlgoPourChaqueFiltre(algoBretagne,graphBretagne,origin4,dest4,i);
        }

        addAlgoPourChaqueFiltre(algoToulouse, graphToulouse, origin3, dest3, 0);
        addAlgoPourChaqueFiltre(algoToulouse, graphToulouse, origin3, dest3, 2);
        

        
        for(int i=0; i<5; i++){
            algoBellman.add(new BellmanFordAlgorithm(new ShortestPathData(graphInsa, origin2, dest2, allFilterInspectors.get(i))));
        }
    }

    public void addAlgoPourChaqueFiltre(ArrayList<DijkstraAlgorithm> listeAlgos,Graph graph,Node origin, Node dest,
    int i){
        if(isDijkstra)
        listeAlgos.add(new DijkstraAlgorithm(new ShortestPathData(graph, origin, dest, allFilterInspectors.get(i))));
        else
        listeAlgos.add(new AStarAlgorithm(new ShortestPathData(graph, origin, dest, allFilterInspectors.get(i))));
 
    }    

   
}
