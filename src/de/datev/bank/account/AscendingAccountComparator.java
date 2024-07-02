package de.datev.bank.account;

import java.util.Comparator;

public class AscendingAccountComparator implements Comparator<Account> {
  @Override
  public int compare(Account a1, Account a2) {
    return a1.compareTo(a2);
  }
}
