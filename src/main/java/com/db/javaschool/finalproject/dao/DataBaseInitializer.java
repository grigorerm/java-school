
package com.db.javaschool.finalproject.dao;

import java.util.Date;

import com.db.javaschool.finalproject.entity.NewsEntry;
import com.db.javaschool.finalproject.entity.Product;
import com.db.javaschool.finalproject.entity.History;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.db.javaschool.finalproject.dao.newsentry.NewsEntryDao;
import com.db.javaschool.finalproject.dao.newsentry.ProductDao;
import com.db.javaschool.finalproject.dao.newsentry.HistoryDao;
import com.db.javaschool.finalproject.dao.user.UserDao;
import com.db.javaschool.finalproject.entity.User;

public class DataBaseInitializer {

	private NewsEntryDao newsEntryDao;
	
	private ProductDao productDao;

	private HistoryDao historyDao;

	private UserDao userDao;

	private PasswordEncoder passwordEncoder;

	protected DataBaseInitializer() {
		/* Default constructor for reflection instantiation */
	}

	public DataBaseInitializer(UserDao userDao, NewsEntryDao newsEntryDao, PasswordEncoder passwordEncoder,ProductDao productDao,HistoryDao historyDao) {
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;

		this.newsEntryDao = newsEntryDao;
		this.productDao = productDao;
		this.historyDao = historyDao;
		
	}

	public void initDataBase() {

		User userUser = new User("user", this.passwordEncoder.encode("user"),"user@user.ro","lidl","str. plopilor nr.10");
		userUser.addRole("user");
		this.userDao.save(userUser);

		User userMarius = new User("marius", this.passwordEncoder.encode("1234"),"marius@admin.ro","carrefour","str. pipera nr.15");
		userMarius.addRole("user");
		userMarius.addRole("admin");
		this.userDao.save(userMarius);

		User userCristi = new User("cristi", this.passwordEncoder.encode("1234"),"cristi@admin.ro","carrefour","str. pipera nr.15");
		userCristi.addRole("user");
		userCristi.addRole("admin");
		this.userDao.save(userCristi);

		User userCosmin = new User("cosmin", this.passwordEncoder.encode("1234"),"cosmin@admin.ro","carrefour","str. pipera nr.15");
		userCosmin.addRole("user");
		userCosmin.addRole("admin");
		this.userDao.save(userCosmin);

		User userAlex = new User("alex", this.passwordEncoder.encode("1234"),"alex@admin.ro","carrefour","str. pipera nr.15");
		userAlex.addRole("user");
		userAlex.addRole("admin");
		this.userDao.save(userAlex);

		User adminUser = new User("admin", this.passwordEncoder.encode("admin"),"admin@user.ro","profi","str. kaimatei nr.10");
		adminUser.addRole("user");
		adminUser.addRole("admin");
		this.userDao.save(adminUser);

		long timestamp = System.currentTimeMillis() - 1000 * 60 * 60 * 24;
		
		for (int i = 0; i < 3; i++) {
			NewsEntry newEntry = new NewsEntry();
			newEntry.setContent("Text " + i);
			newEntry.setDate(new Date(timestamp));
			this.newsEntryDao.save(newEntry);
			
			timestamp += 1000 * 60 * 60;
		}
		History history = new History();
		this.historyDao.save(history);

		Product product = new Product("Laptop i3","Asus",1200,5);
		this.productDao.save(product);
		product = new Product("Macbook Pro i5","Apple",4400,10);
		this.productDao.save(product);
		product = new Product("Tableta","Samsung",800,12);
		this.productDao.save(product);
		product = new Product("Telefon","LG",440,1);
		this.productDao.save(product);
		product = new Product("Telefon","Allview",250,0);
		this.productDao.save(product);
		product = new Product("Mouse optic","Hama",50,12);
		this.productDao.save(product);
		product = new Product("Laptop i5","Samsung",1800,1);
		this.productDao.save(product);
		product = new Product("USB Stick","Kingstone",40,4000);
		this.productDao.save(product);
	
	}

}