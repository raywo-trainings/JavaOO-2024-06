package de.datev.bank.account;

import de.datev.bank.Customer;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class SavingsAccount extends Account {

  private float interestRate;


  public SavingsAccount(String iban, Customer owner) {
    super(iban, owner);

    interestRate = 0.0f;
  }


  public float getInterestRate() {
    return interestRate;
  }


  public void setInterestRate(float interestRate) {
    this.interestRate = interestRate;
  }


  @Override
  public BigDecimal getAvailableAmount() {
    return this.getBalance();
  }


  @Override
  public String toString() {
    return "(Spar) " + super.toString();
  }


  @Override
  protected String additionalInfo() {
    return "Habenzins: " + formattedInterestRate();
  }


  private String formattedInterestRate() {
    NumberFormat format = NumberFormat.getPercentInstance();
    format.setMaximumFractionDigits(2);
    format.setMinimumFractionDigits(2);

    return format.format(interestRate / 100);
  }
}
