import java.util.ArrayList;

public abstract class Creature {
    protected Node currentRoom;
    protected final Graph world;
    protected boolean alive;
    protected int itemCode;
    public Creature( Node startingRoom, int index, Graph world ){
        this.currentRoom = startingRoom;
        this.itemCode = index;
        this.world = world;
        alive =true;
    }

    protected void move(String nextRoom){
        if(currentRoom.getNeighborNode(nextRoom) != null)
            world.moveCreature(nextRoom,this);
            currentRoom = currentRoom.getNeighborNode(nextRoom);
    }
    public abstract void act();

    public Node getCurrentRoom() {
        return currentRoom;
    }
    public Integer getItemCode(){
        return itemCode;
    }
    protected void randomMove(){
        if(currentRoom == null){
            System.out.println("currentRoom == null");
        }
        String[] neighbors = currentRoom.getNeighborNames();
        int random = (int)(Math.random()*neighbors.length);
        world.moveCreature(neighbors[random], this);
        currentRoom = currentRoom.getNeighborNode(neighbors[random]);
    }
    public boolean isAlive(){
        return alive;
    }
}
