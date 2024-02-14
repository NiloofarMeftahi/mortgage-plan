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
        /* calculate without using math.pow */
        double powerCalcualtion = 1.0;
        double monthlyInterest = this.interest / 100 / 12;
        for (int i = 0; i <  (this.years * 12); i++) {
            powerCalcualtion *= (1 + monthlyInterest);
        }
        double monthlyPayment = this.totalLoans * (monthlyInterest * powerCalcualtion) / (powerCalcualtion - 1);
        return customRoundNumber(monthlyPayment);
    }
    public double customRoundNumber(double value) {
        /* Round to two decimal points without using the math library */
        int scaledValue = (int) (value * 1000); /* Multiply by 1000 to shift the decimal point */
        int lastThreeDigits = scaledValue % 1000; /* Extract three decimal points */

        if (lastThreeDigits % 10 >= 5) {
            /* Round up if the last digit is 5 or greater */
            scaledValue += 10 - (lastThreeDigits % 10);
        } else {
            /* Round down if last digit is lower than 5 */
            scaledValue -= lastThreeDigits % 10;
        }

        // Round to two decimal points
        return scaledValue / 1000.0;
    }

}
