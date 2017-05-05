package com.velo.blockchain.transaction.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chain.exception.ChainException;
import com.velo.blockchain.model.IssueTransactionDto;
import com.velo.blockchain.model.TransactionDto;
import com.velo.blockchain.transaction.service.ChainCoreTransactionServiceImpl;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
    private ChainCoreTransactionServiceImpl transactionService;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<TransactionDto>> getAccounts() throws ChainException {
    	logger.debug("getAccounts");
    	return new ResponseEntity<List<TransactionDto>> (transactionService.getAllTransactions(), HttpStatus.OK);    	    	    	
    }       
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TransactionDto>issue(@RequestBody IssueTransactionDto issueRequest) throws ChainException {
    	transactionService.issue(issueRequest.getAccount(), issueRequest.getAsset(),issueRequest.getAmount());
    	return null;
    }
}
