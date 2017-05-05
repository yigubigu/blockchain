package com.velo.blockchain.asset.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chain.exception.ChainException;
import com.velo.blockchain.asset.service.ChainCoreAssetServiceImpl;
import com.velo.blockchain.model.AssetDto;


@RestController
@RequestMapping("/assets")
public class AssetController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Autowired
    private ChainCoreAssetServiceImpl assetService;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<List<AssetDto>> getAccounts() throws ChainException {
    	logger.debug("getAccounts");
    	return new ResponseEntity<List<AssetDto>> (assetService.getAllAsset(), HttpStatus.OK);    	
    	
    	
    }
    
    
}
