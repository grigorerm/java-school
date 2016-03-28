package com.db.javaschool.finalproject.dao;

import java.util.Date;

import com.db.javaschool.finalproject.entity.Command;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.db.javaschool.finalproject.dao.newsentry.NewsEntryDao;
import com.db.javaschool.finalproject.dao.user.UserDao;
import com.db.javaschool.finalproject.entity.User;

public class DataBaseInitializer {

	private NewsEntryDao newsEntryDao;

	private UserDao userDao;

	private PasswordEncoder passwordEncoder;

	protected DataBaseInitializer() {
		/* Default constructor for reflection instantiation */
	}

	public DataBaseInitializer(UserDao userDao, NewsEntryDao newsEntryDao, PasswordEncoder passwordEncoder) {
		this.userDao = userDao;
		this.newsEntryDao = newsEntryDao;
		this.passwordEncoder = passwordEncoder;
	}

	public void initDataBase() {

		User userUser = new User("user", this.passwordEncoder.encode("user"));
		userUser.addRole("user");
		this.userDao.save(userUser);

		User userMarius = new User("marius", this.passwordEncoder.encode("1234"));
		userUser.addRole("user");
		this.userDao.save(userMarius);

		User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
		adminUser.addRole("user");
		adminUser.addRole("admin");
		this.userDao.save(adminUser);

		long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;

		for (int i = 0; i < 10; i++) {
			Command newCommand = new Command(new Date(timestamp),42,i+1);
			this.newsEntryDao.save(newCommand);
			timestamp += 1000 * 60 * 60;
		}
	}

}