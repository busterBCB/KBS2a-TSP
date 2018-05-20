
package tsp;


public class Product {
    private int x;
    private int y;
    
    public Product(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    @Override
    public String toString() {
        return "Product{" + "x=" + x + ", y=" + y + '}';
    }
    
}
