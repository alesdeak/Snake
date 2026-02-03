import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake {

private Direction currentDirection;
private Direction nextDirection;
private final ArrayList<Coordinate> body = new ArrayList<>();

public Snake(Direction direction) {
    Coordinate firstCoordinate = new Coordinate(200, 150);
    body.add(firstCoordinate);
    this.currentDirection = direction;
    this.nextDirection = direction;

}

    public List<Coordinate> getBody() {
        return Collections.unmodifiableList(body);
    }

    public void requestDirection(Direction newDir) {
        // block 180 turns
        if (currentDirection == Direction.WEST && newDir == Direction.EAST) return;
        if (currentDirection == Direction.EAST && newDir == Direction.WEST) return;
        if (currentDirection == Direction.NORTH && newDir == Direction.SOUTH) return;
        if (currentDirection == Direction.SOUTH && newDir == Direction.NORTH) return;

        nextDirection = newDir;
    }

    public void grow(){
        Coordinate tail = body.getLast();
        body.add(new Coordinate(tail.getX(), tail.getY()));
    }

    public void move() {
        currentDirection = nextDirection;
        // Move body segments
        for (int i = body.size() - 1; i > 0; i--) {
            Coordinate prev = body.get(i - 1);
            Coordinate curr = body.get(i);
            curr.setX(prev.getX());
            curr.setY(prev.getY());
        }

        // Move head
        Coordinate head = body.getFirst();
        switch (currentDirection) {
            case EAST -> head.setX(head.getX() + 10);
            case WEST -> head.setX(head.getX() - 10);
            case SOUTH -> head.setY(head.getY() + 10);
            case NORTH -> head.setY(head.getY() - 10);
        }
    }

}

