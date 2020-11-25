package com.bata.billpunch.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.ArticlesMasterDao;
import com.bata.billpunch.dao.ShopMasterDao;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.ShopMasterModel;

@Service
@Transactional
public class ShopMasterServiceImpl {

	@Autowired
	private ShopMasterDao sdao;

	
	public ShopMasterModel getShopDetails(String shopno) {
		
		 return sdao.findWithShopName(shopno);
	}

}
