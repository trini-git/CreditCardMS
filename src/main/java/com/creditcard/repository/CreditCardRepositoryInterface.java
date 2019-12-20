package com.creditcard.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.creditcard.model.CreditCardModel;

import reactor.core.publisher.Mono;

@Repository
public interface CreditCardRepositoryInterface extends ReactiveMongoRepository<CreditCardModel, String>{
	
	Mono<CreditCardModel> findByCreditCardNumber(String creditCardNumber);
}
