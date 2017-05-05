package com.velo.blockchain.account.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chain.exception.ChainException;
import com.velo.blockchain.account.service.ChainCoreAccountServiceImpl;
import com.velo.blockchain.model.AccountBalanceDto;
import com.velo.blockchain.model.AccountDto;


@RestController
@RequestMapping("/accounts")
public class AccountController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
    private ChainCoreAccountServiceImpl accountService;
    
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<AccountDto>> getAccounts() throws ChainException {
    	logger.debug("getAccounts");
    	return new ResponseEntity<List<AccountDto>> (accountService.getAllAccount(), HttpStatus.OK);    	   
    }
    
    @RequestMapping(value="/{accountAllias}/{assetAllias}",method = RequestMethod.GET)
    public ResponseEntity<AccountBalanceDto> getAccountBalance(@PathVariable String accountAllias,
    				@PathVariable String assetAllias) throws ChainException  {
    	logger.debug("getAccountBalance");
    	return new ResponseEntity<AccountBalanceDto> (accountService.getAccountBalance(accountAllias, assetAllias),
    				HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto account) throws ChainException {
    	logger.debug("createAccount");    	
    	return new ResponseEntity<AccountDto> (accountService.createAccount(account.alias), HttpStatus.OK);
    }
    
}
