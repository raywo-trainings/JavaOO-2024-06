package de.datev.bank;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CurrentAccount extends Account {

  private BigDecimal limit;
  private float debitInterestRate;


  public CurrentAccount(String iban, Customer owner) {
    super(iban, owner);

    limit = BigDecimal.ZERO;
    debitInterestRate = 0;
  }


  public BigDecimal getLimit() {
    return limit;
  }


  public void setLimit(BigDecimal limit) {
    this.limit = limit;
  }


  public float getDebitInterestRate() {
    return debitInterestRate;
  }


  public void setDebitInterestRate(float debitInterestRate) {
    this.debitInterestRate = debitInterestRate;
  }


  @Override
  protected boolean isAvailable(BigDecimal amount) {
    BigDecimal maxAmount = getBalance().add(limit);

    return amount.compareTo(maxAmount) <= 0;
  }


  @Override
  public String toString() {
    return "(Giro) " + super.toString();
  }


  @Override
  protected String additionalInfo() {
    return "Dispo: " + formattedLimit() + ", Dispozinssatz: " + formattedDebitInterestRate();
  }


  private String formattedLimit() {
    return NumberFormat.getCurrencyInstance().format(limit);
  }


  private String formattedDebitInterestRate() {
    NumberFormat format = DecimalFormat.getPercentInstance();
    format.setMaximumFractionDigits(2);
    format.setMinimumFractionDigits(2);

    return format.format(debitInterestRate / 100);
  }
}
