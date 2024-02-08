import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        try {
            File file = new File("./prospects.txt");
            Scanner scanner = new Scanner(file);

            String[] headers = scanner.nextLine().split(",");
            for (String header : headers) {
                System.out.println(header);
            }
            while (scanner.hasNextLine()){
                String data = scanner.nextLine();
                String[] customerData = data.split(",");
                for (String customer : customerData) {
                    System.out.println(customer);
                }
            }



        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }
}