package com.creditcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.creditcard.model.CreditCardModel;
import com.creditcard.service.CreditCardService;

import reactor.core.publisher.Mono;

@RestController
//@RequestMapping("/credit-card")
public class CreditCardController {
	
	@Autowired
	CreditCardService creditCardService;
	
	@GetMapping("/find-by/{creditCardNumber}")
	public Mono<CreditCardModel> findByCreditCardNumber (@PathVariable String creditCardNumber){
		
		return creditCardService.findByCreditCardNumber(creditCardNumber);
		
	}
	
	@GetMapping("/find-by-document/{document}")
	public Mono<CreditCardModel> findByDocument (@PathVariable String document){
		
		return creditCardService.findByDocument(document);
		
	}
	
	@PostMapping("/insert")
	public Mono<CreditCardModel> insertCreditCardModel (@RequestBody CreditCardModel creditCardModel){
		
		return creditCardService.insertCreditCard(creditCardModel);
		
	}
	
	@PutMapping("/update-consume")
	public Mono<CreditCardModel> updateConsumeAvalibleAmount (@RequestBody CreditCardModel creditCardModel){
		
		return creditCardService.updateConsumeAvalibleAmount(creditCardModel);
		
	}
	
	@PutMapping("/update-pay")
	public Mono<CreditCardModel> updatePayAvalibleAmount (@RequestBody CreditCardModel creditCardModel){
		
		return creditCardService.updatePayAvalibleAmount(creditCardModel);
		
	}
	
	@PutMapping("/update")
	public Mono<CreditCardModel> updateAmountCc (@RequestBody CreditCardModel creditCardModel){
		
		return creditCardService.updateAmountCc(creditCardModel);
		
	}
}
