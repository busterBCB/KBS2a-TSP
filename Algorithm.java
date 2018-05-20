
package tsp;

import java.util.ArrayList;


public abstract class Algorithm implements RunnableAlgorithm{
    private String Name;
    
    public Algorithm(String Name){
        this.Name = Name;
    }
    
    @Override
    public String toString() {
        return Name;
    }
    
    
}
