package com.BankApplication.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BankApplication.Model.AccountDTO;
import com.BankApplication.Service.AccountService;

@RestController
public class AccountController {
	@Autowired 
	AccountService service;
	
	//Create Account
	@PostMapping("/api/createaccount")
	public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
		return new ResponseEntity<>(service.createAccount(accountDTO),HttpStatus.CREATED);
	}
	
	//Get Single account
	@GetMapping("/api/getAccountInfo/{id}")
	public AccountDTO getAccountInfo(@PathVariable("id") Long id) {
		
		return service.getAccountInfo(id);
	}
//	@PutMapping("/api/depositeAmount/{id}")
//	public AccountDTO depositeAmount(@PathVariable("id") Long id,@RequestBody Map<String, Double> request) {
//		return service.depositeAmount(id, request.get("balance"));
//	}
	//Above is also way to send the data
	
	//Deposit amount
	@PutMapping("/api/depositeAmount/{id}/{amount}")
	public AccountDTO depositeAmount(@PathVariable("id") Long id,@PathVariable("amount") double amount) {
		return service.depositeAmount(id, amount);
	}
	
	// Withdraw amount
	@PutMapping("/api/withdrawAmount/{id}")
	public AccountDTO withdrawAmount(@PathVariable("id") Long id,@RequestBody Map<String,Double> request) {
		return service.withdrawAmount(id, request.get("balance"));
	}
	
	//Get all account information
	@GetMapping("/api/getAllAccounts")
	public List<AccountDTO> getAllAccounts(){
		return service.getAllAccounts();
	}
	
	
}
