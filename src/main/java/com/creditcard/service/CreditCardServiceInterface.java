package com.creditcard.service;

import com.creditcard.model.CreditCardModel;

import reactor.core.publisher.Mono;

public interface CreditCardServiceInterface {

	Mono<CreditCardModel> insertCreditCard(CreditCardModel creditCardModel);
	Mono<CreditCardModel> updateAvalibleAmount (CreditCardModel creditCardModel, String creditCardNumber);
	Mono<CreditCardModel> findByCreditCardNumber(String creditCardNumber);
	
}
