package de.datev.bank;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Test {

  public static void main(String[] args) {
    UUID customerId = UUID.randomUUID();
    String firstname = "Ottokar";
    String lastname = "Domma";
    String dateString = "1980-06-15T00:00:00Z";
    ZonedDateTime dateOfBirth = ZonedDateTime.parse(dateString);

    Customer customer = new Customer(
        customerId,
        firstname,
        lastname,
        dateOfBirth);

    Customer customer2 = new Customer(
        customerId,
        "Ray",
        lastname,
        dateOfBirth);

    System.out.println(customer);

    boolean customersAreEqual = customer.equals(customer2);

    System.out.println("Sind die Kunden gleich? " + customersAreEqual);
//
//    Account account = new Account("DE4711", customer);
//
//    System.out.println(account);
//
//    account.deposit(BigDecimal.valueOf(147.23));
//    System.out.println(account);
//
//    account.withdraw(BigDecimal.valueOf(250));
//    System.out.println(account);
  }
}
