package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.OrdersMasterModel;
import com.bata.billpunch.model.dto.BillPurchaseCostInterface;
import com.bata.billpunch.model.dto.OrderPair;
import com.bata.billpunch.model.dto.PriceInterface;

@Repository
public interface OrdersMasterDao extends JpaRepository<OrdersMasterModel, Long>{

	
	 public void save(List<OrdersMasterModel> mn);
		
		@Modifying
		@Query(nativeQuery = true ,value="TRUNCATE TABLE tm_orders_master_dtls")
		 void findWithDeleteAll();
		
		@Query(nativeQuery = true,value = "SELECT a.* FROM tm_orders_master_dtls a where a.ARTNO like ?1 and a.PARTY_CODE like ?2 and a.ORDERNO like ?3 ")
		public List<OrdersMasterModel> findWithBillOrderDetails(String billNo,String partycode,String orderno);
		
		@Query(nativeQuery = true,value = "SELECT a.* FROM tm_orders_master_dtls a where  a.ORDERNO like ?1 ")
		public List<OrdersMasterModel> findWithBillOrderDetailsByOrderNo(String orderno);
		
		@Query(nativeQuery = true,value = "SELECT sum(a.PURPRICE) as purchaseCost FROM tm_orders_master_dtls a where a.RDCNO like ?1 and a.ORDERNO like ?2 and a.PARTY_CODE like ?3 and a.ARTNO like ?4 group by a.RDCNO,a.ORDERNO,a.PARTY_CODE,a.ARTNO")
		public BillPurchaseCostInterface findWithPurchaseCost(String rdcno,String orderno,String partycode,String artNo);
		
		@Query(nativeQuery = true,value = "SELECT a.ORDER_PR as orderPr FROM tm_orders_master_dtls a where a.ARTNO like ?1 and  a.ORDERNO like ?2 ")
		public OrderPair findWithPairByOrderNoAndArtno(String artno,String orderno);
		
		@Query(nativeQuery = true,value = "SELECT a.PURPRICE as purprice FROM tm_orders_master_dtls a where a.ARTNO like ?1")
		public PriceInterface findWithPriceByArtno(String artno);

		
		@Query(nativeQuery = true,value = "SELECT a.* FROM tm_orders_master_dtls a where a.ARTNO like ?1 and  a.ORDERNO like ?2 ")
		public List<OrdersMasterModel> findWithByOrderNoAndArtno(String artno,String orderno);
}
