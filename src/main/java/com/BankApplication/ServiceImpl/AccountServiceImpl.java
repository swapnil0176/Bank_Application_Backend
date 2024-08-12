package com.BankApplication.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BankApplication.Entity.Account;
import com.BankApplication.Model.AccountDTO;
import com.BankApplication.Repository.AccountRepository;
import com.BankApplication.Service.AccountService;
import com.BankApplication.Utilities.Converter;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	Converter convert;

	@Override
	public AccountDTO createAccount(AccountDTO accountDTO) {
		final Account acc = convert.convertToAccount(accountDTO);
		Account save = accountRepository.save(acc);
		return convert.convertToAccountDTO(save);
	}

	@Override
	public AccountDTO getAccountInfo(Long id) {
		Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		return convert.convertToAccountDTO(acc);
	}

	@Override
	public AccountDTO depositeAmount(Long id, double amount) {
		Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		if (amount < 0) {
			throw new RuntimeException("Amount cannot be negative");
		}
		acc.setBalance(acc.getBalance() + amount);
		Account save = accountRepository.save(acc);
		return convert.convertToAccountDTO(save);
	}

	@Override
	public AccountDTO withdrawAmount(Long id, double balance) {
		Account acc = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account does not exist"));
		if (balance < 0) {
			throw new RuntimeException("Withdraw amount cannot be less than 0");
		} else if (acc.getBalance() < balance) {
			throw new RuntimeException("Insufficent balance...");
		}

		acc.setBalance(acc.getBalance() - balance);
		Account save = accountRepository.save(acc);

		return convert.convertToAccountDTO(save);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		List<Account> allAccounts = accountRepository.findAll();
		List<AccountDTO> all=new ArrayList<>();
		for(Account al : allAccounts) {
			all.add(convert.convertToAccountDTO(al));
		}
		return all;
	}

}
