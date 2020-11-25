package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.ArticlesMasterModel;

@Repository
public interface ArticlesMasterDao extends JpaRepository<ArticlesMasterModel, Long> {

	public void save(List<ArticlesMasterModel> mn);

	@Modifying
	@Query(nativeQuery = true, value = "TRUNCATE TABLE tm_articles_master_dtls")
	void findWithDeleteAll();

	@Query(nativeQuery = true, value = "select a.* from  tm_articles_master_dtls a where a.ART_NAME like ?1 ")
	public List<ArticlesMasterModel> findWithArticleDetails(String artname);
	
	@Query(nativeQuery = true, value = "select a.* from  tm_articles_master_dtls a where a.ART_NO like ?1 ")
	public ArticlesMasterModel findWithArticleDetailsByCode(String artcode);
}
