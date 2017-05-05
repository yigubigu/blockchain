package com.velo.blockchain.account.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chain.api.Account;
import com.chain.api.MockHsm;
import com.chain.api.UnspentOutput;
import com.chain.exception.BadURLException;
import com.chain.exception.ChainException;
import com.chain.http.Client;
import com.chain.signing.HsmSigner;
import com.velo.blockchain.account.config.AccountProperty;
import com.velo.blockchain.model.AccountBalanceDto;
import com.velo.blockchain.model.AccountDto;
import com.velo.blockchain.model.UnspentOutputDto;

@Component
public class ChainCoreAccountServiceImpl {
	@Autowired
	private AccountProperty accountProperty;

	public List<AccountDto> getAllAccount() throws ChainException {
		Client client = new Client(accountProperty.getChainServerUrl(),
				accountProperty.getClientToken());
		// snippet list-accounts-by-tag
		Account.Items accounts = new Account.QueryBuilder().execute(client);
		List<AccountDto> dtos = new ArrayList<AccountDto>();		
		accounts.forEachRemaining( item -> {
			dtos.add(convertAccountToDto(item));
		});
		
		return dtos;
	}
	
	private AccountDto convertAccountToDto(Account source) {
		AccountDto dto = new AccountDto();
		dto.setId(source.id);
		dto.setAlias(source.alias);
		dto.setQuorum(source.quorum);
		return dto;
	}
	
	
	public List<UnspentOutputDto> getUnspentOutputs(String account, String asset) throws ChainException{	
		Client client = new Client(accountProperty.getChainServerUrl(),
				accountProperty.getClientToken());
		UnspentOutput.Items unspentOutputs = new UnspentOutput.QueryBuilder()
	      .setFilter("account_alias=$1 AND asset_alias=$2")
	      .addFilterParameter(account)
	      .addFilterParameter(asset)
	      .execute(client);		
		List<UnspentOutputDto> dtos = new ArrayList<UnspentOutputDto>();
		unspentOutputs.forEachRemaining(item ->{
				  dtos.add(convertUnspentToDto(item));
		});		
		return dtos;	
	}

	public AccountBalanceDto getAccountBalance(String account, String asset)  throws ChainException {
		Client client = new Client(accountProperty.getChainServerUrl(),
				accountProperty.getClientToken());
		UnspentOutput.Items unspentOutputs = new UnspentOutput.QueryBuilder()
	      .setFilter("account_alias=$1 AND asset_alias=$2")
	      .addFilterParameter(account)
	      .addFilterParameter(asset)
	      .execute(client);
		AccountBalanceDto dto = new AccountBalanceDto();
		dto.setAccountAllias(account);
		unspentOutputs.forEachRemaining( item-> {
			dto.setAccountId(item.accountId);
			dto.setBalanceAmount( dto.getBalanceAmount() + item.amount);
		});	
		return dto;
	}
	
	public AccountDto createAccount(String account) throws ChainException {
		Client client = getClient();
		// check alias existing or not
		Account.Items accounts = new Account.QueryBuilder().setFilter("alias=$1")
				.addFilterParameter(account)
				.execute(client);
		if (accounts.list.size() >0 ) {
			throw new ChainException ("account already existing: " + account);			
		}
		
		MockHsm.Key accountKey = MockHsm.Key.create(client);
		HsmSigner.addKey(accountKey, MockHsm.getSignerClient(client));
		
		Account accountObj = new Account.Builder().setAlias(account).addRootXpub(accountKey.xpub).setQuorum(1).create(client);
		return this.convertAccountToDto(accountObj);
	}
	
	private Client getClient() throws BadURLException {
		return new Client(accountProperty.getChainServerUrl(), accountProperty.getClientToken());
	}
	
	private UnspentOutputDto convertUnspentToDto(UnspentOutput source) {
		UnspentOutputDto dto = new UnspentOutputDto();
		dto.setAccountAlias(source.accountAlias);
		dto.setAccountId(source.accountId);
		dto.setAssetAlias(source.assetAlias);
		dto.setAssetId(source.assetId);
		dto.setAmount(source.amount);
		dto.setControlProgram(source.controlProgram);
		dto.setAssetIsLocal(source.assetIsLocal);
		dto.setId(source.id);
		dto.setIsLocal(source.isLocal);
		dto.setPosition(source.position);
		dto.setPurpose(source.purpose);
		dto.setTransactionId(source.transactionId);
		dto.setType(source.type);
		return dto;
	}
}
