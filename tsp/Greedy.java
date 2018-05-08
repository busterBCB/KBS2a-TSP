package tsp;

import java.util.ArrayList;

public class Greedy extends Algorithm {

    private ArrayList<Product> Products;
    public ArrayList<Product> Route = new ArrayList<Product>(25);
    private Product laatste;
    private double afstand;
    private long Rekentijd;

    public Greedy(ArrayList<Product> P) {
        super("Greedy");
        Products = P;
    }

    public Result BerekenRoute() {
        long startTime = System.nanoTime();
        int x;
        int y;
        double klein = 20;
        double lengte = 0;
        for (Product temp : Products) {
            x = temp.getX();
            y = temp.getY();
            double lijn = Math.sqrt((x * x) + (y * y));
            if (lijn < klein) {
                klein = lijn;
                if (Route.size() == 0) {
                    Route.add(temp);
                    laatste = temp;
                    lengte = lijn;
                } else {
                    Route.set(0, temp);
                    laatste = temp;
                    lengte = lijn;
                }
            }
        }
        afstand = afstand + lengte;
        Products.remove(laatste);
        klein = 20;
        double lijn;
        int aantalRoute = 1;
        for (int i = 0; i < Products.size();) {
            for (Product temp : Products) {
                    x = temp.getX();
                    y = temp.getY();
                    int x1 = Route.get(Route.size()-1).getX();
                    int y1 = Route.get(Route.size()-1).getY();
                    int difx = Math.abs(x - x1);
                    int dify = Math.abs(y - y1);
                    if (difx == 0 || dify == 0) {
                        lijn = difx + dify;
                    } else {
                        lijn = Math.sqrt((difx * difx) + (dify * dify));
                    }
                    //System.out.println(lijn);
                    if (lijn < klein) {
                        klein = lijn;
                        if (Route.size() == aantalRoute) {
                            lengte = lijn;
                            Route.add(temp);
                        } else {
                            lengte = lijn;
                            Route.set(aantalRoute, temp);
                        }
                        //System.out.println(Route);
                    }
                
            }
            //System.out.println("end");
            afstand = afstand + lengte;
            klein = 20;
            Products.remove(Route.get(aantalRoute));
            aantalRoute++;    
        }
        Rekentijd = System.nanoTime() - startTime;
        return new Result(Route, afstand, Rekentijd);
    }

    /*@Override
    public String toString() {
        return "Greedy{" + "Route=" + Route + ", afstand=" + afstand + ", Rekentijd=" + Rekentijd + '}';
    }*/

    

    

}
