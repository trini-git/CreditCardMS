package com.creditcard.service;

import com.creditcard.model.BankAccount;
import com.creditcard.model.CreditCardModel;
import com.creditcard.repository.CreditCardRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CreditCardService implements CreditCardServiceInterface {
  
  @Autowired
  WebClient client;
  
  @Autowired
  CreditCardRepositoryInterface creditCardRepositoryInterface;
  
  BankAccount bankAccount = new BankAccount();
  
  /* Microservice that connects insertBankSavingAccount */
  public Mono<BankAccount> insertBankAccount(BankAccount bankAccount) {
    return client.post()
      .uri("/insert")
      .accept(MediaType.APPLICATION_JSON_UTF8)
      .contentType(MediaType.APPLICATION_JSON_UTF8)
      .body(BodyInserters.fromObject(bankAccount))
      .retrieve()
      .bodyToMono(BankAccount.class)
      .switchIfEmpty(Mono.empty());
  }
  
  /* Microservice that connects insertBankSavingAccount */
  public Mono<BankAccount> updateAmount(BankAccount bankAccount) {
    return client.put()
    .uri("/update-amount")
    .accept(MediaType.APPLICATION_JSON_UTF8)
    .contentType(MediaType.APPLICATION_JSON_UTF8)
    .syncBody(bankAccount)
    .retrieve()
    .bodyToMono(BankAccount.class)
    .switchIfEmpty(Mono.empty());
  }

  @Override
  public Mono<CreditCardModel> insertCreditCard(CreditCardModel creditCardModel) {
  
    bankAccount.setCreditCardNumber(creditCardModel.getCreditCardNumber());
    bankAccount.setAccountNumber(creditCardModel.getAccountNumber());
    bankAccount.setCreditLimit(creditCardModel.getCreditLimit());
    bankAccount.setAvalibleAmount(creditCardModel.getAvalibleAmount());
    bankAccount.setCreatedAt(creditCardModel.getCreatedAt());
        
    return insertBankAccount(bankAccount).flatMap(x -> {
      return creditCardRepositoryInterface.save(creditCardModel);
    });
    
  }

  @Override
  public Mono<CreditCardModel> updateConsumeAvalibleAmount(CreditCardModel newCreditCardModel) {
    
    return findByCreditCardNumber(newCreditCardModel.getCreditCardNumber())
    .flatMap(oldCreditCardModel -> {
      bankAccount.setCreditCardNumber(newCreditCardModel.getCreditCardNumber());
    
      double oldAvalibleAmount = oldCreditCardModel.getAvalibleAmount();
      double newAvalibleAmount = newCreditCardModel.getAvalibleAmount();
    
      if (oldAvalibleAmount >= newAvalibleAmount) {
        oldCreditCardModel.setAvalibleAmount(oldAvalibleAmount - newAvalibleAmount);
        bankAccount.setAvalibleAmount(oldCreditCardModel.getAvalibleAmount());
        return updateAmount(bankAccount).flatMap(x -> {
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
      bankAccount.setCreditCardNumber(newCreditCardModel.getCreditCardNumber());
      double oldCreditLimit = oldCreditCardModel.getCreditLimit();
      double oldAvalibleAmount = oldCreditCardModel.getAvalibleAmount();
      double newAvalibleAmount = newCreditCardModel.getAvalibleAmount();
    
      if (oldAvalibleAmount + newAvalibleAmount <= oldCreditLimit) {
        oldCreditCardModel.setAvalibleAmount(oldAvalibleAmount + newAvalibleAmount);
        bankAccount.setAvalibleAmount(oldCreditCardModel.getAvalibleAmount());
        return updateAmount(bankAccount).flatMap(x -> {
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

}
