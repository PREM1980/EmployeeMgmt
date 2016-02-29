package com.employeemgmt.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.employeemgmt.dao.UserDao;
import com.employeemgmt.entity.UserRole;
@Transactional
public class MyUserDetailsService implements UserDetailsService {

	private UserDao userDao;

	//@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

		// Programmatic transaction management
		/*
		return transactionTemplate.execute(new TransactionCallback<UserDetails>() {

			public UserDetails doInTransaction(TransactionStatus status) {
				com.mkyong.users.model.User user = userDao.findByUserName(username);
				List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

				return buildUserForAuthentication(user, authorities);
			}

		});*/
		
		com.employeemgmt.entity.User user = userDao.findByUserName(username);
		
		List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRoles());
		System.out.println("Prem-User     - Username = " + user.getUsername());
		System.out.println("Prem-Password - password = " + user.getPassword());
		System.out.println("Prem-UserRole - userrole = " + user.getUserRoles());
		
		return buildUserForAuthentication(user, authorities);
		

	}

	// Converts com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUserForAuthentication(com.employeemgmt.entity.User user, List<GrantedAuthority> authorities) {
		boolean enab = false;
		if  (user.getEnabled() == 1){
			enab = true;
		};
	
		return new User(user.getUsername(), user.getPassword(), enab, true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(List<UserRole> list) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : list) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}