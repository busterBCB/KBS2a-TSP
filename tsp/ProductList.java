
package tsp;

import java.util.ArrayList;


public class ProductList {
    private ArrayList<Product> List = new ArrayList<Product>();
    
    public ProductList(ArrayList<Product> L){
        List = L;
    }

    public void AddProduct(Product P){
        List.add(P);
    }

    public ArrayList<Product> getList() {
        return List;
    }
    
    @Override
    public String toString() {
        return "ProductList{" + "List=" + List + '}';
    }
    
    
}
