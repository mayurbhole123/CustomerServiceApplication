package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.CustomerModel;

@Repository
public interface CustomerRepo extends JpaRepository<CustomerModel, Integer>{
	public Optional<CustomerModel> findByUserName(String userName);
}
