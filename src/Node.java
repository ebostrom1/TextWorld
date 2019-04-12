import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    private String name;
    private ArrayList<Node> neighbors;
    private String description;
    private HashMap<String, Item> roomInventory;
    private HashMap<String, Player> players;
    private HashMap<Integer, Creature> Creatures;
    public Node(String name, ArrayList<Item> startingItems){
        roomInventory = new HashMap<>();
        for(Item i : startingItems){
            roomInventory.put(i.getName(),i);
        }
        this.name = name;
        description = "";
        neighbors = new ArrayList<>();
        Creatures = new HashMap<>();
        players = new HashMap<>();
    }
    public Node(String name){
        this.name = name;
        neighbors = new ArrayList<>();
        Creatures = new HashMap<>();
        players = new HashMap<>();
    }

    public void addCreatures(ArrayList<Creature> creatures){
        for(Creature c : creatures){
            addCreature(c);
        }
    }
    public void addCreature(Creature creature){
        Creatures.put(creature.getItemCode(),creature);
    }
    public void addNeighbor(Node neighbor){
        neighbors.add(neighbor);
    }
    public String[] getNeighborNames(){
        String[] names = new String[neighbors.size()];
        for (int i = 0; i < neighbors.size(); i++) {
            names[i]= neighbors.get(i).getName();
        }
        return names;
    }
    public Node getNeighborNode(String nodeName){
        for(Node n : neighbors){
            if(n.getName().equals(nodeName)) return n;
        }
        return null;
    }
    public String getName(){
        return name;
    }
    public void addDescription(String description){
        this.description=description;
    }
    public void addItemToNode(ArrayList<Item> items){
        for(Item i : items){
            addItemToNode(i);
        }
    }
    public void addItemToNode(Item item){
        roomInventory.put(item.getName(),item);
    }
    public Item removeItemFromNode(String name){
        return roomInventory.remove(name);
    }
    public void addPlayer(Player player){
        players.put(player.getPlayerName(), player);
    }
    public void removePlayer(String name){
        players.remove(name);
    }
    public int getNumberOfPlayers(){
        return players.size();
    }

    public HashMap<Integer, Creature> getCreatures() {
        return Creatures;
    }
    public boolean containsPlayers(){
        return (getNumberOfPlayers()>0);
    }

    public Creature removeCreature(Integer index) {
        return Creatures.remove(index);
    }
}

