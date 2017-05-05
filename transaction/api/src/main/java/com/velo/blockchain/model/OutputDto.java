package com.velo.blockchain.model;

import java.util.Map;

import lombok.Data;

@Data
public class OutputDto {

	/**
	 * The id of the output.
	 */

	private String id;

	/**
	 * The type the output.<br>
	 * Possible values are "control" and "retire".
	 */
	private String type;

	/**
	 * The purpose of the output.<br>
	 * Possible purposes are "receive" and "change". Only populated if the
	 * output's control program was generated locally.
	 */
	private String purpose;

	/**
	 * The output's position in a transaction's list of outputs.
	 */
	private int position;

	/**
	 * The id of the asset being controlled.
	 */

	private String assetId;

	/**
	 * The alias of the asset being controlled.
	 */

	private String assetAlias;

	/**
	 * The definition of the asset being controlled (possibly null).
	 */

	private Map<String, Object> assetDefinition;

	/**
	 * The tags of the asset being controlled (possibly null).
	 */

	private Map<String, Object> assetTags;

	/**
	 * A flag indicating whether the asset being controlled is local. Possible
	 * values are "yes" or "no".
	 */

	private String assetIsLocal;

	/**
	 * The number of units of the asset being controlled.
	 */
	private long amount;

	/**
	 * The id of the account controlling this output (possibly null if a control
	 * program is specified).
	 */

	private String accountId;

	/**
	 * The alias of the account controlling this output (possibly null if a
	 * control program is specified).
	 */

	private String accountAlias;

	/**
	 * The tags associated with the account controlling this output (possibly
	 * null if a control program is specified).
	 */

	private Map<String, Object> accountTags;

	/**
	 * The control program which must be satisfied to transfer this output.
	 */
	private String controlProgram;

	/**
	 * User specified, unstructured data embedded within an input (possibly
	 * null).
	 */
	private Map<String, Object> referenceData;

	/**
	 * A flag indicating if the output is local. Possible values are "yes" or
	 * "no".
	 */
	private String isLocal;

}
