package clases;
public class Square {
    
    private int x, y, last_x, last_y, size;
    
    //Constructor
    public Square(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
    }
    
    //Método que cambia de posicion
    public void cambiarPosicion(int x, int y){
        last_x = x;
        last_y = y;
        this.x = x;
        this.y = y;
    }
    
    //Métodos get/set
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

    public int getLast_x() {
        return last_x;
    }

    public void setLast_x(int last_x) {
        this.last_x = last_x;
    }

    public int getLast_y() {
        return last_y;
    }

    public void setLast_y(int last_y) {
        this.last_y = last_y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    
    public void incrementoX(int x){
        this.x += x;
    }
    
    public void incrementoY(int y){
        this.y += y;
    }
    
}
