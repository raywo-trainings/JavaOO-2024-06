package de.datev.bank;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.UUID;

public class Customer {

  private final UUID id;
  private String firstname;
  private String lastname;
  private ZonedDateTime dateOfBirth;


  public Customer(UUID id, String firstname, String lastname, ZonedDateTime dateOfBirth) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.dateOfBirth = dateOfBirth;
  }


  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }


  public void setLastname(String lastname) {
    this.lastname = lastname;
  }


  public void setDateOfBirth(ZonedDateTime dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }


  public UUID getId() {
    return id;
  }


  public String getFirstname() {
    return firstname;
  }


  public String getLastname() {
    return lastname;
  }


  public ZonedDateTime getDateOfBirth() {
    return dateOfBirth;
  }


  @Override
  public String toString() {
    return "(" + truncatedId() + ") " + lastname + ", "
        + firstname + ", geb. am: " + formattedDateOfBirth();
  }


  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer customer)) return false;

    return getId().equals(customer.getId());
  }


  @Override
  public int hashCode() {
    return getId().hashCode();
  }


  private String formattedDateOfBirth() {
    DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);

    return dateOfBirth.toLocalDate().format(formatter);
  }


  private String truncatedId() {
    if (id.toString().length() < 8) {
      return id.toString();
    }

    return id.toString().substring(0, 8) + "...";
  }
}
