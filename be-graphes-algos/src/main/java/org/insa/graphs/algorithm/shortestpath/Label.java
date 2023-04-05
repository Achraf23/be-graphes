import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;

public class Label{

    private Node currentNode;
    private boolean marked;
    private Double currentCost;
    private Arc father;

    public Label(Node currentNode, boolean marked, Double currentCost, Arc father) {
        this.currentNode = currentNode;
        this.marked = marked;
        this.currentCost = currentCost;
        this.father = father;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public boolean isMarked() {
        return marked;
    }

    public Double getSmallestCost() {
        return this.currentCost;
    }

    public Arc getFather() {
        return father;
    }

    public Double getCost(){
        return this.currentCost;
    }
}