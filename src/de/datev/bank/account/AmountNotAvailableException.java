package de.datev.bank.account;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class AmountNotAvailableException extends Exception {

  private final BigDecimal availableAmount;


  public AmountNotAvailableException(String message, BigDecimal availableAmount) {
    super(message);

    this.availableAmount = availableAmount;
  }


  public BigDecimal getAvailableAmount() {
    return availableAmount;
  }


  public String getFormattedAvailableAmount() {
    return NumberFormat.getCurrencyInstance().format(availableAmount);
  }
}
