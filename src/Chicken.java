import java.util.ArrayList;

public class Chicken extends Creature{
    public Chicken( Node startingRoom, int index, Graph world){
        super(startingRoom, index ,world);
    }

    public void act() {
        randomMove();
    }

}
