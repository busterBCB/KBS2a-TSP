
package tsp;

import java.util.ArrayList;
import static java.util.Collections.copy;
import java.util.concurrent.ThreadLocalRandom;

public class LimitedBruteForce extends Algorithm{
    private ArrayList<Product> Products;
    private ArrayList<Product> ProductsBackup;
    private ArrayList<ArrayList<Product>> Routes = new ArrayList<ArrayList<Product>>();
    public ArrayList<Product> Route = new ArrayList<Product>(25);
    private ArrayList<Product> Tijdelijk = new ArrayList<Product>();
    private double afstand;
    private long Rekentijd;
    private long RouteAantal = 1000;
    
    public LimitedBruteForce() {
        super("Limited Brute-Force");
    }
    
    public Result BerekenRoute(ArrayList<Product> P) {
        Products = (ArrayList<Product>)P.clone();
        ProductsBackup = (ArrayList<Product>)Products.clone();
        // Rekentijd bijhouden
        long startTime = System.nanoTime();
        int x;
        int y;
        boolean add = true;
        Routes.clear();
        Routes.add(Products);
        int a = 1;
        // checken of aantal oplossingen minder is dan max RouteAantal
        for(int i = 1; i <= Products.size(); i++ ){
            a = a * i;
        }
        // als oplossingen minder is dan RouteAantal word dit nummer de nieuwe RouteAantal
        if(a < RouteAantal){
            RouteAantal = a;
        }
        // Alle oplossingen in Routes stoppen
        for (long i = RouteAantal; i > Routes.size();) {
            for (int ii = -1; ii < ProductsBackup.size()-1;) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, ProductsBackup.size());
                Tijdelijk.add(ProductsBackup.get(randomNum));
                ProductsBackup.remove(randomNum);
            }
            for (ArrayList<Product> temp : Routes){
                int Gelijk = 0;
                int aantal = 0;
                for(Product temp2 : temp){
                    if(temp2 == Tijdelijk.get(aantal)){
                        Gelijk++;
                    }
                    aantal++;
                    }
                    if(Gelijk == Tijdelijk.size()){
                        add = false;
                    }
                }
            
            if(add){
                Routes.add((ArrayList<Product>)Tijdelijk.clone());
            }
            add = true;
            Tijdelijk.clear();
            ProductsBackup = (ArrayList<Product>)Products.clone();
        }
        
        double lijn = 0;
        afstand = 1000;
        // wat is de kortste route
        for(ArrayList<Product> temp : Routes){
            int aantal = 0;
            for(Product temp2 : temp){
                x = temp2.getX();
                y = temp2.getY();
                if(aantal == 0){
                    lijn = lijn + Math.sqrt((x * x) + (y * y));
                }else{
                    int x1 = temp.get(aantal-1).getX();
                    int y1 = temp.get(aantal-1).getY();
                    int difx = Math.abs(x - x1);
                    int dify = Math.abs(y - y1);
                    if (difx == 0 || dify == 0) {
                        lijn = lijn + (difx + dify);
                    } else {
                        lijn = lijn + Math.sqrt((difx * difx) + (dify * dify));
                    }
                }
                aantal++;
            }
            x = temp.get(temp.size()-1).getX();
            y = temp.get(temp.size()-1).getY();
            lijn = lijn + Math.sqrt((x * x) + (y * y));
            if(lijn < afstand){
                afstand = lijn;
                Route = temp; 
            }
            lijn = 0;
        }
        Rekentijd = System.nanoTime() - startTime;
        return new Result(Route, afstand, Rekentijd);
    }

    public ArrayList<ArrayList<Product>> getRoutes() {
        return Routes;
    }

    /*@Override
    public String toString() {
        int aantal = 0;
        for(ArrayList<Product> temp : Routes){
            System.out.println("");
            System.out.println(aantal);
            for (Product temp2 : temp){
                System.out.print(temp2);
            }
            aantal++;
        }
        return "";
    }*/

    
}
