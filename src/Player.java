import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Player {
    private Node currentPosition;
    private Graph world;
    private HashMap<String, Item> inventory;
    private String playerName;
    private String response;
    private String lastResponse;
    private Scanner in;
    public Player(Graph world, Node initialPosition, ArrayList<Item> startingInventory, String name){
        currentPosition = initialPosition;
        this.world = world;
        inventory = new HashMap<>();
        for( Item i : startingInventory){
            inventory.put(i.getName(), i);
        }
        this.playerName = name;
        startScanner();
    }
    public Player(Graph world, Node initialPosition, String name){
        currentPosition = initialPosition;
        this.world = world;
        inventory = new HashMap<>();
        this.playerName = name;
        startScanner();
    }
    public void pickupItem(String name){
        inventory.put(currentPosition.removeItemFromNode(name).getName(),currentPosition.removeItemFromNode(name));
    }
    public void startScanner(){
        in = new Scanner(System.in);
        System.out.println("possible commands: look, go.<room name>, add.<room name>");
    }
    public void dropItem(String name){
        currentPosition.addItemToNode(inventory.remove(name));
    }
    public Node getCurrentPosition(){
        return currentPosition;
    }
    public String getResponse(){
        response = in.nextLine();
        if(response !=null) {
            if (response.equals(lastResponse)) {
                return null;
            }
            lastResponse = response;
            return response;
        }
        return null;
    }
    public String getPlayerName() {
        return playerName;
    }
}
