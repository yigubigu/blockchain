package com.velo.blockchain.asset.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chain.api.Account;
import com.chain.api.Asset;
import com.chain.api.Asset.Items;
import com.chain.exception.ChainException;
import com.chain.http.Client;
import com.velo.blockchain.asset.config.BlockchainProperty;
import com.velo.blockchain.model.AssetDto;

@Component
public class ChainCoreAssetServiceImpl {
	@Autowired
	private BlockchainProperty assetProperty;

	public List<AssetDto> getAllAsset() throws ChainException {
		Client client = new Client(assetProperty.getChainServerUrl(),
				assetProperty.getClientToken());
		// snippet list-accounts-by-tag
		Items accounts = new Asset.QueryBuilder().execute(client);
		List<AssetDto> dtos = new ArrayList<AssetDto>();
		accounts.forEachRemaining(item -> {
			dtos.add(convertAssetToDto(item));
		});

		return dtos;
	}

	private AssetDto convertAssetToDto(Asset source) {
		AssetDto dto = new AssetDto();
		dto.setAlias(source.alias);
		dto.setId(source.id);
		dto.setIsLocal(source.isLocal);
		dto.setIssuanceProgram(source.issuanceProgram);
		dto.setQuorum(source.quorum);
		return dto;
	}

}
