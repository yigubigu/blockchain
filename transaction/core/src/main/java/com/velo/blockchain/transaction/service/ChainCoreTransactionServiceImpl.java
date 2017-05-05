package com.velo.blockchain.transaction.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chain.api.Account;
import com.chain.api.Asset;
import com.chain.api.MockHsm;
import com.chain.api.Transaction;
import com.chain.api.Transaction.Input;
import com.chain.api.Transaction.Output;
import com.chain.exception.BadURLException;
import com.chain.exception.ChainException;
import com.chain.http.Client;
import com.chain.signing.HsmSigner;
import com.velo.blockchain.model.InputDto;
import com.velo.blockchain.model.OutputDto;
import com.velo.blockchain.model.TransactionDto;
import com.velo.blockchain.transaction.config.BlockchainProperty;

@Component
public class ChainCoreTransactionServiceImpl {
	@Autowired
	private BlockchainProperty assetProperty;

	public List<TransactionDto> getAllTransactions() throws ChainException {
		
		// snippet list-accounts-by-tag
		com.chain.api.Transaction.Items accounts = new Transaction.QueryBuilder().execute(getClient());
		List<TransactionDto> dtos = accounts.list.stream().map(this::convertTransactionToDto).collect(Collectors.toList());
		return dtos;
	}
	
	
	public TransactionDto issue(String account, String asset, long amount) throws ChainException {

		Client client = getClient();

		
		
		Asset.Items assets = new Asset.QueryBuilder().setFilter("alias=$1").
			addFilterParameter(asset).execute(client);
		//todo add exception handling here
		Asset assetObj = assets.list.get(0);
		HsmSigner.addKey(assetObj.keys[0].rootXpub, MockHsm.getSignerClient(client));
		
		Account.Items accounts = new Account.QueryBuilder().setFilter("alias=$1")
				.addFilterParameter(account)
				.execute(client);
		Account accountObj = accounts.list.get(0);
		HsmSigner.addKey(accountObj.keys[0].rootXpub, MockHsm.getSignerClient(client));
		
		Transaction.submit(client, HsmSigner.sign(
			new Transaction.Builder()
		.addAction(new Transaction.Action.Issue().setAssetAlias(asset).setAmount(amount))
		.addAction(new Transaction.Action.ControlWithAccount().setAccountAlias(account).setAssetAlias(asset).setAmount(amount))
		.build(client)
		));
		
		return null;
		
	}

	private TransactionDto convertTransactionToDto(Transaction source) {
		TransactionDto dto = new TransactionDto();
		dto.setId(source.id);
		dto.setBlockHeight(source.blockHeight);
		dto.setBlockId(source.blockId);
		dto.setInputs(source.inputs.stream().map(this::convertToInputDto).collect(Collectors.toList()));
		dto.setOutputs(source.outputs.stream().map(this::convertToOutputDto).collect(Collectors.toList()));
		
		return dto;
	}
	
	private InputDto convertToInputDto(Input source) {
		InputDto dto = new InputDto();
		dto.setAccountAlias(source.accountAlias);
		dto.setAccountId(source.accountId);
		dto.setAmount(source.amount);
		dto.setAssetAlias(source.assetAlias);
		dto.setAssetId(source.assetId);
		dto.setAssetIsLocal(source.assetIsLocal);
		dto.setIsLocal(source.isLocal);
		dto.setIssuanceProgram(source.issuanceProgram);
		dto.setSpentOutputId(source.spentOutputId);
		dto.setType(source.type);
		return dto;
	}
	
	private OutputDto convertToOutputDto(Output source) {
		OutputDto dto = new OutputDto();
		dto.setAccountAlias(source.accountAlias);
		dto.setAccountId(source.accountId);
		dto.setAmount(source.amount);
		dto.setAssetId(source.assetId);
		dto.setAssetAlias(source.assetAlias);
		dto.setAssetIsLocal(source.assetIsLocal);
		dto.setControlProgram(source.controlProgram);
		dto.setId(source.id);
		dto.setIsLocal(source.isLocal);
		dto.setPosition(source.position);
		dto.setType(source.type);
		
		return dto;
	}
	
	private Client getClient() throws BadURLException {
		return new Client(assetProperty.getChainServerUrl(), assetProperty.getClientToken());
	}

}
