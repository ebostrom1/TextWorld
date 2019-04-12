import java.util.ArrayList;

public class PopStar extends Creature {
    public PopStar( Node startingRoom, int index, Graph world){
        super(startingRoom,index,world);
    }
    public void act(){
        String[] neighbors = currentRoom.getNeighborNames();
        if(currentRoom.containsPlayers()) StealItem();
        for( String neighbor : neighbors){
            if(world.containsPlayer(neighbor)) {
                move(neighbor);
                break;
            }
        }
        for( String neighbor : neighbors){
            if(world.AdjacentContainsPlayer(neighbor)) {
                move(neighbor);
                break;
            }
        }
        randomMove();
    }

    private void StealItem() {

    }
}
