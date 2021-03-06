package com.velo.blockchain.asset.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "blockchain")
@Data
public class BlockchainProperty {
	private String clientToken;			
	private String chainServerUrl;
}
