package de.datev.bank;

import de.datev.bank.account.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class BankSystem {

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


  public List<Account> getSortedAccounts(SortOrder order) {
    List<Account> sortedAccounts = new ArrayList<>(accounts);
    Comparator<Account> comparator = null;

    switch (order) {
      case ASCENDING:
        comparator = new AscendingAccountComparator();
        break;

      case DESCENDING:
        comparator = new DescendingAccountComparator();
        break;

      default:
        comparator = new AscendingAccountComparator();
        break;
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
