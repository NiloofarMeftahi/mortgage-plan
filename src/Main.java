
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        var allCustomers = Utils.parseCustomersFromFile("./prospects.txt");
        if (allCustomers.isEmpty()) {
            return;
        }
        Scanner userInputScanner = new Scanner(System.in);
        while (true) {
            String userChoice = Utils.showInfo(userInputScanner);
            if (userChoice.equalsIgnoreCase("all")){
                Utils.printCustomers(allCustomers);
            }
            else if (userChoice.equalsIgnoreCase("search")) {
                var searchItem = Utils.enterName(userInputScanner);
                var filteredCustomer = Utils.searchCustomersByName(allCustomers, searchItem + "*");
                Utils.printCustomers(filteredCustomer);
            }
            if (userChoice.equalsIgnoreCase("exit")) {  /* if entered exit, stop the program */
                break;
            }
        }

    }

}