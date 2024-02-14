package mortgage.planner;

public class Customer {
    // Properties
    private String customerName;
    private double totalLoans;
    private double interest;
    private int years;

    // Constructor
    public Customer() {
    }
    public Customer(String customerName, double totalLoans, double interest, int years) {
        this.customerName = customerName;
        this.totalLoans = totalLoans;
        this.interest = interest;
        this.years = years;
    }

    // Getters and Setters
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(double totalLoans) {
        this.totalLoans = totalLoans;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }
    public double getMonthlyPayment() {
        double monthlyInterest = this.interest / 100 / 12;
        double monthlyPayement = this.totalLoans * monthlyInterest /
                (1 - Math.pow(1 + monthlyInterest, - (this.years * 12)));
        return monthlyPayement;
    }
}
