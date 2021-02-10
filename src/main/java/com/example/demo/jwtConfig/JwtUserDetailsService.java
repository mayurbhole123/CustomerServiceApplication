package com.example.demo.jwtConfig;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomerModel;
import com.example.demo.repo.CustomerRepo;

/**
 * User service class to validate user is available or valid or not.
 *
 */
@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private CustomerRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<CustomerModel> user = repo.findByUserName(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.get().getUserName(),
				user.get().getPassword(), getAuthority(user.get()));
	}

	@SuppressWarnings("unchecked")
	private Set getAuthority(CustomerModel customer) {
		Set authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" +customer.getRole().getRoleCode()));
		return authorities;
	}
}
