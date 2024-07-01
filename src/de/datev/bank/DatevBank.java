package de.datev.bank;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
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

    CurrentAccount currentAccount = new CurrentAccount("DE4711", customer);
    system.addAccount(currentAccount);

    currentAccount.setLimit(BigDecimal.valueOf(500));
    currentAccount.setDebitInterestRate(11.25f);

    currentAccount.deposit(BigDecimal.valueOf(100.14));
    currentAccount.withdraw(BigDecimal.valueOf(200));
    System.out.println(currentAccount);

    SavingsAccount savingsAccount = new SavingsAccount("DE4712", customer);
    system.addAccount(savingsAccount);

    savingsAccount.setInterestRate(0.75f);
    savingsAccount.deposit(BigDecimal.valueOf(200));
    System.out.println(savingsAccount);

    system.addAccount(new CurrentAccount("DE4713", customer));
    system.addAccount(new CurrentAccount("DE4718", customer));
    system.addAccount(new CurrentAccount("DE4714", customer));
    system.addAccount(new CurrentAccount("DE4719", customer));
    system.addAccount(new CurrentAccount("DE4715", customer));

    System.out.println("\n************");
    for (Account a : system.getSortedAccounts(BankSystem.DESCENDING_ORDER)) {
      System.out.println(a);
    }
  }
}
