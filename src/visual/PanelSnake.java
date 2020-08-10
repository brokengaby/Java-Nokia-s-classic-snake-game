package visual;

import clases.Square;
import clases.Snake;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class PanelSnake extends JPanel {

    //Timer para repintar
    Timer timer;
    TimerTask task;

    //Snake size (each square)
    int t_v = 25;

    //Serpiente
    Snake s;

    //KeyListener
    KeyListener kl;

    //Para iniciar
    Square Fruit;
    Square head;

    //Colores
    Color color_background = new Color(6, 0, 12);
    Color color_Fruit = new Color(254, 0, 156);
    Color color_snake = new Color(207, 247, 255);
    Color color_grid = new Color(30, 30, 30);

    //font
    Font font_score = new Font("consolas", 1, 15);
    Font font_loose = new Font("consolas", 1, 25);

    //Constructor
    public PanelSnake() {
        //Tamaño
        this.setSize(600, 550);
        //Color de background
        this.setBackground(color_background);
        //Focusable
        this.setFocusable(true);
        //Doble buffer
        this.setDoubleBuffered(true);
        //Creando vibora
        s = new Snake(t_v, getWidth(), getHeight());
        //Timer
        this.defineTimer();
        //Controlar con teclado
        this.defineKeyListener();
        //Generamos la Fruit
        this.createFruit();
        //La head capturada
        head = s.getSquares().get(0);
    }

    //Pintar
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //Pintando la serpiente, cada cuadro de ella
        g.setColor(color_snake); //Color blanco para todas
        //Para cada cuadro
        ArrayList<Square> cds = s.getSquares();
        try {
            for (Square c : cds) {
                g.fillRect(c.getX(), c.getY(), c.getSize(), c.getSize());
            }
        }catch(java.util.ConcurrentModificationException ex){
            System.out.println("");
        }
        //La Fruit
        g.setColor(color_Fruit);
        if (Fruit != null) {
            g.fillRect(Fruit.getX(), Fruit.getY(), t_v, t_v);
        }
        //Dibuja una grid
        g.setColor(color_grid);
        int x_grid = t_v;
        for (int i = 0; i < 24; i++) {
            g.drawLine(x_grid, 0, x_grid, this.getHeight());
            g.drawLine(0, x_grid, this.getWidth(), x_grid);
            x_grid += t_v;
        }

        //La puntuación
        if (s.isPlaying()) {
            g.setColor(Color.WHITE);
            g.setFont(font_score);
            g.drawString("Score: " + s.getScore(), getWidth() - 125, 20);
        } //SI HEMOS PERDIDO}
        else {
            g.setColor(Color.WHITE);
            g.setFont(font_loose);
            g.drawString("¡You Loose!, Score: " + s.getScore(), 160, this.getHeight() / 2 - 50);
            g.drawString("Hit ENTER to restart", 175, this.getHeight() / 2);
        }
    }

    //Timer
    private void defineTimer() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                repaint();
                //Si la head golpea la Fruit, come y crece
                if (head.getX() == Fruit.getX() && head.getY() == Fruit.getY()) {
                    s.eat();
                    Fruit = null;
                    createFruit();
                }
            }
        };
        //Programando
        timer.schedule(task, 100, 1);
    }

    private void defineKeyListener() {
        kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyPressed(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    if (s.getdirection() != Snake.DOWN) {
                        s.setdirection(Snake.UP);
                    }
                }
                if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    if (s.getdirection() != Snake.UP) {
                        s.setdirection(Snake.DOWN);
                    }
                }
                if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                    if (s.getdirection() != Snake.RIGHT) {
                        s.setdirection(Snake.LEFT);
                    }
                }
                if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                    if (s.getdirection() != Snake.LEFT) {
                        s.setdirection(Snake.RIGHT);
                    }
                }

                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (!s.playing) {
                        //Creando vibora
                        s = new Snake(t_v, getWidth(), getHeight());
                        //Generamos la Fruit
                        createFruit();
                        //La head capturada
                        head = s.getSquares().get(0);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };

        //Añadimos
        this.addKeyListener(kl);
    }

    //create Fruit
    private void createFruit() {
        int x = (int) (Math.random() * (this.getWidth() / t_v)) * t_v;
        int y = (int) (Math.random() * (this.getHeight() / t_v)) * t_v;
        Fruit = new Square(x, y, t_v);
    }

}
