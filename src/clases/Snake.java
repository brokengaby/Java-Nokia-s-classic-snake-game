package clases;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Snake extends Thread {

    private int x, y, lenght, size, movement, direction, width_jpanel, height_jpanel;
    public static final int UP = 1, DOWN = 2, LEFT = 3, RIGHT = 4;
    public boolean playing = true;
    private int score = 0;

    ArrayList<Square> body = new ArrayList<Square>();

    //Constructor
    public Snake(int size, int width_jpanel, int height_jpanel) {
        this.width_jpanel = width_jpanel;
        this.height_jpanel = height_jpanel;
        this.lenght = 1;
        this.x = 50;
        this.y = 50;
        this.size = size;
        this.movement = size;
        this.direction = RIGHT;
        body.add(new Square(x, y, size));
        body.add(new Square(x - size, y, size));
        body.add(new Square(x - size * 2, y, size));
        body.add(new Square(x - size * 3, y, size));
        body.add(new Square(x - size * 4, y, size));

        //Corremos
        this.start();
    }

    //Método para move
    public void move() {

        body.get(0).setLast_x(body.get(0).getX());
        body.get(0).setLast_y(body.get(0).getY());

        if (direction == RIGHT) {
            body.get(0).incrementoX(size);
        }
        if (direction == LEFT) {
            body.get(0).incrementoX(-size);
        }
        if (direction == DOWN) {
            body.get(0).incrementoY(size);
        }
        if (direction == UP) {
            body.get(0).incrementoY(-size);
        }

        int x_s = body.get(0).getX();
        int y_s = body.get(0).getY();

        if (x_s + size > width_jpanel || x_s < 0 || y_s + size > height_jpanel || y_s < 0 || eatItself()) {
            playing = false;
        }

    }

    //eat y crecer
    public void eat() {
        body.add(new Square(body.get(body.size() - 1).getX(), body.get(body.size() - 1).getY(), size));
        this.score++;
    }

    //Refresca las posicones conforme al movimiento
    private void refreshBody() {
        for (int i = 1; i < body.size(); i++) {
            body.get(i).setLast_x(body.get(i).getX());
            body.get(i).setLast_y(body.get(i).getY());
            body.get(i).setX(body.get(i - 1).getLast_x());
            body.get(i).setY(body.get(i - 1).getLast_y());
        }
    }

    //Método run
    @Override
    public void run() {
        while (playing) {
            move();
            refreshBody();
            try {
                Thread.sleep(70);
            } catch (InterruptedException ex) {
                Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public int getSize() {
        return size;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getdirection() {
        return direction;
    }

    public void setdirection(int direction) {
        this.direction = direction;
    }

    public ArrayList<Square> getSquares() {
        return body;
    }

    public int getScore() {
        return score;
    }

    public boolean isPlaying() {
        return playing;
    }
    
    public boolean eatItself(){
        for(int i = 2; i < body.size(); i++){
            if(body.get(0).getX() == body.get(i).getX() && body.get(0).getY() == body.get(i).getY())return true;
        }
        return false;
    }

}
