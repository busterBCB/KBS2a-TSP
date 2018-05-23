package TSP1;

import java.util.ArrayList;

public class Greedy extends Algorithm {

    private ArrayList<Product> Products;
    public ArrayList<Product> Route = new ArrayList<Product>(25);
    private long Rekentijd;

    public Greedy() {
        super("Greedy");
    }

    @Override
    public Result run(ArrayList<Product> P) {
        Products = (ArrayList<Product>) P.clone();
        Route.clear();

        // Rekentijd bijhouden
        long startTime = System.nanoTime();
        int x;
        int y;

        double shortestDistance = 20;
        Product startProduct = null;
        double routeDistance = 0;

        // begin punt berekenen
        for (Product temp : Products) {
            x = temp.getX();
            y = temp.getY();

            // afstand van startpunt tot eerste pakketje berekenen
            double distanceToStart = Math.sqrt((x * x) + (y * y));

            // de kortste afstand onthouden
            if (distanceToStart < shortestDistance) {
                shortestDistance = distanceToStart;
                startProduct = temp;
            }
        }

        if (startProduct != null) {
            routeDistance += shortestDistance;
            Products.remove(startProduct);
        }
        // eerste pakketje toevoegen
        Route.add(startProduct);

        shortestDistance = 20;
        double distanceToPrevProduct;

        int aantalRoute = 1;
        int productIndex = 0;

        for (int i = 0; i < Products.size();) {

            for (Product temp : Products) {
                x = temp.getX();
                y = temp.getY();

                int x1 = Route.get(productIndex).getX();
                int y1 = Route.get(productIndex).getY();

                int difX = Math.abs(x - x1);
                int difY = Math.abs(y - y1);

                // afstand berekeken
                if (difX == 0 || difY == 0) {
                    distanceToPrevProduct = difX + difY;
                } else {
                    distanceToPrevProduct = Math.sqrt((difX * difX) + (difY * difY));
                }

                if (distanceToPrevProduct < shortestDistance) {
                    shortestDistance = distanceToPrevProduct;
                    if (Route.size() == aantalRoute) {
                        Route.add(temp);
                    } else {
                        Route.set(aantalRoute, temp);
                    }
                }

            }
            routeDistance += shortestDistance;
            shortestDistance = 20;
            Products.remove(Route.get(aantalRoute));
            aantalRoute++;
            productIndex++;
        }

        // de afstand vanaf het laatste pakketje naar het startpunt berekenen
        x = Route.get(Route.size() - 1).getX();
        y = Route.get(Route.size() - 1).getY();
        routeDistance += Math.sqrt((x * x) + (y * y));

        Rekentijd = System.nanoTime() - startTime;
        return new Result((ArrayList<Product>) Route.clone(), routeDistance, Rekentijd, "Greedy");
    }
}
