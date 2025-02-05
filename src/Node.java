import java.util.ArrayList;
import java.util.List;

// Estrutura de controle de nó
public class Node {
    int value;
    List<Node> children;

    public Node(int value) {
        this.value = value;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        this.children.add(child);
    }
}