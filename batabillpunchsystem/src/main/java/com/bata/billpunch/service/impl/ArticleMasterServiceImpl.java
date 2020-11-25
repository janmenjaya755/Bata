package com.bata.billpunch.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.ArticlesMasterDao;
import com.bata.billpunch.model.ArticlesMasterModel;

@Service
@Transactional
public class ArticleMasterServiceImpl {

	@Autowired
	private ArticlesMasterDao ordao;

	
	public ArticlesMasterModel getArticleDetails(String artcode) {
		
		 return ordao.findWithArticleDetailsByCode(artcode);
	}

}
