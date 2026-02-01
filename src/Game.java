import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    Snake snake = new Snake(Direction.EAST);
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

    @Override
    public void keyReleased(KeyEvent e) {
            }

    class MyDrawPanel extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(0,0,getWidth(),getHeight());
            g.setColor(Color.RED);
            g.fillRect(snakeFood.getX(), snakeFood.getY(), 10, 10);
            g.setColor(Color.BLACK);
            for ( Coordinate element : snake.getBody()){
                g.fillRect(element.getX(), element.getY(), 10, 10);
            }
        }
    }

    public Boolean isEating(){
        return Math.abs(snake.getBody().getFirst().getX() - snakeFood.getX()) < 10 &&
                Math.abs(snake.getBody().getFirst().getY() - snakeFood.getY()) < 10;
    }

public void isCollision() {
    int x = snake.getBody().getFirst().getX();
    int y = snake.getBody().getFirst().getY();

    if (x < 0 || x > panel.getWidth() - 10 ||
            y < 0 || y > panel.getHeight() - 10) {
        gameOver = true;
    }

    for (int j = snake.getBody().size() - 1; j > 1; j--) {
        if (snake.getBody().getFirst().getX() == snake.getBody().get(j).getX() && snake.getBody().getFirst().getY() == snake.getBody().get(j).getY()) {
            gameOver = true;
            break;
        }
    }
}

    private boolean foodOnSnake(int x, int y) {
        for (Coordinate part : snake.getBody()) {
            if (part.getX() == x && part.getY() == y) {
                return true;
            }
        }
        return false;
    }

    private void resetGame() {
        snake = new Snake(Direction.EAST);
        snakeFood = new SnakeFood();
        gameOver = false;



        while (foodOnSnake(snakeFood.getX(), snakeFood.getY())) {
            snakeFood = new SnakeFood();
        }

        panel.repaint();
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
                        "Game Over! You ate " + (snake.getBody().size() - 1) + " foods.",
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
                        snakeFood.getX(),
                        snakeFood.getY()
                ));
            }
            panel.repaint();
        });
    gameTimer.start();
    }
}








