import java.io.File;
import java.io.FileNotFoundException;
import java.text.Normalizer;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner userInputScanner = new Scanner(System.in);
        String searchItem = enterName(userInputScanner);
        searchCustomer(searchItem);


    }
    private static String enterName(Scanner scanner) {
        String searchItem;
        do {
            System.out.print("Enter customer name: ");
            searchItem = scanner.nextLine().trim();
        } while (searchItem.isEmpty() || !searchItem.matches(".*[a-zA-Z0-9].*"));

        return searchItem;
    }

    private static void searchCustomer(String searchItem){
        String customerToSearch = removeDiacritics(searchItem).replaceAll("[,\\s-_\\*]+", "").trim().toLowerCase();

        try {
            File file = new File("./prospects.txt");
            Scanner scanner = new Scanner(file);
            String[] headers = scanner.nextLine().split(",");
            boolean customerFound = false;
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] customerData = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String preparedCustomerName = removeDiacritics(customerData[0]).replaceAll("[,\\s-_\\*]", "").trim().toLowerCase();

                if (!preparedCustomerName.isEmpty() && preparedCustomerName.contains(customerToSearch)){
                    customerFound = true;
                    double totalLoan = Double.parseDouble(customerData[1].trim());
                    double interest = Double.parseDouble(customerData[2].trim());
                    int years = Integer.parseInt(customerData[3].trim());
//                    double years = Double.parseDouble(customerData[3].trim()); ?
                    System.out.println("Customer: " + customerData[0].replaceAll("[,\\s\"]", " ").trim());
                    System.out.println("Total Loan: " + totalLoan);
                    System.out.println("Interest: " + interest);
                    System.out.println("Years: " + years);
                    double monthlyPayment = monthlyPayment(totalLoan, interest, years * 12);
                    System.out.println("Monthly Payment: " + monthlyPayment);
                    break;

                }



            }
            if(!customerFound) {
                System.err.println("Customer not found.");
            }



        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
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