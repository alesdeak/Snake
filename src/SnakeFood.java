import java.util.Random;

public class SnakeFood {
    Random random = new Random();
    private final int TILE = 10;

    private final int X =
            (random.nextInt(300 / TILE)) * TILE;

    private final int Y =
            (random.nextInt(150 / TILE)) * TILE;


    public int getX() {
        return this.X;}

    public int getY() {
        return  this.Y;
    }
}

