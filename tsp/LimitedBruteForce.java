
package tsp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class LimitedBruteForce extends Algorithm{
    private ArrayList<Product> Products = new ArrayList<Product>();
    private ArrayList<Product> ProductsBackup = new ArrayList<Product>();
    private ArrayList<ArrayList<Product>> Routes = new ArrayList<ArrayList<Product>>();
    public ArrayList<Product> Route = new ArrayList<Product>(25);
    private ArrayList<Product> Tijdelijk = new ArrayList<Product>();
    private Product laatste;
    private double afstand;
    private long Rekentijd;
    
    public LimitedBruteForce(ArrayList<Product> P) {
        super("Limited Brute-Force");
        Products = P;
        //ProductsBackup = P;
    }
    
    public void BerekenRoute() {
        long startTime = System.nanoTime();
        int x;
        int y;
        boolean add = true;
        Routes.add(Products);
        //Product[] Product = Products.toArray(new Product[Products.size()]);
        //ArrayList<Product> Tijdelijk = new ArrayList<Product>();
        for (int i = 100; i > Routes.size();) {
            for (int ii = -1; ii < Products.size()-1;) {
                int randomNum = ThreadLocalRandom.current().nextInt(0, Products.size());
                Tijdelijk.add(Products.get(randomNum));
                Products.remove(randomNum);
                System.out.println("hello");
            }
            for (ArrayList<Product> temp : Routes){
                if(temp == Tijdelijk){
                    //System.out.println("bam");
                    add = false;
                }
            }
            if(add){
                Routes.add(Tijdelijk);
                add = true;
            }
            Tijdelijk.clear();
            Products = ProductsBackup;
        }
        System.out.println(Routes.size());
    }

    public ArrayList<ArrayList<Product>> getRoutes() {
        return Routes;
    }

    
}
