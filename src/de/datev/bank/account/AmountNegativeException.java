package de.datev.bank.account;

public class AmountNegativeException extends Exception {
  public AmountNegativeException(String message) {
    super(message);
  }
}
