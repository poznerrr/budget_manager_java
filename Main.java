package budget;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Budget budget = new Budget();
        int close = 0;
        while (close != 1) {
            close = budget.showMenu();
        }

    }
}
