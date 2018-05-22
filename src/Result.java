package TSP1;

import java.util.ArrayList;

public class Result {

    ArrayList<Product> Route;
    double Afstand;
    long Rekentijd;
    String Algorithm;

    public Result(ArrayList<Product> Route, double Afstand, long Rekentijd, String Algorithm) {
        this.Route = Route;
        this.Afstand = Afstand;
        this.Rekentijd = Rekentijd;
        this.Algorithm = Algorithm;
    }

    public Result() {

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

    public String getAlgorithm() {
        return Algorithm;
    }

    public void clearRoute() {
        Route.clear();
        Afstand = 0;
        Rekentijd = 0;
    }

    @Override
    public String toString() {
        return "Result{" + "Algorithm=" + Algorithm + ", Route=" + Route + ", Afstand=" + Afstand + ", Rekentijd=" + Rekentijd + '}';
    }

}
