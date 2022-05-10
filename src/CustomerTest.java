import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    ArrayList<Customer> _customer = new ArrayList<>();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        _customer.add(new Customer("c1"));
        _customer.add(new Customer("c2"));
        Movie regular = new Movie("m1", Movie.REGULAR);
        Movie new_release = new Movie("m2", Movie.NEW_RELEASE);
        Movie childrens = new Movie("m3", Movie.CHILDRENS);
        Rental r1 = new Rental(regular, 1); // += 2
        Rental r2 = new Rental(regular, 6); // += 8
        Rental n1 = new Rental(new_release, 1); // += 3
        Rental n2 = new Rental(new_release, 3); // += 9
        Rental c1 = new Rental(childrens, 1); // += 1.5
        Rental c2 = new Rental(childrens, 7); // += 7.5
        for (int i = 0; i < _customer.size(); i++) {
            addAllRentals(_customer.get(i), r1, r2, n1, n2, c1, c2);
        }
    }

    private void addAllRentals(Customer customer, Rental... rentals){
        for (int i = 0; i < rentals.length; i++) {
            customer.addRental(rentals[i]);
        }
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @Test
    void statement() {
        String name;
        double totalAmount = 31;
        int frequentRenterPoints = 7;
        for (int i = 0; i < _customer.size(); i++) {
            String s = _customer.get(i).statement();
            name = i == 0 ? "c1" : "c2";
            assertEquals("Rental Record for " + name + "\n"
            + "\t" + "m1" + "\t" + String.valueOf((double)2) + "\n"
                    + "\t" + "m1" + "\t" + String.valueOf((double)8) + "\n"
                    + "\t" + "m2" + "\t" + String.valueOf((double)3) + "\n"
                    + "\t" + "m2" + "\t" + String.valueOf((double)9) + "\n"
                    + "\t" + "m3" + "\t" + String.valueOf((double)1.5) + "\n"
                    + "\t" + "m3" + "\t" + String.valueOf((double)7.5) + "\n"
            + "Amount owed is " + String.valueOf(totalAmount) + "\n"
            + "You earned " + String.valueOf(frequentRenterPoints) + " frequent renter points",
                    s,
                    "customer" + (i + 1) + " failed");
        }
    }
}