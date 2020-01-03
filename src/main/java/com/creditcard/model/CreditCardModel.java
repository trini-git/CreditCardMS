package com.creditcard.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "credit_card")
public class CreditCardModel {

	@Id
	private String id;
	private String creditCardNumber;
	private String creditCardAccount;
	private String type;
	private Double creditLimit;
	private Double avalibleAmount;
	private List<CcClient> ccClient;
	private String createdAt;
	private Bank bank;
	
	public CreditCardModel() {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss"); 
	    this.createdAt = formatter.format(new Date());
		
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

	public List<CcClient> getCcClient() {
		return ccClient;
	}

	public void setCcClient(List<CcClient> ccClient) {
		this.ccClient = ccClient;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
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
