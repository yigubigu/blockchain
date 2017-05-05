package com.velo.blockchain.account.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.chain.exception.ChainException;
import com.velo.blockchain.AccountApplication;
import com.velo.blockchain.model.AccountBalanceDto;
import com.velo.blockchain.model.AccountDto;
import com.velo.blockchain.model.UnspentOutputDto;
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes={SpringMongoConfiguration.class})
@SpringApplicationConfiguration(AccountApplication.class)
@WebAppConfiguration
public class ChainCoreAccountServiceImplTest {

	@Autowired
	private ChainCoreAccountServiceImpl accountService;
	
	@Before
	public void init(){	
	}
	
	@Test
	public void verifyFindAllAccounts() throws ChainException {		
		List<AccountDto> dtos = accountService.getAllAccount();
		assertNotNull (dtos);
	}
	
	@Test
	public void verifyGetUnspentOutputs() throws ChainException {
		String account = "James";
		String asset = "sugar";
		List<UnspentOutputDto> dtos = accountService.getUnspentOutputs(account, asset);		
		assertNotNull (dtos);
		
		String bobAccount = "bob";		
		List<UnspentOutputDto> bobDtos = accountService.getUnspentOutputs(bobAccount, asset);
		assertNotNull (bobDtos);
	}
	
	@Test 
	public void verifyAccountBalance() throws ChainException {
		String account = "James";
		String asset = "sugar";
		AccountBalanceDto balance =  accountService.getAccountBalance(account, asset);
		assertNotNull (balance);
	}
	
}
