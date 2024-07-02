package de.datev.bank.account;

import java.util.Comparator;

public class DescendingAccountComparator implements Comparator<Account> {
  @Override
  public int compare(Account a1, Account a2) {
    return a2.compareTo(a1);
  }
}
