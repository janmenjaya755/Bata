package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.ShopMasterModel;

@Repository
public interface ShopMasterDao extends JpaRepository<ShopMasterModel, Long>{

	
	 public void save(List<ShopMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_shop_master_dtls")
		 void findWithDeleteAll();
		
		@Query(nativeQuery = true, value = "select a.* from  tm_shop_master_dtls a where TRIM(a.SHOP_NAME) like ?1 ")
		public List<ShopMasterModel> findWithShopDetails(String shopcode);
		
		@Query(nativeQuery = true, value = "select a.* from  tm_shop_master_dtls a where a.SHOP_NO like ?1 ")
		public ShopMasterModel findWithShopName(String shopcode);
		
		
		}
