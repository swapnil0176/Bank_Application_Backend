package com.BankApplication.Utilities;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.BankApplication.Entity.Account;
import com.BankApplication.Model.AccountDTO;

@Component
public class Converter {
	
	//Convert Account Entity to DTO
	public AccountDTO convertToAccountDTO(Account account) {
		AccountDTO accountDTO=null;
		if(account!=null) {
			accountDTO=new AccountDTO();
			BeanUtils.copyProperties(account, accountDTO);
		}
		return accountDTO;
	}
	
	//Convert AccountDTO to Entity
	public Account convertToAccount(AccountDTO accountDTO) {
		Account account=null;
		if(accountDTO!=null) {
			account= new Account();
			BeanUtils.copyProperties(accountDTO,account);
		}
		return account;
	}
	
}
