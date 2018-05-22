package TSP1;

import java.util.ArrayList;

public class ReverseDefault extends Algorithm{
    private ArrayList<Product> Products;
    public ArrayList<Product> Route = new ArrayList<Product>(25);
    private double afstand;
    private long Rekentijd;
    
    public ReverseDefault(){
        super("Reverse");
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
        
        // Rekentijd bijhouden
        long startTime = System.nanoTime();
        
        Products = (ArrayList<Product>) P.clone();
        Route.clear();
        
        // Lijst omdraaien
        int aantal = Products.size()-1;
        for(int i = 0; i <= aantal;){
            Route.add(Products.get(aantal));
            aantal--;
        }
        
        afstand = this.BerekenAfstand(Route);
        Rekentijd = System.nanoTime() - startTime;
        return new Result((ArrayList<Product>) Route.clone(), afstand, Rekentijd, "Reverse");
    }
    
}
