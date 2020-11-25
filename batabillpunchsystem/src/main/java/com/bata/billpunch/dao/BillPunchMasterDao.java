package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchMasterModel;

@Repository
public interface BillPunchMasterDao extends JpaRepository<BillPunchMasterModel, Long>{
	
	/*
	 * @Query(nativeQuery = true,value =
	 * "SELECT a.* FROM tt_bill_punch_master a where a.INV_NO like ?1 and a.PARTY_CODE like ?2 and a.ORD_NO like ?3 "
	 * ) public List<BillPunchMasterModel> findWithBillNoPartyCodeAndOrderNo(String
	 * billNo,String partycode,String orderno);
	 */
	public void save(List<BillPunchMasterModel> mn);
	
	@Modifying
	@Query(nativeQuery = true ,value="TRUNCATE TABLE tt_bill_punch_master")
	 void findWithDeleteAll();
}
