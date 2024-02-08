import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String searchItem = "Claes Månsson";
        String customerToSearch = searchItem.replaceAll("[,\\s]", "").trim().toLowerCase();
        try {
            File file = new File("./prospects.txt");
            Scanner scanner = new Scanner(file);
            String[] headers = scanner.nextLine().split(",");
            boolean customerFound = false;
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] customerData = data.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                String preparedCustomerName = customerData[0].replaceAll("[,\\s]", "").trim().toLowerCase();

                if (preparedCustomerName.contains(customerToSearch)){
                    customerFound = true;
                    double totalLoan = Double.parseDouble(customerData[1].trim());
                    double interest = Double.parseDouble(customerData[2].trim());
                    int years = Integer.parseInt(customerData[3].trim());
//                    double years = Double.parseDouble(customerData[3].trim()); ?
                    System.out.println("Customer: " + customerData[0].trim());
                    System.out.println("Total Loan: " + totalLoan);
                    System.out.println("Interest: " + interest);
                    System.out.println("Years: " + years);
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
}