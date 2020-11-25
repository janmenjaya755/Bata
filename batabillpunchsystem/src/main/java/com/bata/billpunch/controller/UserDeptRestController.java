package com.bata.billpunch.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bata.billpunch.constant.ReraMessageConstants;
import com.bata.billpunch.constant.ResponseModel;
import com.bata.billpunch.model.UserDeptModel;
import com.bata.billpunch.service.impl.UserDeptServiceimpl;

@RestController
@PropertySource(ignoreResourceNotFound = true, value = "classpath:static/common.properties")
@CrossOrigin(origins = "*")
@RequestMapping("/bill-punch/depertment")
public class UserDeptRestController {
	private static final Logger logger = LogManager.getLogger(UserDeptRestController.class);
	@Autowired
	private UserDeptServiceimpl services;

	@Autowired
	private Environment environment;

	/********************************************************************************************
	 * save user Role
	 ********************************************************************************************/
	@PostMapping("/save")
	public ResponseEntity<ResponseModel> saveUserRole(@RequestBody UserDeptModel entity) {
		UserDeptModel cm = services.save(entity);
		ResponseModel rs = new ResponseModel();
		if (Optional.ofNullable(cm).isPresent()) {
			logger.debug("Save data Successfully");
			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.SUCCESS_MESSAGE));
			rs.setData(cm);
		} else {
			logger.debug("Failed to save");
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.FAILED_MESSAGE));
		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * Find All UserRole
	 ********************************************************************************************/
	@GetMapping("/getall")
	public ResponseEntity<ResponseModel> getAll() {
		List<UserDeptModel> cm = services.getAll();
		ResponseModel rs = new ResponseModel();
		if (!cm.isEmpty()) {
			logger.debug("fetch data Successfully");
			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.SUCCESS_MESSAGE));
			rs.setData(cm);
		} else {
			logger.debug("No data found");
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.FAILED_MESSAGE));

		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * Find UserDept by Passing Id
	 ********************************************************************************************/
	@GetMapping("/{id}")
	public ResponseEntity<ResponseModel> getByuserId(@PathVariable("id") Long id) {
		UserDeptModel cm = services.getById(id);
		ResponseModel rs = new ResponseModel();
		if (Optional.ofNullable(cm).isPresent()) {
			logger.debug("fetch data Successfully");
			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.SUCCESS_MESSAGE));
			rs.setData(cm);
		} else {
			logger.debug("No data found");
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.FAILED_MESSAGE));

		}
		return ResponseEntity.ok(rs);
	}

	/********************************************************************************************
	 * update user Dept
	 ********************************************************************************************/
	@PutMapping("/modify")
	public ResponseEntity<ResponseModel> updateUserRole(@RequestBody UserDeptModel entity) {
		UserDeptModel cm = services.update(entity);
		ResponseModel rs = new ResponseModel();
		if (Optional.ofNullable(cm).isPresent()) {
			logger.debug("update data Successfully");
			rs.setStatus(ReraMessageConstants.SUCCESS_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.SUCCESS_MESSAGE));
			rs.setData(cm);
		} else {
			logger.debug("Failed to update");
			rs.setStatus(ReraMessageConstants.FAILED_STATUS);
			rs.setMessage(environment.getProperty(ReraMessageConstants.FAILED_MESSAGE));

		}
		return ResponseEntity.ok(rs);
	}
}
