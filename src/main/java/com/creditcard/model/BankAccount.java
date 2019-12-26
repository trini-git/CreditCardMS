package com.creditcard.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BankAccount {

  private String id;
  private String creditCardNumber;
  private String accountNumber;
  private String type;
  private Double creditLimit;
  private Double avalibleAmount;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
  private Date createdAt;
  
  public BankAccount() {
    
    this.createdAt = new Date();
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCreditCardNumber() {
    return creditCardNumber;
  }

  public void setCreditCardNumber(String creditCardNumber) {
    this.creditCardNumber = creditCardNumber;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Double getCreditLimit() {
    return creditLimit;
  }

  public void setCreditLimit(Double creditLimit) {
    this.creditLimit = creditLimit;
  }
  
  public Double getAvalibleAmount() {
    return avalibleAmount;
  }

  public void setAvalibleAmount(Double avalibleAmount) {
    this.avalibleAmount = avalibleAmount;
  }

  public Date getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(Date createdAt) {
	this.createdAt = createdAt;
}

public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
}
