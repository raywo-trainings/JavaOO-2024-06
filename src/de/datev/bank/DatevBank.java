package de.datev.bank;

import de.datev.bank.account.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class DatevBank {

  public static void main(String[] args) {
    BankSystem system = new BankSystem();

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

    system.addCustomer(customer);

    System.out.println(customer.toString());

    System.out.println(system.getCustomers());

    try {
      CurrentAccount currentAccount = new CurrentAccount("DE4711", customer);
      system.addAccount(currentAccount);

      currentAccount.setLimit(BigDecimal.valueOf(500));
      currentAccount.setDebitInterestRate(11.25f);

      currentAccount.deposit(BigDecimal.valueOf(100.14));
      currentAccount.withdraw(BigDecimal.valueOf(2000));

      System.out.println(currentAccount);

    } catch (AmountNotAvailableException exception) {
      System.out.println(exception.getMessage());
      System.out.println("Verfügbar wäre: " + exception.getFormattedAvailableAmount());
    } catch (AmountNegativeException exception) {
      System.out.println(exception.getMessage());
    }

    try {
      SavingsAccount savingsAccount = new SavingsAccount("DE4712", customer);
      system.addAccount(savingsAccount);

      savingsAccount.setInterestRate(0.75f);

      savingsAccount.deposit(BigDecimal.valueOf(200));

      System.out.println(savingsAccount);
    } catch (AmountNegativeException e) {
      System.out.println(e.getMessage());
    }

    system.addAccount(new CurrentAccount("DE4713", customer));
    system.addAccount(new CurrentAccount("DE4718", customer));
    system.addAccount(new CurrentAccount("DE4714", customer));
    system.addAccount(new CurrentAccount("DE4719", customer));
    system.addAccount(new CurrentAccount("DE4715", customer));

    System.out.println("\n****** sortiert ******");
    for (Account a : system.getSortedAccounts(SortOrder.ASCENDING)) {
      System.out.println(a);
    }

    System.out.println("\n****** Original ******");
    for (Account a : system.getAccounts()) {
      System.out.println(a);
    }

    customerShenanigans(system);
  }


  private static void customerShenanigans(BankSystem system) {
    String lastname = "Domma";
    String dateString = "1980-06-15T00:00:00Z";
    ZonedDateTime dateOfBirth = ZonedDateTime.parse(dateString);

    Customer customer = new Customer(
        UUID.randomUUID(),
        "Adalbert",
        lastname,
        dateOfBirth);

    system.addCustomer(customer);

    customer = new Customer(
        UUID.randomUUID(),
        "Bärbel",
        lastname,
        dateOfBirth);

    system.addCustomer(customer);

    customer = new Customer(
        UUID.randomUUID(),
        "Christopher",
        lastname,
        dateOfBirth);

    system.addCustomer(customer);

    customer = new Customer(
        UUID.randomUUID(),
        "Desiree",
        lastname,
        dateOfBirth);

    system.addCustomer(customer);

    customer = new Customer(
        UUID.randomUUID(),
        "Fridolin",
        lastname,
        dateOfBirth);

    system.addCustomer(customer);

    List<Customer> unsortedCustomers = system.getCustomers();
    List<Customer> sortedCustomers = unsortedCustomers.stream()
        .sorted((c1, c2) -> c2.getFirstname().compareTo(c1.getFirstname()))
        .toList();

    System.out.println("\n****** unsortiert ******");
    for (Customer c: unsortedCustomers) {
      System.out.println(c);
    }

    System.out.println("\n****** sortiert ******");
    for (Customer c: sortedCustomers) {
      System.out.println(c);
    }
  }
}
