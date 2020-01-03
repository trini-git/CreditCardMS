package com.creditcard.model;

public class BankAccountMain {

  private String id;
  private String creditCardNumber;
  private String creditCardAccount;
  private String type;
  private Double creditLimit;
  private Double avalibleAmount;
  private String createdAt;
  private Bank bank;
  
//  public BankAccountMain() {
//    
//	  SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss"); 
//	    this.createdAt = formatter.format(new Date());
//  }

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

  public String getCreditCardAccount() {
	return creditCardAccount;
}

public void setCreditCardAccount(String creditCardAccount) {
	this.creditCardAccount = creditCardAccount;
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

  public String getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(String string) {
	this.createdAt = string;
}

public String getType() {
    return type;
  }
  
  public void setType(String type) {
    this.type = type;
  }

public Bank getBank() {
	return bank;
}

public void setBank(Bank bank) {
	this.bank = bank;
}
    
}
