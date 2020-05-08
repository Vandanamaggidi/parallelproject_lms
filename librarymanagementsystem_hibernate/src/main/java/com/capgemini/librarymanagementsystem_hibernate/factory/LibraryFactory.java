package com.capgemini.librarymanagementsystem_hibernate.factory;

import com.capgemini.librarymanagementsystem_hibernate.dao.AdminDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.AdminDaoImplements;
import com.capgemini.librarymanagementsystem_hibernate.dao.AdminUserDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.AdminUserDaoImplements;
import com.capgemini.librarymanagementsystem_hibernate.dao.UserDao;
import com.capgemini.librarymanagementsystem_hibernate.dao.UserDaoImplements;
import com.capgemini.librarymanagementsystem_hibernate.service.AdminService;
import com.capgemini.librarymanagementsystem_hibernate.service.AdminServiceImplements;
import com.capgemini.librarymanagementsystem_hibernate.service.AdminUserService;
import com.capgemini.librarymanagementsystem_hibernate.service.AdminUserServiceImplements;
import com.capgemini.librarymanagementsystem_hibernate.service.UserService;
import com.capgemini.librarymanagementsystem_hibernate.service.UserServiceImplements;


public class LibraryFactory {

	public static UserDao getUserDao() {
		return new UserDaoImplements();
	}

	public static UserService getUserService() {
		return new UserServiceImplements();
	}
	
	public static AdminDao getAdminDao() {
		return new AdminDaoImplements();
	}
	
	public static AdminService getAdminService() {
		return new AdminServiceImplements();
	}
	
	public static AdminUserDao getAdminUserDao() {
		return new AdminUserDaoImplements();
	}
	
	public static AdminUserService getAdminUserService() {
		return new AdminUserServiceImplements();
	}
}
