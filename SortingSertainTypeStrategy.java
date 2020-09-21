package budget;

import java.util.ArrayList;

public class SortingSertainTypeStrategy implements SortingStrategy {
    private String type;
    ArrayList<Purchase> concretePurchases = new ArrayList<>();
    public SortingSertainTypeStrategy(String type) {
        this.type = type;
    }
    @Override
    public void sort(ArrayList<Purchase> listPurchases) {
        for (Purchase purchase: listPurchases) {
            if (purchase.category.equals(type)) {
                concretePurchases.add(purchase);
            }
        }


        if (concretePurchases.size() == 0) {
            System.out.println();
            System.out.println("Purchase list is empty");
        } else {

            Purchase[] purchases = concretePurchases.toArray(new Purchase[0]);
            for (int i = 0; i < purchases.length - 1; i++) {
                for (int j = 0; j < purchases.length - 1 - i; j++) {
                    if (purchases[j].cost < purchases[j + 1].cost) {
                        Purchase tmp = purchases[j];
                        purchases[j] = purchases[j + 1];
                        purchases[j + 1] = tmp;
                    }
                }
            }
            double costSum = 0.0;
            System.out.println();
            System.out.printf("%s:\n", type);
            for (int i = 0; i < purchases.length; i++) {
                System.out.printf("%s $%.2f\n", purchases[i].name, purchases[i].cost);
                costSum += purchases[i].cost;
            }
            System.out.printf("Total sum: $%.2f\n",costSum);
        }
    }
}
