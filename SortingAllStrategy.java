package budget;

import java.util.ArrayList;

public class SortingAllStrategy implements SortingStrategy {

    @Override
    public void sort(ArrayList<Purchase> listPurchases) {
        if (listPurchases.size() == 0) {
            System.out.println();
            System.out.println("Purchase list is empty");
        } else {

            Purchase[] purchases = listPurchases.toArray(new Purchase[0]);
            for (int i = 0; i < purchases.length - 1; i++) {
                for (int j =0; j < purchases.length - 1 - i; j++) {
                    if (purchases[j].cost < purchases[j + 1].cost) {
                        Purchase tmp = purchases[j];
                        purchases[j] = purchases[j+1];
                        purchases[j+1] = tmp;
                    }
                }
            }

            System.out.println();
            System.out.println("All");
            for (int i = 0; i < purchases.length; i++) {
                System.out.printf("%s $%.2f\n", purchases[i].name, purchases[i].cost);
            }
        }
    }
}
