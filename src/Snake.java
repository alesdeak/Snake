import java.util.ArrayList;

public class Snake {

private int size;
private Direction snakeDirection;
public ArrayList<Coordinate> body = new ArrayList<>();

public Snake(int size, Direction direction) {
    Coordinate firstCoordinate = new Coordinate(200, 150);
body.add(firstCoordinate);
    this.size = size;
    this.snakeDirection = direction;
}

    public int getHeadPositionX() {
    return this.body.getFirst().x;}

    public int getHeadPositionY() {
        return  this.body.getFirst().y;
    }
    public Direction getDirection() {
    return snakeDirection;
    }

    public void setDirection(Direction snakeDirection) {
    this.snakeDirection = snakeDirection;
    }

    public void setSize(int size) {
    this.size = size;
    }
    public int getSize() {
    return this.size;
    }

    public void grow(){
        Coordinate tail = body.getLast();
        body.add(new Coordinate(tail.x, tail.y));
        setSize(getSize() + 1);
    }

    public void move(){
        if (getDirection() == Direction.EAST) {
            for (int i = body.size() - 1; i > 0; i--) {
                body.get(i).x = body.get(i - 1).x;
                body.get(i).y = body.get(i - 1).y;
            }
            body.getFirst().x = getHeadPositionX() + 10;
            body.getFirst().y = getHeadPositionY();
        } else if (getDirection() == Direction.SOUTH) {
            for (int i = body.size() - 1; i > 0; i--) {
                body.get(i).x = body.get(i - 1).x;
                body.get(i).y = body.get(i - 1).y;
            }
            body.getFirst().x = getHeadPositionX();
            body.getFirst().y = getHeadPositionY() + 10;
        }
        else if (getDirection() == Direction.NORTH) {
            for (int i = body.size() - 1; i > 0; i--) {
                body.get(i).x = body.get(i - 1).x;
                body.get(i).y = body.get(i - 1).y;
            }
            body.getFirst().x = getHeadPositionX();
            body.getFirst().y = getHeadPositionY() - 10;
        }
        else if (getDirection() == Direction.WEST) {
            for (int i = body.size() - 1; i > 0; i--) {
                body.get(i).x = body.get(i - 1).x;
                body.get(i).y = body.get(i - 1).y;
            }
            body.getFirst().x = getHeadPositionX() - 10;
            body.getFirst().y = getHeadPositionY();
        }
    }
}

