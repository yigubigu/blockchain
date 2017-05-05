package com.velo.blockchain.transaction.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "blockchain")
@Data
public class BlockchainProperty {
	//todo: move to common 
	private String clientToken;			
	private String chainServerUrl;
}
