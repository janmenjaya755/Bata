package com.bata.billpunch.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.ApprovalDetailsModel;

@Repository
public interface ApprovalDetailsDao extends JpaRepository<ApprovalDetailsModel, Long>{
	
	
	@Query(nativeQuery = true,value = "SELECT a.* FROM TL_APPROVAL_DETAILS a where a.BILL_UNIQUE_CODE like ?1 and a.FIN_USER_STATUS like ?2")
	public List<ApprovalDetailsModel> findwithApprovalDetailsForValidate(String uniqueId,String status);

}
