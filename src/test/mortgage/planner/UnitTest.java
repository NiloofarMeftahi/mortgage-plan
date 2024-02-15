package test.mortgage.planner;

import org.junit.Test;
import mortgage.planner.*;

import java.util.ArrayList;
import java.util.List;

import static mortgage.planner.Utils.parseCustomersFromFile;
import static mortgage.planner.Utils.searchCustomersByName;
import static org.junit.Assert.assertEquals;

public class UnitTest {

    @Test
    public void testParseCustomersFromFile_SimpleTest_ShouldReturn3Records() {
        // Prepare
        String filename = "./prospects-01.txt"; // Assuming this file exists in the test resources

        // Execute
        List<Customer> customers = parseCustomersFromFile(filename);

        // Verify
        assertEquals(3, customers.size());

        // Verify customer details
        assertEquals("Juha", customers.get(0).getCustomerName());
        assertEquals(1000.0, customers.get(0).getTotalLoans(),0.02);
        assertEquals(5.0, customers.get(0).getInterest(), 0.02);
        assertEquals(2, customers.get(0).getYears());

        assertEquals("Karvinen", customers.get(1).getCustomerName());
        assertEquals(4356.0, customers.get(1).getTotalLoans(), 0.02);
        assertEquals(1.27, customers.get(1).getInterest(), 0.02);
        assertEquals(6, customers.get(1).getYears());

        assertEquals("Claes Månsson", customers.get(2).getCustomerName());
        assertEquals(1300.55, customers.get(2).getTotalLoans(), 0.02);
        assertEquals(8.67, customers.get(2).getInterest(), 0.02);
        assertEquals(2, customers.get(2).getYears());
    }
    @Test
    public void testParseCustomersFromFile_ComplexTest_ShouldReturn1Record() {
        // Prepare
        String filename = "./prospects-02.txt"; // Assuming this file exists in the test resources

        // Execute
        List<Customer> customers = parseCustomersFromFile(filename);

        // Verify
        assertEquals(1, customers.size());

        // Verify customer details
        assertEquals("Clarencé Andersson", customers.get(0).getCustomerName());
        assertEquals(2000, customers.get(0).getTotalLoans(), 0.02);
        assertEquals(6, customers.get(0).getInterest(), 0.02);
        assertEquals(4, customers.get(0).getYears());
    }

    @Test
    public void testSearchCustomersByName_SimpleTest() {
        // Prepare
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Harry Potter", 10000.0, 0.05, 5));
        customers.add(new Customer("Hermione Granger", 15000.0, 0.07, 3));
        customers.add(new Customer("Ron Weasley", 12000.0, 0.06, 4));

        // Execute
        List<Customer> matchingCustomers1 = searchCustomersByName(customers, "H*");
        List<Customer> matchingCustomers2 = searchCustomersByName(customers, "Hermione*");
        List<Customer> matchingCustomers3 = searchCustomersByName(customers, "*Weasley");

        // Verify
        assertEquals(2, matchingCustomers1.size());
        assertEquals(1, matchingCustomers2.size());
        assertEquals(1, matchingCustomers3.size());

        assertEquals("Harry Potter", matchingCustomers1.get(0).getCustomerName());
        assertEquals("Hermione Granger", matchingCustomers1.get(1).getCustomerName());
        assertEquals("Hermione Granger", matchingCustomers2.get(0).getCustomerName());
        assertEquals("Ron Weasley", matchingCustomers3.get(0).getCustomerName());
    }
    @Test
    public void testSearchCustomersByName_ComplexTest() {
        // Prepare
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Éowyn Elfsheen", 20000.0, 0.1, 2));

        // Execute
        List<Customer> matchingCustomers1 = searchCustomersByName(customers, "Éowyn");
        List<Customer> matchingCustomers2 = searchCustomersByName(customers, "Eowyn");


        // Verify
        assertEquals(1, matchingCustomers1.size());
        assertEquals(1, matchingCustomers2.size());

        assertEquals("Éowyn Elfsheen", matchingCustomers1.get(0).getCustomerName());
        assertEquals("Éowyn Elfsheen", matchingCustomers2.get(0).getCustomerName());
    }

    @Test
    public void testCustomRoundNumber() {
        // Prepare
        Customer customer = new Customer();

        // Execute
        double result1 = customer.customRoundNumber(43.8713897340686);
        double result2 = customer.customRoundNumber(62.86631476623255);

        // Verify
        assertEquals(43.87, result1, 0.001);
        assertEquals(62.87, result2, 0.001);

    }
    @Test
    public void testGetMonthlyPayment() {
        // Prepare
        Customer customer = new Customer();
        customer.setTotalLoans(1500.0);
        customer.setInterest(1.4);
        customer.setYears(3);

        // Execute
        double monthlyPayment = customer.getMonthlyPayment();

        // Verify
        assertEquals(42.57, monthlyPayment, 0.01);
    }

}
