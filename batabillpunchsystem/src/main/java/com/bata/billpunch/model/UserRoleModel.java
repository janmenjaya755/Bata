package com.bata.billpunch.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "UserRoleModel")
@Table(name = "TL_USER_ROLE")
public class UserRoleModel implements Serializable {

	private static final long serialVersionUID = 187767777L;

	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_Sequence")
	@SequenceGenerator(name = "roles_Sequence", sequenceName = "ROLES_SEQ",allocationSize = 1)
	private Long roleId;
	
	@Column(name = "USER_ROLE_KEY")
	private String userRoleKey;
	
	@Column(name = "USER_ROLE")
	private String userRole;


	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}


	public String getUserRoleKey() {
		return userRoleKey;
	}

	public void setUserRoleKey(String userRoleKey) {
		this.userRoleKey = userRoleKey;
	}

}
