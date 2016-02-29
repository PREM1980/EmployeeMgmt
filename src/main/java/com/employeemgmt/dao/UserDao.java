package com.employeemgmt.dao;

import com.employeemgmt.model.User;

public interface UserDao {

	com.employeemgmt.entity.User findByUserName(String username);

}