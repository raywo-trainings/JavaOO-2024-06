package de.datev.bank.account;

import de.datev.bank.Customer;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;

public abstract class Account implements Comparable<Account> {

  private final String iban;
  private BigDecimal balance = BigDecimal.ZERO;
  private Customer owner;


  public Account(String iban, Customer owner) {
    this.iban = iban;
    this.owner = owner;
  }


  public BigDecimal deposit(BigDecimal amount) throws AmountNegativeException {
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new AmountNegativeException("Der Betrag darf nicht negativ sein!");
    }

    balance = balance.add(amount);

    return balance;
  }


  public BigDecimal withdraw(BigDecimal amount) throws AmountNotAvailableException, AmountNegativeException {
    if (amount.compareTo(BigDecimal.ZERO) < 0) {
      throw new AmountNegativeException("Der Betrag darf nicht negativ sein!");
    }

    if (!isAvailable(amount)) {
      throw new AmountNotAvailableException("Der angeforderte Betrag ist nicht verfügbar",
          getAvailableAmount());
    }

    balance = balance.subtract(amount);

    return balance;
  }


  public String getIban() {
    return iban;
  }


  public BigDecimal getBalance() {
    return balance;
  }


  public Customer getOwner() {
    return owner;
  }


  public void setOwner(Customer owner) {
    this.owner = owner;
  }


  public int compareTo(Account otherAccount) {
    // Gleichheit: wenn IBANs beider Konten gleich sind
    // kleiner: wenn IBAN dieses Objekts (this) kleiner ist als die IBAN von otherAccount
    // größer: wenn IBAN dieses Objekts (this) größer ist als die IBAN von otherAccount
    // => wir müssen eigentlich nur die IBANs vergleichen

    return iban.compareTo(otherAccount.iban);
  }


  @Override
  public String toString() {
    return "[" + iban + "]: " + formattedBalance() + ", "
        + additionalInfo() + ", Inhaber: " + owner;
  }


  public abstract BigDecimal getAvailableAmount();


  protected boolean isAvailable(BigDecimal amount) {
    return amount.compareTo(balance) <= 0;
  }


  protected abstract String additionalInfo();


  private String formattedBalance() {
    Format formatter = NumberFormat.getCurrencyInstance();
    return formatter.format(balance);
  }
}
