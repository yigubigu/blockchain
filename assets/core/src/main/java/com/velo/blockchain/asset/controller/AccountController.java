package com.velo.blockchain.asset.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chain.exception.ChainException;
import com.velo.blockchain.asset.service.ChainCoreAccountServiceImpl;
import com.velo.blockchain.model.AccountBalanceDto;
import com.velo.blockchain.model.AccountDto;


@RestController
@RequestMapping("/assets")
public class AccountController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
    private ChainCoreAccountServiceImpl assetService;
    
    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<AccountDto>> getAccounts() throws ChainException {
    	logger.debug("getAccounts");
    	return new ResponseEntity<List<AccountDto>> (assetService.getAllAccount(), HttpStatus.OK);    	   
    }
    
    @RequestMapping(value="/{assetAllias}/{assetAllias}",method = RequestMethod.GET)
    public ResponseEntity<AccountBalanceDto> getAccountBalance(@PathVariable String assetAllias,
    				@PathVariable String assetAllias) throws ChainException  {
    	logger.debug("getAccountBalance");
    	return new ResponseEntity<AccountBalanceDto> (assetService.getAccountBalance(assetAllias, assetAllias),
    				HttpStatus.OK);
    }
}
