package com.bata.billpunch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "UserDeptModel")
@Table(name = "TM_USER_DEPT")
public class UserDeptModel implements Serializable {

	private static final long serialVersionUID = 187767777L;

	
	@Id
	@Column(name = "DEPT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "depts_Sequence")
	@SequenceGenerator(name = "bills_Sequence", sequenceName = "DEPTS_SEQ",allocationSize = 1)
	private Long deptId;

	@Column(name = "USER_DEPT_KEY")
	private String userDeptKey;
	
	@Column(name = "USER_DEPT")
	private String userDept;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getUserDeptKey() {
		return userDeptKey;
	}

	public void setUserDeptKey(String userDeptKey) {
		this.userDeptKey = userDeptKey;
	}

	public String getUserDept() {
		return userDept;
	}

	public void setUserDept(String userDept) {
		this.userDept = userDept;
	}

	
}
