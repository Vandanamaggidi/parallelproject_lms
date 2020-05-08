package com.capgemini.librarymanagementsystem.database;

import java.util.ArrayList;
import java.util.List;

import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;

public class LibraryDB {

	public static final List<BookDetails> bookDetails = new ArrayList<BookDetails>();
	public static final List<User> users = new ArrayList<User>();
	public static final List<Admin> admins = new ArrayList<Admin>();
	public static final List<Request> REQUEST = new ArrayList<Request>();

	public static void dupData() {
		
		admins.add(new Admin(12345, "dhana","dhana@gmail.com","Dan23@", 987654321));
		admins.add(new Admin(12321, "ramu", "ramu@gmail.com","Ramu21@", 987342166));
		
		bookDetails.add(new BookDetails(12345, "Sql","programming","BenForta","sams"));
		bookDetails.add(new BookDetails(12344, "TellMeAStory","novel","RavinderSingh","sams"));
	}
}
