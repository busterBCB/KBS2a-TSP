package TSP1;

import java.util.ArrayList;

public class ResultSet {

    private ArrayList<ArrayList<Result>> ResultSet = new ArrayList<ArrayList<Result>>();

    public ResultSet() {

    }

    public void addResult(ArrayList<Result> R) {
        ResultSet.add((ArrayList<Result>) R.clone());
    }

    public Result getBest() {
        // get beste route
        double Afstand = 0;
        double ShortestDistance = 1000;
        Result BestResult = new Result();
        for (ArrayList<Result> temp : ResultSet) {
            for (Result temp2 : temp) {
                Afstand = temp2.getAfstand();
                if (Afstand < ShortestDistance) {
                    ShortestDistance = Afstand;
                    BestResult = temp2;
                }
            }
        }
        return BestResult;
    }

    public Result getBest(int ronde) {
        // get beste route van bepaalde ronde
        double Afstand = 0;
        double ShortestDistance = 1000;
        Result BestResult = new Result();
        for (Result temp : ResultSet.get(ronde)) {
            Afstand = temp.getAfstand();
            if (Afstand < ShortestDistance) {
                ShortestDistance = Afstand;
                BestResult = temp;
            }
        }
        return BestResult;
    }

    public ArrayList<Result> getAlgorithms() {
        return ResultSet.get(0);

    }

    public int getRondes() {
        return ResultSet.size();
    }

    public int getWins(String algoritme) {
        int wins = 0;
        for (ArrayList<Result> temp : ResultSet) {
            double afstand = 100;
            int aantal = 0;
            int aantal1 = 0;
            for (Result temp2 : temp) {
                if (temp2.getAfstand() < afstand) {
                    afstand = temp2.getAfstand();
                    aantal1 = aantal;
                }
                aantal++;
            }
            if (algoritme == temp.get(aantal1).getAlgorithm()) {
                wins++;
            }
        }
        return wins;
    }

    public int getBestRonde() {
        // get ronde met beste Route
        int Ronde = 0;
        for (ArrayList<Result> temp : ResultSet) {
            for (Result temp2 : temp) {
                if (temp2.getAfstand() == this.getBest().getAfstand()) {
                    return Ronde;
                }
            }
            Ronde++;
        }
        return 0;
    }

    public Result getSpecific(int ronde, String algorithm) {
        //specifiek resultaat uit bepaalde ronde halen
        int Ronde = 0;
        for (ArrayList<Result> temp : ResultSet) {
            if (Ronde == ronde) {
                for (Result temp2 : temp) {
                    if (temp2.getAlgorithm() == algorithm) {
                        return temp2;
                    }
                }
            }
            Ronde++;
        }
        return this.getBest();
    }

    @Override
    public String toString() {
        int aantal = 1;
        for (ArrayList<Result> temp : ResultSet) {
            System.out.println("Ronde " + aantal);
            for (Result temp2 : temp) {
                System.out.println(temp2);
            }
            aantal++;
        }
        System.out.println("");
        return "ResultSet{" + "ResultSet=" + ResultSet + '}' + "\n\n";
    }

}
