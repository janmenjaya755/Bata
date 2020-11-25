package com.bata.billpunch.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bata.billpunch.dao.OrdersMasterDao;
import com.bata.billpunch.model.OrdersMasterModel;

@Service
@Transactional
public class OrederMasterServiceImpl {

	@Autowired
	private OrdersMasterDao ordao;

	public List<OrdersMasterModel> findWithBillOrderDetailsByOrderNo(String orderNo) {

		return ordao.findWithBillOrderDetailsByOrderNo(orderNo);
	}

}
