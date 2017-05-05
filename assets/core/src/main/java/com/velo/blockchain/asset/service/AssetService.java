package com.velo.blockchain.asset.service;

import java.util.List;

import com.chain.api.Account.Items;
import com.velo.blockchain.model.UnspentOutputDto;

public interface AccountService {
	public Items getAllAccount();
	public List<UnspentOutputDto> getUnspentOutputs(String account, String asset);
	
}
