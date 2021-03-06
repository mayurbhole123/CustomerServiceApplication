package com.example.demo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.RoleModel;

/**
 * Dao implementation class having all queries related to roles.
 *
 */
@Repository
public class RoleDaoImplementation implements RoleDao {

	@Autowired
	SessionFactory sessionFactory;

	/**
	 * Find role by role id.
	 */
	@Override
	public RoleModel findByRoleId(int roleId) {
		@SuppressWarnings("unchecked")
		Query<RoleModel> query = sessionFactory.getCurrentSession().createQuery("from RoleModel where roleId=:roleId");
		query.setParameter("roleId", roleId);
		return (RoleModel) query.getResultList().get(0);
	}

	/**
	 * Find all roles.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<RoleModel> findAllRoles() {
		return (List<RoleModel>) sessionFactory.getCurrentSession().createQuery("from RoleModel").getResultList();
	}

}
