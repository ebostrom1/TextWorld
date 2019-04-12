
public class Main {
    public static void main(String[] args) {
        Graph test = new Graph();
        Node root = new Node("hall");
        test.addNode(root);
        test.addNode("closet");
        test.addNode("bedroom");
        test.addUndirectedEdge("hall","closet");
        test.addUndirectedEdge("hall", "bedroom");
        Node currentRome = test.getNode("hall");
        test.fillWithCreatures(100 , 1, 2);
        test.addPlayer("Eric");
        do{
            test.update();
        }while (true);
    }
}
