
package tsp;

import java.util.ArrayList;
import java.util.Random;


public class ProductList {
    private ArrayList<Product> List = new ArrayList<Product>();
    
    public ProductList(){
        
    }

    public void AddProduct(Product P){
        List.add(P);
    }

    public ArrayList<Product> getList() {
        return (ArrayList<Product>)List.clone();
    }
    
    public void RandomSet(int aantal){
        List.clear();
        Random rand = new Random();
        int x = rand.nextInt(5) + 1;
        int y = rand.nextInt(5) + 1;
        Product Tijdelijk = new Product(x,y);
        List.add(Tijdelijk);
        for(int i = aantal; i > List.size();){
            boolean gelijk = false;
            x = rand.nextInt(5) + 1;
            y = rand.nextInt(5) + 1;
            Tijdelijk = new Product(x,y);
            for(Product temp : List){
                int x1 = temp.getX();
                int y1 = temp.getY();
                int x2 = Tijdelijk.getX();
                int y2 = Tijdelijk.getY();
                if(x1 == x2 && y1 == y2){
                    gelijk = true;
                }
            }
            if(gelijk == false){
                List.add(Tijdelijk);
            }    
        }

        
        
        
        
        
        
        
        
        /*
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
                }*/
    }
    @Override
    public String toString() {
        return "ProductList{" + "List=" + List + '}';
    }
    
    
}
