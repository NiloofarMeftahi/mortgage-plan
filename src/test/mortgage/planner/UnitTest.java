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

}
