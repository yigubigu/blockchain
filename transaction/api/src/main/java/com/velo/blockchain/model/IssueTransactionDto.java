package com.velo.blockchain.model;

import lombok.Data;

@Data
public class IssueTransactionDto {

	private String account;
	
	private String asset;
	
	private long amount;
}
