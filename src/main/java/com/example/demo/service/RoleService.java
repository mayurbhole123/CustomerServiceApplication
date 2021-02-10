package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RoleModel;

/**
 * Interface having abstract method which will be implemented by service
 * implementation class.
 *
 */
public interface RoleService {
	public RoleModel findByRoleId(int roleId);

	public List<RoleModel> findAllRoles();
}
