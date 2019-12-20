package com.creditcard.service;

import com.creditcard.model.CreditCardModel;
import com.creditcard.model.PayAndConsumeModel;

import reactor.core.publisher.Mono;

public interface CreditCardServiceInterface {

	Mono<CreditCardModel> insertCreditCard(CreditCardModel creditCardModel);
	Mono<CreditCardModel> updateConsumeAvalibleAmount (CreditCardModel creditCardModel);
	Mono<CreditCardModel> updatePayAvalibleAmount (CreditCardModel creditCardModel);
	Mono<CreditCardModel> findByCreditCardNumber(String creditCardNumber);
	
}
