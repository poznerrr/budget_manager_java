package budget;

import java.util.ArrayList;
import java.util.TreeMap;

public class SortingByTypeStrategy implements  SortingStrategy {
    @Override
    public void sort(ArrayList<Purchase> purchases) {
        //declare and fill TreeMap
        TreeMap <String ,Double> categoryCost = new TreeMap<String, Double>();
        categoryCost.put("Food" ,0.0);
        categoryCost.put("Clothes" ,0.0);
        categoryCost.put("Entertainment" ,0.0);
        categoryCost.put("Other" ,0.0);
        for (Purchase purchase : purchases) {
            categoryCost.put(purchase.category, categoryCost.get(purchase.category) + purchase.cost);

        }

        //sorting values in TreeMap
        String[] categories = categoryCost.keySet().toArray(new String[0]);
        Double[] costs = categoryCost.values().toArray(new Double[0]);
        for (int i = 0; i < categories.length - 1; i++) {
            for (int j = 0; j < categories.length - i - 1; j++) {
                if (costs[j] < costs[j + 1]) {
                    String tmp = categories[j];
                    categories[j] = categories[j + 1];
                    categories[j + 1] = tmp;

                    double tmpCost = costs[j];
                    costs[j] = costs[j + 1];
                    costs[j + 1] = tmpCost;
                }
            }
        }

        double costAll = 0.0;

        System.out.println();
        System.out.println("Types:");
        for (int i = 0; i < categories.length; i++) {
            System.out.printf("%s - $%.2f\n", categories[i], costs[i]);
            costAll += costs[i];
        }
        System.out.printf("Total sum: $%.2f\n", costAll);
    }
}
