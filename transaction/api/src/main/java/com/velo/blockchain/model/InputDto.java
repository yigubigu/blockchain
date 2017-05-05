package com.velo.blockchain.model;

import java.util.Map;

import lombok.Data;

@Data
public class InputDto {

	/**
	 * The type of the input.<br>
	 * Possible values are "issue" and "spend".
	 */
	private String type;

	/**
	 * The id of the asset being issued or spent.
	 */

	private String assetId;

	/**
	 * The alias of the asset being issued or spent (possibly null).
	 */

	private String assetAlias;

	/**
	 * The definition of the asset being issued or spent (possibly null).
	 */

	private Map<String, Object> assetDefinition;

	/**
	 * The tags of the asset being issued or spent (possibly null).
	 */

	private Map<String, Object> assetTags;

	/**
	 * A flag indicating whether the asset being issued or spent is local.
	 * Possible values are "yes" or "no".
	 */

	private String assetIsLocal;

	/**
	 * The number of units of the asset being issued or spent.
	 */
	private long amount;

	/**
	 * The id of the output consumed by this input. Null if the input is an
	 * issuance.
	 */

	private String spentOutputId;

	/**
	 * The id of the account transferring the asset (possibly null if the input
	 * is an issuance or an unspent output is specified).
	 */

	private String accountId;

	/**
	 * The alias of the account transferring the asset (possibly null if the
	 * input is an issuance or an unspent output is specified).
	 */

	private String accountAlias;

	/**
	 * The tags associated with the account (possibly null).
	 */

	private Map<String, Object> accountTags;

	/**
	 * A program specifying a predicate for issuing an asset (possibly null if
	 * input is not an issuance).
	 */

	private String issuanceProgram;

	/**
	 * User specified, unstructured data embedded within an input (possibly
	 * null).
	 */

	private Map<String, Object> referenceData;

	/**
	 * A flag indicating if the input is local. Possible values are "yes" or
	 * "no".
	 */

	private String isLocal;

}
