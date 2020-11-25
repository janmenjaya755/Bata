package com.bata.billpunch.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.ArticlesMasterDao;
import com.bata.billpunch.dao.PartiesMasterDao;
import com.bata.billpunch.model.ArticlesMasterModel;
import com.bata.billpunch.model.PartiesMasterModel;

@Service
@Transactional
public class PartyMasterServiceImpl {

	@Autowired
	private PartiesMasterDao pdao;

	
	public PartiesMasterModel getPartiesDetails(String partycode) {
		
		 return pdao.findWithPartyName(partycode);
	}

}
