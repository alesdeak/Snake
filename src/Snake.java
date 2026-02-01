import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake {

private Direction snakeDirection;
private final ArrayList<Coordinate> body = new ArrayList<>();

public Snake(Direction direction) {
    Coordinate firstCoordinate = new Coordinate(200, 150);
    body.add(firstCoordinate);
    this.snakeDirection = direction;
}
    public Direction getDirection() {
    return snakeDirection;
    }

    public void setDirection(Direction snakeDirection) {
    this.snakeDirection = snakeDirection;
    }

    public List<Coordinate> getBody() {
        return Collections.unmodifiableList(body);
    }

    public void grow(){
        Coordinate tail = body.getLast();
        body.add(new Coordinate(tail.getX(), tail.getY()));
    }

    public void move() {
        // Move body segments
        for (int i = body.size() - 1; i > 0; i--) {
            Coordinate prev = body.get(i - 1);
            Coordinate curr = body.get(i);
            curr.setX(prev.getX());
            curr.setY(prev.getY());
        }

        // Move head
        Coordinate head = body.getFirst();
        switch (snakeDirection) {
            case EAST -> head.setX(head.getX() + 10);
            case WEST -> head.setX(head.getX() - 10);
            case SOUTH -> head.setY(head.getY() + 10);
            case NORTH -> head.setY(head.getY() - 10);
        }
    }

}

