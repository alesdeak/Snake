import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    Snake snake = new Snake(1, Direction.EAST);
    SnakeFood snakeFood = new SnakeFood();
    boolean gameOver = false;
    MyDrawPanel panel = new MyDrawPanel();
    Timer gameTimer;


    public static void main(String[] args) {
        Game game = new Game();
        game.go();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && snake.getDirection() != Direction.EAST) {
            snake.setDirection(Direction.WEST);}
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT && snake.getDirection() != Direction.WEST) {
            snake.setDirection(Direction.EAST);}
            else if (e.getKeyCode() == KeyEvent.VK_UP && snake.getDirection() != Direction.SOUTH) {
                snake.setDirection(Direction.NORTH);}
                else  if (e.getKeyCode() == KeyEvent.VK_DOWN && snake.getDirection() != Direction.NORTH) {
                    snake.setDirection(Direction.SOUTH);
                }
            }

    class MyDrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.BLACK);
            g.fillRect(snake.body.getFirst().x, snake.body.getFirst().y, 10, 10);
            g.setColor(Color.RED);
            g.fillRect(snakeFood.getPositionX(), snakeFood.getPositionY(), 10, 10);
            g.setColor(Color.BLACK);
            for ( Coordinate element : snake.body){
                g.fillRect(element.x, element.y, 10, 10);
            }
        }
    }

    public Boolean isEating(){
        return Math.abs(snake.body.getFirst().x - snakeFood.getPositionX()) < 10 &&
                Math.abs(snake.body.getFirst().y - snakeFood.getPositionY()) < 10;
    }

public void isCollision() {
    int x = snake.body.getFirst().x;
    int y = snake.body.getFirst().y;

    if (x < 0 || x > panel.getWidth() - 10 ||
            y < 0 || y > panel.getHeight() - 10) {
        gameOver = true;
    }

    for (int j = snake.body.size() - 1; j > 1; j--) {
        if (snake.body.getFirst().x == snake.body.get(j).x && snake.body.getFirst().y == snake.body.get(j).y) {
            gameOver = true;
            break;
        }
    }
}

    private boolean foodOnSnake(int x, int y) {
        for (Coordinate part : snake.body) {
            if (part.x == x && part.y == y) {
                return true;   // food would spawn inside the snake
            }
        }
        return false;
    }

    private void resetGame() {
        snake = new Snake(1, Direction.EAST);      // new snake
        snakeFood = new SnakeFood();              // new food
        gameOver = false;                          // reset flag
                                  // reset score

        // Make sure the new food is not on the snake
        while (foodOnSnake(snakeFood.getPositionX(), snakeFood.getPositionY())) {
            snakeFood = new SnakeFood();
        }

        panel.repaint();                           // redraw initial state
    }

    public void go() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setVisible(true);
        frame.add(this.panel);
        frame.addKeyListener(this);

        gameTimer = new Timer(150, e -> {
            if (gameOver) {
                gameTimer.stop();
                JOptionPane.showMessageDialog(
                        null,
                        "Game Over! You ate " + (snake.getSize() - 1) + " foods.",
                        "Snake Score",
                        JOptionPane.INFORMATION_MESSAGE
                );
                resetGame();
                gameTimer.start();
                return;
            }

            snake.move();
            isCollision();
            if(isEating()) {
                snake.grow();
                do {
                    snakeFood = new SnakeFood();
                } while (foodOnSnake(
                        snakeFood.getPositionX(),
                        snakeFood.getPositionY()
                ));
            }
            panel.repaint();
        });
    gameTimer.start();
    }
}








