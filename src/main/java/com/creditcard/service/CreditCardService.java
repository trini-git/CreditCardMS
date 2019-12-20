package com.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcard.model.CreditCardModel;
import com.creditcard.repository.CreditCardRepositoryInterface;

import reactor.core.publisher.Mono;

@Service
public class CreditCardService implements CreditCardServiceInterface {

	@Autowired
	CreditCardRepositoryInterface creditCardRepositoryInterface;

	@Override
	public Mono<CreditCardModel> insertCreditCard(CreditCardModel creditCardModel) {

		return creditCardRepositoryInterface.save(creditCardModel);
	}

	@Override
	public Mono<CreditCardModel> updateAvalibleAmount(CreditCardModel newCreditCardModel, String creditCardNumber) {
				
		return findByCreditCardNumber(creditCardNumber)
			.flatMap(oldCreditCardModel -> {
				double oldAvalibleAmount = oldCreditCardModel.getAvalibleAmount();
				double newAvalibleAmount = newCreditCardModel.getAvalibleAmount();
				
				if (oldAvalibleAmount >= newAvalibleAmount) {
					oldCreditCardModel.setAvalibleAmount(oldAvalibleAmount - newAvalibleAmount);
					return creditCardRepositoryInterface.save(oldCreditCardModel);
				}
				return Mono.error(new Exception("Cash is not enough"));				
		});

	}

	@Override
	public Mono<CreditCardModel> findByCreditCardNumber(String creditCardNumber) {

		return creditCardRepositoryInterface.findByCreditCardNumber(creditCardNumber);
	}

}
