package com.creditcard.service;

import com.creditcard.model.CreditCardModel;

import reactor.core.publisher.Mono;

public interface CreditCardServiceInterface {

	Mono<CreditCardModel> insertCreditCard(CreditCardModel creditCardModel);
	Mono<CreditCardModel> updateConsumeAvalibleAmount (CreditCardModel creditCardModel);
	Mono<CreditCardModel> updatePayAvalibleAmount (CreditCardModel creditCardModel);
	Mono<CreditCardModel> findByCreditCardNumber(String creditCardNumber);
	Mono<CreditCardModel> updateAmountCc (CreditCardModel creditCardModel);
	
	Mono<CreditCardModel> findByDocument(String document);
	
}
