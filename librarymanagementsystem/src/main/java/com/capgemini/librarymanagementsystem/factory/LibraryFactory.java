package com.capgemini.librarymanagementsystem.factory;

import com.capgemini.librarymanagementsystem.dao.AdminDao;
import com.capgemini.librarymanagementsystem.dao.AdminDaoImplements;
import com.capgemini.librarymanagementsystem.dao.UserDao;
import com.capgemini.librarymanagementsystem.dao.UserDaoImplememts;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.AdminServiceImplements;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.service.UserServiceImplements;

public class LibraryFactory {
	public static AdminDao getAdminDao() {
		return new AdminDaoImplements();
	}

	public static AdminService getAdminService() {
		return new AdminServiceImplements();
	}

	public static UserDao getUserDao() {
		return new UserDaoImplememts();
	}

	public static UserService getUserService() {
		return new UserServiceImplements();
	}

}
