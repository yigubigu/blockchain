package com.velo.blockchain.model;

import lombok.Data;

@Data
public class AccountDto {
  
	  /**
	   * Unique account identifier.
	   */
	  public String id;

	  /**
	   * User specified, unique identifier.
	   */
	  public String alias;


	  /**
	   * The number of keys required to sign transactions for the account.
	   */
	  public int quorum;

}
