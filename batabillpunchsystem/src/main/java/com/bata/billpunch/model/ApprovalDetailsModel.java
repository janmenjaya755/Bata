package com.bata.billpunch.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity(name = "ApprovalDetailsModel")
@Table(name = "TL_APPROVAL_DETAILS")
public class ApprovalDetailsModel implements Serializable {

	private static final long serialVersionUID = 13332225667L;

	@Id
	@Column(name = "APP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approvals_Sequence")
	@SequenceGenerator(name = "approvals_Sequence", sequenceName = "APPROVAL_SEQ",allocationSize = 1)
	private Long appId;

	@Column(name = "BILL_ID_FK")
	private Long billpunchFk;

	@Column(name = "LOGIN_ID")
	private Long loginId;

	@Column(name = "APPROVER_ID")
	private Long approverId;
	
	@Column(name = "USER_ROLE")
	private String userRole;
	
	@Column(name = "BILL_UNIQUE_CODE")
	private String billUniqueCode;
	
	@Column(name = "FIN_USER_NAME")
	private String finUserName;
	
	@Column(name = "APPROVER_NAME")
	private String approverName;
	
	@Column(name = "ADMIN_OFF_NAME")
	private String adminOffName;
	
	@Column(name = "REMARKS")
	private String remarks;

	@Column(name = "FIN_USER_STATUS")
	private String finUserStatus;
	
	@Column(name = "APPROVER_STATUS")
	private String approverStatus;
	
	@Column(name = "ADMIN_STATUS")
	private String adminStatus;

	@Column(name = "MODIFIED_ON")
	private Calendar modifiedOn;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_ON")
	private Calendar createdOn;

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Long getBillpunchFk() {
		return billpunchFk;
	}

	public void setBillpunchFk(Long billpunchFk) {
		this.billpunchFk = billpunchFk;
	}

	public Long getLoginId() {
		return loginId;
	}

	public void setLoginId(Long loginId) {
		this.loginId = loginId;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public String getFinUserName() {
		return finUserName;
	}

	public void setFinUserName(String finUserName) {
		this.finUserName = finUserName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}

	public String getAdminOffName() {
		return adminOffName;
	}

	public void setAdminOffName(String adminOffName) {
		this.adminOffName = adminOffName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getFinUserStatus() {
		return finUserStatus;
	}

	public void setFinUserStatus(String finUserStatus) {
		this.finUserStatus = finUserStatus;
	}

	public String getApproverStatus() {
		return approverStatus;
	}

	public void setApproverStatus(String approverStatus) {
		this.approverStatus = approverStatus;
	}

	public String getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(String adminStatus) {
		this.adminStatus = adminStatus;
	}

	public Calendar getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Calendar modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Calendar getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Calendar createdOn) {
		this.createdOn = createdOn;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getBillUniqueCode() {
		return billUniqueCode;
	}

	public void setBillUniqueCode(String billUniqueCode) {
		this.billUniqueCode = billUniqueCode;
	}



}
