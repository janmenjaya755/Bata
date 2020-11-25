package com.bata.billpunch.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bata.billpunch.model.BillPunchRdcDetailsModel;

@Repository
public interface BillPunchRdcDetailsDao extends JpaRepository<BillPunchRdcDetailsModel, Long>{

}
