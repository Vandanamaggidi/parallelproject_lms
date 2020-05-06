package com.capgemini.librarymanagementsystem_hibernate.factory;

import com.capgemini.librarymanagementsystem_hibernate.dao.UserDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.UserDaoImplements;
import com.capgemini.librarymanagementsystem_hibernate.service.UserService;
import com.capgemini.librarymanagementsystem_hibernate.service.UserServiceImplements;

public class LibraryFactory {

	public static UserDao getUserDao() {
		return new UserDaoImplements();
	}

	public static UserService getUserService() {
		return new UserServiceImplements();
	}
}
