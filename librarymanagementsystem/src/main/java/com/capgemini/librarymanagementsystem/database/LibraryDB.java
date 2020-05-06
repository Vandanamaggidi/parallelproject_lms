package com.capgemini.librarymanagementsystem.database;

import java.util.LinkedList;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public class LibraryDB {

	public static final LinkedList<BookDetails> bookDetails = new LinkedList<BookDetails>();
	public static final LinkedList<User> users = new LinkedList<User>();
	public static final LinkedList<Admin> admins = new LinkedList<Admin>();
	public static final LinkedList<Request> REQUEST = new LinkedList<Request>();

}
