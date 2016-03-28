package com.db.javaschool.finalproject.dao.user;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.db.javaschool.finalproject.dao.Dao;
import com.db.javaschool.finalproject.entity.User;

public interface UserDao extends Dao<User, Long>, UserDetailsService {

	User findByName(String name);

}