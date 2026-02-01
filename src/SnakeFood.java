import java.util.Random;

public class SnakeFood {
    Random random = new Random();
    private final int TILE = 10;   // same size as your snake block

    private final int snakeFoodPositionX =
            (random.nextInt(300 / TILE)) * TILE;

    private final int snakeFoodPositionY =
            (random.nextInt(150 / TILE)) * TILE;


    public int getPositionX() {
        return this.snakeFoodPositionX;}

    public int getPositionY() {
        return  this.snakeFoodPositionY;
    }
}

