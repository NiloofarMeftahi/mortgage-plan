import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        boolean continueSearch = true; // var check for continuing the program
        boolean showAll;
        //System.out.println(showInfo(userInputScanner));
        String searchItem;

        while (continueSearch){
            String userChoice = showInfo(userInputScanner);
            if (userChoice.equalsIgnoreCase("search")) {
                searchItem = enterName(userInputScanner); /* ask for the name of the customer */
                showAll = false;
            }
            else {
                searchItem = userChoice; /* so if user put exit at the beginning program stops */
                showAll = true;
            }

            if (searchItem.equalsIgnoreCase("exit")) {  /* if entered exit, stop the program */
                break;
            }

            try {
                searchCustomer(searchItem, showAll); /* search for the entered name */
            } catch (FileNotFoundException e) {  /* if file not found stop the program */
                continueSearch = false;
            }
        }
    }
    private static String enterName(Scanner scanner) {
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
    private static String showInfo(Scanner scanner) {
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

    private static void searchCustomer (String searchItem, boolean showAll) throws FileNotFoundException{
        int prospectNum = 0;
        String customerToSearch = removeDiacritics(searchItem)
                .replaceAll("[,\\s-_\\*]+", "").trim().toLowerCase();
        /*
        * remove diacritics, comma, dash, underline and star
        * from the name entered for search.
        * transform to lowercase
        */

        try {
            File file = new File("./prospects.txt");
            Scanner scanner = new Scanner(file);
            String[] headers = scanner.nextLine().split(","); /* find table headers */
            Map<String, Integer> headerIndices = getHeaderIndices(headers);
            boolean customerFound = false;
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] customerData = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                /*
                * do not split the data if it is in double quotation
                 */
                if (customerData.length >= headers.length) { /* check if customerData contains enough elements */
                    String preparedCustomerName = removeDiacritics(customerData[headerIndices.get("Customer")])
                            .replaceAll("[,\\s-_\\*]", "").trim().toLowerCase();

                    if ((!preparedCustomerName.isEmpty() && preparedCustomerName.contains(customerToSearch) && !showAll)
                            || (!preparedCustomerName.isEmpty() && showAll)){
                        customerFound = true;
                        printCustomerData(customerData, headerIndices, ++prospectNum);

                    }
                }

            }
            if(!customerFound) {
                System.out.println("Customer Not Found ");

            }

        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
            throw e;
        }
    }

    private static void printCustomerData(String[] customerData, Map<String, Integer> headerIndices, int prospectNumber) {
        String customer = customerData[headerIndices.get("Customer")]
                .replaceAll("[,\\s\"]", " ").trim();
        /*
         * prepare customer data for printing
         */
        double totalLoan = Double.parseDouble(customerData[headerIndices.get("Total loan")].trim());
        double interest = Double.parseDouble(customerData[headerIndices.get("Interest")].trim());
        int years = Integer.parseInt(customerData[headerIndices.get("Years")].trim());
//                    double years = Double.parseDouble(customerData[3].trim()); ?
//        System.out.println("Customer: " + customer );
//
//        System.out.println("Total Loan: " + totalLoan);
//        System.out.println("Interest: " + interest);
//        System.out.println("Years: " + years);
        double monthlyPayment = monthlyPayment(totalLoan, interest, years * 12);
        /*
         * calculate the monthly payment
         */
//        System.out.println("Monthly Payment: " + monthlyPayment);
        System.out.println("Prospect " + prospectNumber + " " + customer + " wants to borrow " + totalLoan + " € for a period of "
                + years + " years and pay approximately " + monthlyPayment + " € each month.");
    }
    private static Map<String, Integer> getHeaderIndices(String[] headers) {
        Map<String, Integer> headerIndices = new HashMap<>();
        for (int i = 0; i < headers.length; i++) {
            headerIndices.put(headers[i], i);
        }
        return headerIndices;
    }
    private static String removeDiacritics(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase(Locale.ENGLISH);
    }
   private static  double monthlyPayment(double totalLoan, double interest, int numberOfPayments){
        double monthlyInterest = interest / 100 / 12;
        double monthlyPayement = totalLoan * monthlyInterest /
                (1 - Math.pow(1 + monthlyInterest, -numberOfPayments));
        return monthlyPayement;
   }
}