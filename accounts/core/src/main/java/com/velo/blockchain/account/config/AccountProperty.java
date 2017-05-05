package com.velo.blockchain.account.config;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "account")
@Data
public class AccountProperty {
	private String clientToken;			
	private String chainServerUrl;
}
