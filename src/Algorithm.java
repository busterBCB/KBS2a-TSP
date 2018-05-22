package TSP1;

public abstract class Algorithm implements RunnableAlgorithm {

    private final String Name;

    public Algorithm(String Name) {
        this.Name = Name;
    }

    @Override
    public String toString() {
        return Name;
    }

}
