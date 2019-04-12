import java.util.ArrayList;

public class wumpus extends Creature{
    public wumpus( Node startingRoom, int index, Graph world){
        super(startingRoom,index,world);
    }
    public void act(){
        String[] neighbors = currentRoom.getNeighborNames();
        boolean hasNotMoved = true;
        for( String neighbor : neighbors){
            if(!world.AdjacentContainsPlayer(neighbor) && hasNotMoved) {
                move(neighbor);
                hasNotMoved = false;
            }
        }
        for( String neighbor : neighbors){
            if(!world.containsPlayer(neighbor) && hasNotMoved) {
                move(neighbor);
                hasNotMoved = false;
            }
        }
        if(hasNotMoved) {
            randomMove();
        }
    }
}
