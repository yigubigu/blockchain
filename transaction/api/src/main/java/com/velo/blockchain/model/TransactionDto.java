package com.velo.blockchain.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class TransactionDto {
	/**
	 * Unique identifier, or transaction hash, of a transaction.
	 */
	private String id;

	/**
	 * Time of transaction.
	 */
	private Date timestamp;

	/**
	 * Unique identifier, or block hash, of the block containing a transaction.
	 */
	private String blockId;

	/**
	 * Height of the block containing a transaction.
	 */
	private int blockHeight;

	/**
	 * Position of a transaction within the block.
	 */
	private int position;

	/**
	 * User specified, unstructured data embedded within a transaction.
	 */
	private Map<String, Object> referenceData;

	/**
	 * A flag indicating one or more inputs or outputs are local. Possible
	 * values are "yes" or "no".
	 */

	private String isLocal;

	/**
	 * List of specified inputs for a transaction.
	 */
	private List<InputDto> inputs;

	/**
	 * List of specified outputs for a transaction.
	 */
	private List<OutputDto> outputs;

}
