
package tsp;

import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;


public class Test {

   
    public static void main(String[] args) {
       
        Product kaas = new Product(1 , 5);
        Product melk = new Product(3 , 4);
        Product boter = new Product(2 , 4);
        Product ei = new Product(1 , 4);
        Product spek = new Product(5 , 3);
        /*Product kaas = new Product(3 , 5);
        Product melk = new Product(3 , 2);
        Product boter = new Product(1 , 4);
        Product ei = new Product(1 , 2);
        Product spek = new Product(5 , 3);*/
        ArrayList<Product> AList = new ArrayList<Product>();
        
        ProductList PList = new ProductList(AList);
        PList.AddProduct(kaas);
        PList.AddProduct(melk);
        PList.AddProduct(boter);
        PList.AddProduct(ei);
        PList.AddProduct(spek);
        //System.out.println(PList);
        
        Greedy A1 = new Greedy(PList.getList());
        Result R1 = A1.BerekenRoute();
        System.out.println(R1);
        
        //Hoofdscherm scherm = new Hoofdscherm(PList);
        //scherm.setVisible(true);
        ProductList P2List = new ProductList(AList);
        P2List.AddProduct(kaas);
        P2List.AddProduct(melk);
        P2List.AddProduct(boter);
        P2List.AddProduct(ei);
        P2List.AddProduct(spek);
        
        LimitedBruteForce Brute = new LimitedBruteForce(P2List.getList());
        Brute.BerekenRoute();

        
        ArrayList<ArrayList<Product>> Routes = Brute.getRoutes();
        System.out.println(Routes.get(0));
        
        }
    }
    

