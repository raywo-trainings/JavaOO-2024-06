package de.datev.bank;

import java.util.*;

public class BankSystem {

  public static final int ASCENDING_ORDER = 1;
  public static final int DESCENDING_ORDER = -1;


  private final List<Customer> customers;
  private final List<Account> accounts;


  public BankSystem() {
    customers = new LinkedList<>();
    accounts = new ArrayList<>();
  }


  public List<Customer> getCustomers() {
    return customers;
  }

  /*
    Sortierung

    Wir benötigen:
    * einen Sortieralgorithmus -> Collections.sort()
    * eine Regel, nach der sortiert werden soll (IBAN aufsteigend)

    Dann:
    * Algorithmus mit Regel auf vorhandene Accounts anwenden -> Collections.sort(accounts)

    Wo:
    * Regel bei Accounts
    * Anwendung hier
   */

  public List<Account> getAccounts() {
    // Liefert immer eine nach IBAN aufsteigend sortierte Liste
//    Collections.sort(accounts); // O(n*log(n))
//    Collections.reverse(accounts); // O(n)
    // Gesamt: O(n + n*log(n))

    return accounts;
  }


  public List<Account> getSortedAccounts(int direction) {
    List<Account> sortedAccounts = new ArrayList<>(accounts.size());
    sortedAccounts.addAll(accounts);

    Comparator<Account> comparator = null;

    if (direction == ASCENDING_ORDER) {
      comparator = new AscendingAccountComparator();
    } else if (direction == DESCENDING_ORDER) {
      comparator = new DescendingAccountComparator();
    }

    sortedAccounts.sort(comparator);
    return sortedAccounts;
  }


  public void addCustomer(Customer c) {
    customers.add(c);
  }


  public void addAccount(Account a) {
    accounts.add(a);
  }


  public List<CurrentAccount> getCurrentAccounts() {
    List<CurrentAccount> result = new ArrayList<>();

    for (Account a : accounts) {
      if (a instanceof CurrentAccount) {
        result.add((CurrentAccount) a);
      }
    }

    return result;
  }















  public List<SavingsAccount> getSavingsAccounts() {
    return accounts.stream()
        .filter(a -> a instanceof SavingsAccount)
        .map(a -> (SavingsAccount) a)
        .toList();
  }

}
