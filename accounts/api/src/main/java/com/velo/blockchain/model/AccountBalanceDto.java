package com.velo.blockchain.model;

import lombok.Data;

@Data
public class AccountBalanceDto {

	private String accountId;
	
	private String accountAllias;
	
	private long balanceAmount;
}
