package de.datev.bank;

import de.datev.bank.account.Account;

public class BankSystemOld {

  private Customer[] customers;
  private Account[] accounts;

  private int lastCustomerIndex = 0;
  private int lastAccountIndex = 0;


  public BankSystemOld() {
    customers = new Customer[10];
    accounts = new Account[10];
  }


  public Customer[] getCustomers() {
    return customers;
  }


  public Account[] getAccounts() {
    return accounts;
  }


  public void addCustomer(Customer c) {
    if (lastCustomerIndex >= customers.length) {
      int currentSize = customers.length;
      int newSize = currentSize * 2;
      Customer[] newCustomers = new Customer[newSize];

      for (int i = 0; i < customers.length; i++) {
        newCustomers[i] = customers[i];
      }

      customers = newCustomers;
    }

    customers[lastCustomerIndex] = c;
    lastCustomerIndex++; // lastCustomerIndex = lastCustomerIndex + 1;
  }


  public void addAccount(Account a) {
    if (lastAccountIndex >= accounts.length) {
      return;
    }

    accounts[lastAccountIndex] = a;
    lastAccountIndex++;
  }
}
