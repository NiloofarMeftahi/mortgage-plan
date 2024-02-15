# Mortgage Plan
Mortgage Plan is a Java-based application designed to assist users in managing mortgage-related information. This application allows users to read customer data from a text file, calculate monthly payments, and perform customer searches.
## Features
- Calculating fixed monthly payments and displaying all customers' names,  total loans,  years of payments, and monthly payments.
- Searching for a particular customer and displaying their information.
- Supporting wildcards, searching without diacritics, and parsing complex text files.
- Unit testing for different scenarios.
## Prerequisites
Ensure you have the following installed:

- Java Development Kit (JDK)
- Any Java IDE (Optional)
- JUnit (for running unit tests)


## Instalation
- Clone the repository: git clone https://github.com/NiloofarMeftahi/mortgage-plan
- Open the project in your preferred Java IDE.
- Run the Main class to start the application.
## Usage
Upon running the application, users will be prompted to choose between viewing all customer data or conducting a search. Follow the on-screen instructions to navigate through the application.
### Viewing All Customer Data
If the user chooses to view all data, the application will display information about all customers in the format:
Prospect [Number]: [CustomerName] wants to borrow [TotalLoan] € for a period of [Years] years and pay approximately [MonthlyPayment] € each month.
### Searching for Customers
If the user chooses to search, they can enter a name or a wildcard pattern to search for specific customers. The application will display the matching results in the same format as above. The application also supports searching for names with diacritics using the English alphabet.

### Exiting the Application
To exit the application, simply type "exit" when prompted.

## Testing
The project includes a set of unit tests to ensure its functionality. These tests cover file parsing, customer search, and mathematical calculations. To run the tests, execute the UnitTest class.
