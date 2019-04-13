import java.util.HashMap;
import java.util.Map;


public class Graph {
    private HashMap<String, Node> nodes;
    private HashMap<Integer, Creature> creatureMasterList;
    private  Integer nextAvailableItemCode;
    private HashMap<String, Player> players;
    public Graph(){
        nodes = new HashMap<>();
        nextAvailableItemCode =0;
        creatureMasterList = new HashMap<>();
        players = new HashMap<>();
    }
    public void addNode(String name){
        Node n = new Node(name);
        nodes.put(name, n);
    }
    public void addNode(Node node){
        nodes.put(node.getName(),node);
    }
    public void addDirectedEdge(String name1, String name2){
        nodes.get(name1).addNeighbor(nodes.get(name2));
    }
    public void addUndirectedEdge(String name1, String name2){
        addDirectedEdge(name1, name2);
        addDirectedEdge(name2, name1);
    }
    public Node getNode(String name){
        return nodes.get(name);
    }

    public boolean AdjacentContainsPlayer(String nodeName){
        String[] neighbors = nodes.get(nodeName).getNeighborNames();
        for(String s : neighbors){
            if(containsPlayer(s))return true;
        }
        return false;
    }

    public boolean containsPlayer(String s) {
        if (nodes.get(s).getNumberOfPlayers()>0) return true;
        return false;
    }
    public void moveCreature(String nextNode, Creature creature){
        Node currentRoom = creature.getCurrentRoom();
        HashMap<Integer, Creature> creatures =  currentRoom.getCreatures();
        creatures.remove(creature);
        nodes.get(nextNode).addCreature(creature);
    }
    public void movePlayer(String response, Player player) {
        String[] s = response.split("\\.");
        String nextNode = s[1];
        Node currentPosition = player.getCurrentPosition();
        currentPosition.getCreatures().remove(player);
        nodes.get(nextNode).addPlayer(player);
    }
    public void fillWithCreatures(int chickens, int popStars, int wumpuses) {
        for (int i = 0; i < chickens; i++) {
             creatureMasterList.put(nextAvailableItemCode, new Chicken(randomNode(), nextAvailableItemCode, this ));
             nextAvailableItemCode++;
        }
        for (int i = 0; i < popStars; i++) {
            creatureMasterList.put(nextAvailableItemCode, new PopStar(randomNode(), nextAvailableItemCode, this ));
            nextAvailableItemCode++;
        }
        for (int i = 0; i < wumpuses; i++) {
            creatureMasterList.put(nextAvailableItemCode, new wumpus(randomNode(), nextAvailableItemCode, this ));
            nextAvailableItemCode++;
        }
    }

    public void update(){
        for(Map.Entry<Integer, Creature> entry : creatureMasterList.entrySet()){
            entry.getValue().act();
        }
        for(Map.Entry<String, Player> entry: players.entrySet()){
            String response = entry.getValue().getResponse();
            if(response != null) {
                if (response.equals("look")) printMoveOptions(entry.getValue());
                if (response.startsWith("go")) movePlayer(response, entry.getValue());
                if (response.startsWith("pickUp")) entry.getValue().pickupItem(response.split(".")[1]);
                if (response.startsWith("getLocation")) System.out.println(entry.getValue().getCurrentPosition());
            }
        }
    }

    private void printMoveOptions(Player player) {
        for(String s : player.getCurrentPosition().getNeighborNames()) {
            System.out.print(s + " ");
        }
    }

    private Node randomNode() {
        int random = (int)Math.random()*nodes.size();
        int count = 0;
        for(Map.Entry<String, Node> entry : nodes.entrySet()){
            if (count == random) return entry.getValue();
        }
        return null;
    }

    public void addPlayer(String name) {
        players.put(name, new Player(this,randomNode(),name));
    }
}

