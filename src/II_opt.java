package TSP1;

import java.util.ArrayList;

public class II_opt extends Algorithm {

    private ArrayList<Product> Products;
    public ArrayList<Product> Route = new ArrayList<>(25);
    public ArrayList<Product> Nieuwe_Route = new ArrayList<>(25);
    private double afstand;
    private long Rekentijd;

    public II_opt() {
        super("2-opt");
    }

    public double BerekenAfstand(ArrayList<Product> Products) {
        // afstand berekenen van de route
        int x;
        int y;
        double Afstand = 0;
        for (int i = 0; i < Products.size(); i++) {
            double lijn = 0;
            // afstand berekenen van startpunt naar eerste pakketje
            if (i == 0) {
                x = Products.get(i).getX();
                y = Products.get(i).getY();
                Afstand = Math.sqrt((x * x) + (y * y));
                // afstand berekenen tussen twee pakketjes
            } else {
                x = Products.get(i - 1).getX();
                y = Products.get(i - 1).getY();
                int x1 = Products.get(i).getX();
                int y1 = Products.get(i).getY();
                int difx = Math.abs(x - x1);
                int dify = Math.abs(y - y1);
                if (difx == 0 || dify == 0) {
                    lijn = difx + dify;
                } else {
                    lijn = Math.sqrt((difx * difx) + (dify * dify));
                }
            }
            Afstand = Afstand + lijn;
        }
        // afstand berekenen van laatste pakketje naar startpunt
        x = Products.get(Products.size() - 1).getX();
        y = Products.get(Products.size() - 1).getY();
        Afstand = Afstand + Math.sqrt((x * x) + (y * y));
        return Afstand;
    }

    @Override
    public Result run(ArrayList<Product> P) {
        Route.clear();
        Nieuwe_Route.clear();
        Products = (ArrayList<Product>) P.clone();
        // rekentijd bijhouden
        long startTime = System.nanoTime();
        // afstand berekenen van gekregen lijst
        afstand = BerekenAfstand(Products);
        int aantal = 0;
        for (int i = 0; i < Products.size() - 1; i++) {
            for (int k = i + 1; k < Products.size() - 1; k++) {
                // punten binnen de route omwisselen 
                Nieuwe_Route = (ArrayList<Product>) Products.clone();
                Product a = Nieuwe_Route.get(i);
                Product b = Nieuwe_Route.get(k);
                Nieuwe_Route.set(i, b);
                Nieuwe_Route.set(k, a);
                double Nieuwe_Afstand = BerekenAfstand(Nieuwe_Route);
                // als nieuwe afstand kleiner is dan de vorige afstand word dit de nieuwe beste route
                if (Nieuwe_Afstand < afstand) {
                    Route = (ArrayList<Product>) Nieuwe_Route.clone();
                    afstand = Nieuwe_Afstand;
                    aantal++;
                }
            }
        }
        // wanner geen een afstand groter is word de default lijst genomen
        if (aantal == 0) {
            Route = (ArrayList<Product>) Products.clone();
        }
        Rekentijd = System.nanoTime() - startTime;
        return new Result((ArrayList<Product>) Route.clone(), afstand, Rekentijd, "2-opt");
    }
}
