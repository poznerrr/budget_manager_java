
package budget;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
public class Budget {
    File path = new File("./budget/purchases.txt");
    private ArrayList<Purchase> myPurchase = new ArrayList<>();
    private Map<Integer, String> typeOfPurchaseMap = Map.of(1,"Food",2,"Clothes",3,"Entertainment",4,"Other");
    private double income ;
    public double getIncome() {
        return income;
    }
    Scanner scanner = new Scanner(System.in);
    public int showMenu() {
        System.out.println("Choose your action:");
        System.out.println("1) Add income");
        System.out.println("2) Add purchase");
        System.out.println("3) Show list of purchases");
        System.out.println("4) Balance");
        System.out.println("5) Save");
        System.out.println("6) Load");
        System.out.println("7) Analyze (Sort)");
        System.out.println("0) Exit");
        int answer = scanner.nextInt();

        return this.menuHandler(answer);
    }
    private int menuHandler(int answer) {
        if (answer == 1) {
            addIncome();
            return 0;
        } else if (answer == 2) {
            addPurchase();
            return 0;
        } else if (answer == 3) {
            showListOfPurchase();
            return 0;
        } else if (answer == 4) {
            showBalance();
            return 0;
        } else if (answer == 5) {
            savePurchases();
            return 0;
        } else if (answer == 6) {
            loadPurchases();
            return 0;
        } else if (answer == 7) {
            analyzePurchase();
            return 0;
        } else if (answer == 0) {
          System.out.println();
          System.out.println("Bye!");
          return 1;
        } else {
            System.out.println();
            System.out.println("Unknown command!");
            return 0;
        }
    }
    private void showBalance() {
        System.out.println();
        System.out.printf("Balance: $%.2f\n", this.getIncome());
        System.out.println();
    }
    private void addIncome() {
        System.out.println();
        System.out.println("Enter income:");
        double debet = scanner.nextDouble();
        this.income += debet;
        System.out.println("Income was added!");
        System.out.println();
    }
    private void addPurchase() {
        System.out.println();
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) Back");
        int typeOfPurchase = scanner.nextInt();
        if (typeOfPurchase == 5) {
            System.out.println();
            return;
        } else {
            System.out.println();
            if (typeOfPurchaseMap.containsKey(typeOfPurchase)) {
                String category = typeOfPurchaseMap.get(typeOfPurchase);
                System.out.println("Enter purchase name:");
                scanner.nextLine();
                String name = scanner.nextLine();
                System.out.println("Enter its price:");
                double cost = scanner.nextDouble();
                myPurchase.add(new Purchase(name, cost, category));
                income -= cost;
                System.out.println("Purchase was added!\n");

            } else {
                System.out.println("Incorrect input");
            }
                addPurchase();
        }
    }
    private void showListOfPurchase() {
        System.out.println();
        System.out.println("Choose the type of purchase");
        System.out.println("1) Food");
        System.out.println("2) Clothes");
        System.out.println("3) Entertainment");
        System.out.println("4) Other");
        System.out.println("5) All");
        System.out.println("6) Back");
        int typeOfPurchase = scanner.nextInt();
        if (typeOfPurchase == 6) {
            System.out.println();
            return;
        } else if (typeOfPurchase == 5) {
            System.out.println();
            System.out.println("All:");
            if (myPurchase.size() == 0) {
                System.out.println("Purchase list is empty");
            } else {
                double total = 0.0d;
                for (Purchase purchase : myPurchase) {
                    System.out.printf("%s $%.2f\n", purchase.name, purchase.cost);
                    total += purchase.cost;
                }
                System.out.printf("Total sum: $%.2f\n", total);
                System.out.println();
                showListOfPurchase();
            }
        } else if (typeOfPurchaseMap.containsKey(typeOfPurchase)) {
            String category = typeOfPurchaseMap.get(typeOfPurchase);
            System.out.println();
            System.out.println(category + ":");
            double total = 0.0d;
            for (Purchase purchase : myPurchase) {
                if (purchase.category.equals(category)) {
                    System.out.printf("%s $%.2f\n", purchase.name, purchase.cost);
                    total += purchase.cost;
                }
            }
            System.out.printf("Total sum: $%.2f\n", total);
            System.out.println();
            showListOfPurchase();
        }
    }

    private void savePurchases() {
        try(PrintWriter txtWriter = new PrintWriter(path)) {
            txtWriter.println(income);
            for (Purchase purchase : myPurchase) {
                txtWriter.println(purchase.name);
                txtWriter.println(purchase.cost);
                txtWriter.println(purchase.category);
            }
            System.out.println();
            System.out.println("Purchases were saved");
            System.out.println();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    private void loadPurchases() {
        try(Scanner txtScanner = new Scanner(path)) {
         myPurchase.clear();
         if(txtScanner.hasNext()) {
             income = Double.parseDouble(txtScanner.nextLine());
         }
         while (txtScanner.hasNext()) {
             String name = txtScanner.nextLine();
             double cost= Double.parseDouble(txtScanner.nextLine());
             String category = txtScanner.nextLine();
             myPurchase.add(new Purchase(name, cost, category));
         }
         System.out.println();
         System.out.println("Purchases were loaded");
         System.out.println();

        }
        catch(Exception e) {
        System.out.println(e.getMessage() + path.getAbsolutePath());
        }
    }

    private void analyzePurchase() {
        SortingStrategy strategy = null;
        System.out.println();
        System.out.println("How do you want to sort?");
        System.out.println("1) Sort all purchases");
        System.out.println("2) Sort by type");
        System.out.println("3) Sort certain type");
        System.out.println("4) Back");

        int answer = scanner.nextInt();
        if (answer == 1) {
            strategy = new SortingAllStrategy();
        } else if (answer == 2) {
            strategy = new SortingByTypeStrategy();
        } else if (answer == 3) {
            System.out.println();
            System.out.println("Choose the type of purchase");
            System.out.println("1) Food");
            System.out.println("2) Clothes");
            System.out.println("3) Entertainment");
            System.out.println("4) Other");
            String categoryName = null;
            int category = scanner.nextInt();
            if (category == 1) {
                categoryName = "Food";
            } else if (category == 2) {
                categoryName = "Clothes";
            } else if (category == 3) {
                categoryName = "Entertainment";
            } else if (category == 4) {
                categoryName = "Other";
            } else {
                System.out.println("Incorrect Input");
                analyzePurchase();
            }
            strategy = new SortingSertainTypeStrategy(categoryName);
        } else if (answer == 4) {
            System.out.println();
            return;
        }

        strategy.sort(myPurchase);
        analyzePurchase();
    }


}
