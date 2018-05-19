package tsp;

import java.util.ArrayList;

public class Greedy extends Algorithm {

    private ArrayList<Product> Products;
    public ArrayList<Product> Route = new ArrayList<Product>(25);
    private Product laatste;
    private double afstand;
    private long Rekentijd;

    public Greedy() {
        super("Greedy");
    }

    public Result BerekenRoute(ArrayList<Product> P) {
        Products = (ArrayList<Product>)P.clone();
        Route.clear();
        afstand = 0;
        long startTime = System.nanoTime();
        int x;
        int y;
        double klein = 20;
        for (Product temp : Products) {
            x = temp.getX();
            y = temp.getY();
            double lijn = Math.sqrt((x * x) + (y * y));
            //kortste onthouden
            if (lijn < klein) {
                klein = lijn;
                if (Route.size() == 0) {
                    Route.add(temp);
                    laatste = temp;
                } else {
                    Route.set(0, temp);
                    laatste = temp;
                }
            }
        }
        afstand = afstand + klein;
        Products.remove(laatste);
        klein = 20;
        double lijn;
        int aantalRoute = 1;
        int aantal = 0;
        // tot alle producten verwijderd zijn
        for (int i = 0; i < Products.size();) {
            for (Product temp : Products) {                    
                    x = temp.getX();
                    y = temp.getY();
                    int x1 = Route.get(aantal).getX();
                    int y1 = Route.get(aantal).getY();
                    int difx = Math.abs(x - x1);
                    int dify = Math.abs(y - y1);
                    if (difx == 0 || dify == 0) {
                        lijn = difx + dify;
                    } else {
                        lijn = Math.sqrt((difx * difx) + (dify * dify));
                    }
                    if (lijn < klein) {
                        klein = lijn;
                        if (Route.size() == aantalRoute) {
                            Route.add(temp);
                        } else {
                            Route.set(aantalRoute, temp);
                        }
                    }
                
            }
            afstand = afstand + klein;
            klein = 20;
            Products.remove(Route.get(aantalRoute));
            aantalRoute++;
            aantal++;
        }
        // laatste product naar de start
        x = Route.get(Route.size()-1).getX();
        y = Route.get(Route.size()-1).getY();
        afstand = afstand + Math.sqrt((x * x) + (y * y));
        Rekentijd = System.nanoTime() - startTime;
        return new Result((ArrayList<Product>)Route.clone(), afstand, Rekentijd, "Greedy");
    }
}
