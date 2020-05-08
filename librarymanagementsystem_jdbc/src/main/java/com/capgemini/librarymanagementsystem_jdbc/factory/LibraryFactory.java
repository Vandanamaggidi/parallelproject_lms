package com.capgemini.librarymanagementsystem_jdbc.factory;

import com.capgemini.librarymanagementsystem_jdbc.dao.AdminDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.AdminDaoImplements;
import com.capgemini.librarymanagementsystem_jdbc.dao.AdminUserDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.AdminUserDaoImplements;
import com.capgemini.librarymanagementsystem_jdbc.dao.UserDao;
import com.capgemini.librarymanagementsystem_jdbc.dao.UserDaoImplements;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminService;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminServiceImplements;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminUserService;
import com.capgemini.librarymanagementsystem_jdbc.service.AdminUserServiceImplements;
import com.capgemini.librarymanagementsystem_jdbc.service.UserService;
import com.capgemini.librarymanagementsystem_jdbc.service.UserServiceImplements;

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
