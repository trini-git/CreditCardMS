package com.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditcard.model.CreditCardModel;
import com.creditcard.model.PayAndConsumeModel;
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
	public Mono<CreditCardModel> updateConsumeAvalibleAmount(CreditCardModel newCreditCardModel) {
				
		return findByCreditCardNumber(newCreditCardModel.getCreditCardNumber())
			.flatMap(oldCreditCardModel -> {
				
				double oldAvalibleAmount = oldCreditCardModel.getAvalibleAmount();
				double newAvalibleAmount = newCreditCardModel.getAvalibleAmount();
				
				if (oldAvalibleAmount >= newAvalibleAmount) {
					oldCreditCardModel.setAvalibleAmount(oldAvalibleAmount - newAvalibleAmount);
					return creditCardRepositoryInterface.save(oldCreditCardModel);
				}
				return Mono.empty();				
		});

	}
	
	@Override
	public Mono<CreditCardModel> updatePayAvalibleAmount(CreditCardModel newCreditCardModel) {
		
		return findByCreditCardNumber(newCreditCardModel.getCreditCardNumber())
			.flatMap(oldCreditCardModel -> {
				
				double oldCreditLimit = oldCreditCardModel.getCreditLimit();
				double oldAvalibleAmount = oldCreditCardModel.getAvalibleAmount();
				double newAvalibleAmount = newCreditCardModel.getAvalibleAmount();
				
				if (oldAvalibleAmount + newAvalibleAmount <= oldCreditLimit) {
					oldCreditCardModel.setAvalibleAmount(oldAvalibleAmount + newAvalibleAmount);
					return creditCardRepositoryInterface.save(oldCreditCardModel);
				}
				return Mono.empty();
			});
	}

	@Override
	public Mono<CreditCardModel> findByCreditCardNumber(String creditCardNumber) {

		return creditCardRepositoryInterface.findByCreditCardNumber(creditCardNumber);
	}

}
