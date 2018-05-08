
package tsp;

import java.util.ArrayList;


public class Result {
    ArrayList<Product> Route;
    double Afstand;
    long Rekentijd;

    public Result(ArrayList<Product> Route, double Afstand, long Rekentijd) {
        this.Route = Route;
        this.Afstand = Afstand;
        this.Rekentijd = Rekentijd;
    }

    public ArrayList<Product> getRoute() {
        return Route;
    }

    public double getAfstand() {
        return Afstand;
    }

    public long getRekentijd() {
        return Rekentijd;
    }

    @Override
    public String toString() {
        return "Result{" + "Route=" + Route + ", Afstand=" + Afstand + ", Rekentijd=" + Rekentijd + '}';
    }
    
    
}
