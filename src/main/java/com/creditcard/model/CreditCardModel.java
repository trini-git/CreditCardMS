package com.creditcard.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "credit_card")
public class CreditCardModel {

	@Id
	private String id;
	private String creditCardNumber;
	private String accountNumber;
	private Double creditLimit;
	private Double avalibleAmount;
	private List<CcClient> ccClient;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
	private Date dateCreatedAt;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
	private Date dateLastModified;
	
	
	public CreditCardModel() {
		
		this.dateCreatedAt = new Date();
		this.dateLastModified = new Date();
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

	public List<CcClient> getCcClient() {
		return ccClient;
	}

	public void setCcClient(List<CcClient> ccClient) {
		this.ccClient = ccClient;
	}

	public Date getDateCreatedAt() {
		return dateCreatedAt;
	}

	public void setDateCreatedAt(Date dateCreatedAt) {
		this.dateCreatedAt = dateCreatedAt;
	}

	public Date getDateLastModified() {
		return dateLastModified;
	}

	public void setDateLastModified(Date dateLastModified) {
		this.dateLastModified = dateLastModified;
	}

}
