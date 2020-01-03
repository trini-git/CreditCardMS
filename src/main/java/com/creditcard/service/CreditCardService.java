package com.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.creditcard.model.BankAccountMain;
import com.creditcard.model.CreditCardModel;
import com.creditcard.repository.CreditCardRepositoryInterface;

import reactor.core.publisher.Mono;

@Service
public class CreditCardService implements CreditCardServiceInterface {
  
  @Autowired
  WebClient client;
  
  @Autowired
  CreditCardRepositoryInterface creditCardRepositoryInterface;
  
  BankAccountMain bankAccountMain = new BankAccountMain();
  
  /* Microservice that connects insertBankSavingAccount */
  public Mono<BankAccountMain> insertCreditCard(BankAccountMain bankAccountMain) {
    return client.post()
      .uri("/insert-credit")
      .accept(MediaType.APPLICATION_JSON_UTF8)
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .body(BodyInserters.fromObject(bankAccountMain))
      .retrieve()
      .bodyToMono(BankAccountMain.class)
      .switchIfEmpty(Mono.empty());
  }
  
  /* Microservice that connects insertBankSavingAccount */
  public Mono<BankAccountMain> updateCreditAmount(BankAccountMain bankAccountMain) {
    return client.put()
    .uri("/update-credit-amount")
    .accept(MediaType.APPLICATION_JSON_UTF8)
    .contentType(MediaType.APPLICATION_JSON_UTF8)
    .syncBody(bankAccountMain)
    .retrieve()
    .bodyToMono(BankAccountMain.class)
    .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<CreditCardModel> insertCreditCard(CreditCardModel creditCardModel) {
  
    bankAccountMain.setCreditCardNumber(creditCardModel.getCreditCardNumber());
    bankAccountMain.setCreditCardAccount(creditCardModel.getCreditCardAccount());
    bankAccountMain.setCreditLimit(creditCardModel.getCreditLimit());
    bankAccountMain.setAvalibleAmount(creditCardModel.getAvalibleAmount());
    bankAccountMain.setCreatedAt(creditCardModel.getCreatedAt());
    bankAccountMain.setType(creditCardModel.getType());
    bankAccountMain.setBank(creditCardModel.getBank());
    return insertCreditCard(bankAccountMain).flatMap(x -> {
      return creditCardRepositoryInterface.save(creditCardModel);
    });
    
  }

  @Override
  public Mono<CreditCardModel> updateConsumeAvalibleAmount(CreditCardModel newCreditCardModel) {
    
    return findByCreditCardNumber(newCreditCardModel.getCreditCardNumber())
    .flatMap(oldCreditCardModel -> {
      bankAccountMain.setCreditCardNumber(newCreditCardModel.getCreditCardNumber());
    
      double oldAvalibleAmount = oldCreditCardModel.getAvalibleAmount();
      double newAvalibleAmount = newCreditCardModel.getAvalibleAmount();
    
      if (oldAvalibleAmount >= newAvalibleAmount) {
        oldCreditCardModel.setAvalibleAmount(oldAvalibleAmount - newAvalibleAmount);
        bankAccountMain.setAvalibleAmount(oldCreditCardModel.getAvalibleAmount());
        return updateCreditAmount(bankAccountMain).flatMap(x -> {
          return creditCardRepositoryInterface.save(oldCreditCardModel);
        });       
      }
      return Mono.empty();    
    });
  }
  
  @Override
  public Mono<CreditCardModel> updatePayAvalibleAmount(CreditCardModel newCreditCardModel) {
  
    return findByCreditCardNumber(newCreditCardModel.getCreditCardNumber())
    .flatMap(oldCreditCardModel -> {
      bankAccountMain.setCreditCardNumber(newCreditCardModel.getCreditCardNumber());
      double oldCreditLimit = oldCreditCardModel.getCreditLimit();
      double oldAvalibleAmount = oldCreditCardModel.getAvalibleAmount();
      double newAvalibleAmount = newCreditCardModel.getAvalibleAmount();
    
      if (oldAvalibleAmount + newAvalibleAmount <= oldCreditLimit) {
        oldCreditCardModel.setAvalibleAmount(oldAvalibleAmount + newAvalibleAmount);
        bankAccountMain.setAvalibleAmount(oldCreditCardModel.getAvalibleAmount());
        return updateCreditAmount(bankAccountMain).flatMap(x -> {
          return creditCardRepositoryInterface.save(oldCreditCardModel);
        });
      }
      return Mono.empty();
    });
  }

  @Override
  public Mono<CreditCardModel> findByCreditCardNumber(String creditCardNumber) {

    return creditCardRepositoryInterface.findByCreditCardNumber(creditCardNumber);
  }

@Override
public Mono<CreditCardModel> updateAmountCc(CreditCardModel creditCardModel) {
	
	return creditCardRepositoryInterface.findByCreditCardNumber(creditCardModel.getCreditCardNumber())
		.flatMap(x -> {
			x.setAvalibleAmount(creditCardModel.getAvalibleAmount());
			return creditCardRepositoryInterface.save(x);
		});
}

@Override
public Mono<CreditCardModel> findByDocument(String document) {
	
	return creditCardRepositoryInterface.findByDocument(document);
}

}
