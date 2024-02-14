package mortgage.planner;

import java.io.*;
import java.text.Normalizer;
import java.util.*;
import java.util.HashMap;

public class Utils {
    // Method to parse customers from a file and return a list of Customer objects
    public static List<Customer> parseCustomersFromFile(String filePath) {
        List<Customer> customers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            String[] headers = scanner.nextLine().split(","); /* find table headers */
            Map<String, Integer> headerIndices = getHeaderIndices(headers);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] customerData = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                /*
                 * do not split the data if it is in double quotation
                 */
                if (customerData.length == headers.length) {
                    String name = customerData[headerIndices.get("Customer")];
                    double totalLoans = Double.parseDouble((customerData[headerIndices.get("Total loan")]));
                    double interest = Double.parseDouble(customerData[headerIndices.get("Interest")]);
                    int years = Integer.parseInt(customerData[headerIndices.get("Years")]);
                    name = name.replaceAll("\"([^\"]*)\"", "$1").replace(",", " ");
                    /*
                    * Modify the name to remove quotes and replace commas with spaces
                     */
                    customers.add(new Customer(name, totalLoans, interest, years));
                } else {
                    System.err.println("Invalid data format: " + data);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        return customers;
    }
    public static void printCustomers(List<Customer> customers) {
        if (customers.isEmpty()) {
            System.out.println("Customer Not Found");
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println("Prospect " + (i + 1) + " " + customer.getCustomerName() + " wants to borrow " + customer.getTotalLoans() + " € for a period of "
                    + customer.getYears() + " years and pay approximately " + customer.getMonthlyPayment() + " € each month.");
        }
    }

    // Method to search customers by name with wildcard support
    public static List<Customer> searchCustomersByName(List<Customer> customers, String searchString) {
        List<Customer> matchingCustomers = new ArrayList<>();

        // Iterate through all customers
        for (Customer customer : customers) {
            String customerName = customer.getCustomerName();

            // Check if the customer name matches the search string with wildcard support
            if (isMatch(customerName, searchString)) {
                matchingCustomers.add(customer);
            }
        }

        return matchingCustomers;
    }

    // Method to check if a string matches the search string with wildcard support
    private static boolean isMatch(String str, String searchString) {
        // Convert the search string to regex pattern
        String regex = ".*" + searchString.toLowerCase().replace("*", ".*") + ".*";
        return removeDiacritics(str.toLowerCase()).matches(removeDiacritics(regex));
    }
    private static String removeDiacritics(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase(Locale.ENGLISH);
    }
    public static String enterName(Scanner scanner) {
        String searchItem;
        do {
            /*
             * ask for customer name again if only pressed enter,
             * or entered a symbol
             */
            System.out.println("Enter customer name. Enter exit to stop: ");
            searchItem = scanner.nextLine().trim();
        } while (searchItem.isEmpty() || !searchItem.matches(".*[a-zA-Z0-9].*"));

        return searchItem;
    }
    public static String showInfo(Scanner scanner) {
        String choice;
        //boolean userChoice = true;
        do {
            /*
             * ask if the user wants to access all customer info or search
             */
            System.out.println("Do you want to see all data or search? Type all/search . Enter exit to stop: ");
            choice = scanner.nextLine().trim();

        } while (choice.isEmpty() || !choice.equalsIgnoreCase("all")
                && !choice.equalsIgnoreCase("search")
                && !choice.equalsIgnoreCase("exit"));


        return choice;
    }
    private static Map<String, Integer> getHeaderIndices(String[] headers) {
        Map<String, Integer> headerIndices = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            headerIndices.put(headers[i], i);
        }
        return headerIndices;
    }

}
