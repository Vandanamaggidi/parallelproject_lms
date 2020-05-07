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

	public static void dupData() {
		
		admins.add(new Admin(12345, "dhana","dhana@gmail.com","Dan23@", 987654321));
		admins.add(new Admin(12321, "ramu", "ramu@gmail.com","Ramu21@", 987342166));
		
		bookDetails.add(new BookDetails(12345, "Sql","programming","BenForta","sams"));
		bookDetails.add(new BookDetails(12344, "TellMeAStory","novel","RavinderSingh","sams"));
	}
}
