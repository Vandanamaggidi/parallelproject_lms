package com.capgemini.librarymanagementsystem.controller;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.capgemini.librarymanagementsystem.database.LibraryDB;
import com.capgemini.librarymanagementsystem.dto.Admin;
import com.capgemini.librarymanagementsystem.dto.BookDetails;
import com.capgemini.librarymanagementsystem.dto.Request;
import com.capgemini.librarymanagementsystem.dto.User;
import com.capgemini.librarymanagementsystem.exception.LMSException;
import com.capgemini.librarymanagementsystem.factory.LibraryFactory;
import com.capgemini.librarymanagementsystem.service.AdminService;
import com.capgemini.librarymanagementsystem.service.UserService;
import com.capgemini.librarymanagementsystem.validation.LibraryValidation;


public class LibrarySupport {
	
	public static void doStart() {
		
		LibraryDB.dupData();
		
		boolean flag = false;
		int regId = 0;
		String regName = null;
		long regMobile = 0;
		String regEmail = null;
		String regPassword = null;
		int regId1 = 0;
		String regName1 = null;
		long regMobile1 = 0;
		String regEmail1 = null;
		String regPassword1 = null;
		int bookId = 0;
		String bookAuthor = null;
		String bookName = null;
		String bookCategory = null;
		String bookPublisherName = null;

		LibraryValidation validation = new LibraryValidation();

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

		do {
			try {
				System.out.println("------- WELCOME TO LIBRARY -------");
				System.out.println("Press 1 To Admin Page");
				System.out.println("Press 2 To User Page");
				System.out.println("----------------------------------------");
				int i = scanner.nextInt();
				switch (i) {
				case 1:
					AdminService service = LibraryFactory.getAdminService();
					do {
						try {
							System.out
									.println("-------------------------------------------");
							System.out.println("Press 1 To Admin Registration");
							System.out.println("Press 2 To Admin Login");
							System.out.println("Press 3 To Exit");
							System.out
									.println("-------------------------------------------");
							int choice = scanner.nextInt();
							switch (choice) {
							case 1:
								do {
									try {
										System.out.println("Enter ID to Register : ");
										regId = scanner.nextInt();
										validation.validateId(regId);
										flag = true;
									} catch (InputMismatchException e) {
										System.err.println("ID should consist of only digits");
										flag = false;
                                        scanner.next();
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Name to Register : ");
										regName = scanner.next();
										validation.validateName(regName);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should consists of only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter MobileNumber to Register : ");
										regMobile = scanner.nextLong();
										validation.validateMobileNo(regMobile);
										flag = true;
									} catch (InputMismatchException e) {
                                         System.err.println("Mobile Number  should consists of only numbers");
                                         flag = false;
                                         scanner.next();
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Email to Register : ");
										regEmail = scanner.next();
										validation.validateEmail(regEmail);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println(
												"Enter proper emailId");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Password :");
										regPassword = scanner.next();
										validation.validatePassword(regPassword);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Enter correct Password ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									} 
								} while (!flag);

								Admin bean = new Admin();
								bean.setAdminId(regId);
								bean.setName(regName);
								bean.setMobileNo(regMobile);
								bean.setEmail(regEmail);
								bean.setPassword(regPassword);

								boolean check = service.registerAdmin(bean);
								if (check) {
									System.out.println("successfully registered");
								} else {
									System.out.println("Email already exist");
								}
								break;

							case 2:
								System.out.println("Enter registered email to login : ");
								String email = scanner.next();
								System.out.println("Enter registered Password to login : ");
								String password = scanner.next();
								try {
									@SuppressWarnings("unused")
									Admin authBean = service.authAdmin(email, password);
									System.out.println("Logged in");

									do {
										try {
											System.out.println(
													"<--------------------------------------------------------------------->");
											System.out.println("PRESS-1 TO ADD BOOKS");
											System.out.println("PRESS-2 TO UPDATE THE BOOK");
											System.out.println("PRESS-3 TO SEARCH BOOK BY AUTHOR");
											System.out.println("PRESS-4 TO SEARCH BOOK BY TITLE");
											System.out.println("PRESS-5 TO SEARCH BOOK BY CATEGORY");
											System.out.println("PRESS-6 TO REMOVE THE BOOK");
											System.out.println("PRESS-7 TO GET INFO ABOUT ALL BOOKS");
											System.out.println("PRESS-8 TO ISSUE THE BOOK");
											System.out.println("PRESS-9 TO VIEW STUDENTS");
											System.out.println("PRESS-10 TO VIEW REQUESTS");
											System.out.println("PRESS-11 TO RECEIVE RETURNED BOOK");
											System.out.println("PRESS-12 TO  RETURN BACK TO MAIN");
											System.out.println(
													"<--------------------------------------------------------------------->");
											int choice1 = scanner.nextInt();
											switch (choice1) {
											case 1:

												do {
													try {
														System.out.println("Enter BookID to add : ");
														bookId = scanner.nextInt();
														validation.validateId(bookId);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("Id should contains only digits");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter BookName : ");
														bookName = scanner.next();
														validation.validateName(bookName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println("BookName should contains only Alphabets");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter AuthorName : ");
														bookAuthor = scanner.next();
														validation.validateName(bookAuthor);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err
																.println("AuthorName should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter Category : ");
														bookCategory = scanner.next();
														validation.validateName(bookCategory);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println(
																"Book-Category should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												do {
													try {
														System.out.println("Enter PublisherName : ");
														bookPublisherName = scanner.next();
														validation.validateName(bookPublisherName);
														flag = true;
													} catch (InputMismatchException e) {
														flag = false;
														System.err.println(
																"BookPublisherName should contains only Alphabates");
													} catch (LMSException e) {
														flag = false;
														System.err.println(e.getMessage());
													}
												} while (!flag);

												BookDetails bean1 = new BookDetails();
												bean1.setBookId(bookId);
												bean1.setBookName(bookName);
												bean1.setAuthorName(bookAuthor);
												bean1.setPublisherName(bookPublisherName);
												bean1.setBookCategory(bookCategory);
												// bean1.setIssuedate(bookIssuedate);
												boolean check2 = service.addBook(bean1);
												if (check2) {
													System.out.println("Book Added");
												} else {
													System.out.println("Book already exist");
												}
												break;

											case 2:
												System.out.println("Enter the updated id :");
												int bid = scanner.nextInt();
												System.out.println("enter name");
												String title = scanner.next();
												System.out.println("enter author");
												String bauthor = scanner.next();
												System.out.println("enter category");
												String category = scanner.next();
												BookDetails bean2 = new BookDetails();
												bean2.setBookId(bid);
												bean2.setBookName(title);
												bean2.setAuthorName(bauthor);
												bean2.setBookCategory(category);
												boolean updated = service.updateBook(bean2);
												if (updated) {
													System.out.println("Book is updated");
												} else {
													System.out.println("Book is not updated");
												}
												break;

											case 3:
												System.out.println("Search the book by the Author Name:");
												String author = scanner.next();

												BookDetails bean3 = new BookDetails();
												bean3.setAuthorName(author);
												List<BookDetails> bookauthor = service.searchBookByAuthor(author);

												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookCategory", "AuthorName", "PublisherName"));
												for (BookDetails bookBean : bookauthor) {

													if (bookBean != null) {
														System.out.println(
																"*****************************************************");
														System.out.println(bookBean.toString());

													} else {
														System.out.println("No books are available written by this author");
													}
												}
												break;

											case 4:
												System.out.println("  Search the book by the Book_Title :");
												String bookTitle = scanner.next();

												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookCategory", "AuthorName", "PublisherName"));
												BookDetails bean4 = new BookDetails();

												bean4.setBookName(bookTitle);
												List<BookDetails> booktitle = service.searchBookByTitle(bookTitle);

												for (BookDetails bookBean : booktitle) {
													if (bookBean != null) {
														System.out.println(
																"*****************************************************");
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available with this title.");
													}
												}
												break;

											case 5:
												System.out.println("Search the book by the Book Id :");
												String category1 = scanner.next();

												BookDetails bean5 = new BookDetails();
												bean5.setBookCategory(category1);
												List<BookDetails> bookIds = service.searchBookBycategory(category1);
												for (BookDetails bookBean : bookIds) {
													if (bookBean != null) {
														System.out.println("-----------------------------------");
														System.out.println("Book_Id is-->" + bookBean.getBookId());
														System.out.println("Book_Name is-->" + bookBean.getBookName());
														System.out.println("Book_Author is-->" + bookBean.getAuthorName());
														System.out.println("Book_Category is-->" + bookBean.getBookCategory());
														System.out.println(
																"Book_PublisherName is-->" + bookBean.getPublisherName());
													} else {
														System.out.println("No books are available with this Id.");
													}
												}
												break;

											case 6:
												System.out.println("Enter the book_Id to delete :");
												int book_Id = scanner.nextInt();
												if (book_Id == 0) {
													System.out.println("Enter the Valid Book_Id");
												} else {
													BookDetails bean6 = new BookDetails();
													bean6.setBookId(book_Id);
													boolean remove = service.removeBook(book_Id);
													if (remove) {
														System.out.println("The Book is removed");
													} else {
														System.out.println("The Book is not removed");
													}
												}
												break;

											case 7:
												LinkedList<BookDetails> info = service.getBooksInfo();
												for (BookDetails bookBean : info) {
													System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
															"BookName", "BookCategory", "AuthorName", "PublisherName"));
													if (bookBean != null) {
														System.out.println(
																"*****************************************************");
														System.out.println(bookBean.toString());
													} else {
														System.out.println("Books info is not present");
													}
												}
												break;

											case 8:
												User userBean2 = new User();
												BookDetails bookBean2 = new BookDetails();
												System.out.println("enter Book Id");
												int bId = scanner.nextInt();
												System.out.println("enter User Id");
												int uId = scanner.nextInt();

												bookBean2.setBookId(bId);
												userBean2.setUserId(uId);

												try {
													boolean isIssued = service.bookIssue(userBean2, bookBean2);
													if (isIssued) {
														System.out.println("Book Issued");
													} else {
														System.out.println("Book cannot be issued");
													}

												} catch (Exception e) {
													System.out.println("Invalid data request book cannot be issued");
												}
												break;

											case 9:

												try {
													System.out.println("Users of Library are :");
													System.out.println("-------------------------------");

													List<User> userInfos = service.showUsers();
													for (User infos : userInfos) {

														System.out.println("User id ---------- " + infos.getUserId());
														System.out.println("User Name -------- " + infos.getUserName());
														System.out.println("User Email------ " + infos.getEmail());
														System.out.println("User No Of Books Borrowed ------- "
																+ infos.getNoOfBooksBorrowed());

													}
												} catch (Exception e) {
													System.out.println("no books present in library");
												}
												break;

											case 10:
												try {
													System.out.println("Requests for Books are :");
													System.out.println("-------------------------------");

													List<Request> requestInfos = service.showRequests();
													for (Request info1 : requestInfos) {

														System.out.println(
																"Book id ---------- " + info1.getBookDetails().getBookId());
														System.out.println(
																"Book Name -------- " + info1.getBookDetails().getAuthorName());
														System.out.println(
																"User id----------- " + info1.getUserInfo().getUserId());
														System.out.println(
																"User Name -------- " + info1.getUserInfo().getUserName());
														System.out.println("Book Issued ------" + info1.isIssued());
														System.out.println("Book Returned------" + info1.isReturned());

													}
												} catch (Exception e) {
													System.out.println("no books present in library");
												}
												break;

											case 11:
												System.out.println("Receive Returned Book");
												System.out.println("-----------------------");
												System.out.println("Enter Student Id");
												int user_Id = scanner.nextInt();
												System.out.println("Enter Book Id");
												int book_id = scanner.nextInt();

												User student = new User();
												BookDetails book = new BookDetails();
												student.setUserId(user_Id);
												;
												book.setBookId(book_id);
												boolean isReceive = service.isBookReceived(student, book);
												if (isReceive) {
													System.out.println(" Received Returned book");
												} else {
													System.out.println("Invalid ! Admin unable to receive");
												}
												break;

											case 12:
												doStart();

											default:
												System.out.println("Invalid Choice");
												break;
											}
										} catch (InputMismatchException e) {
											System.out.println("Invalid entry please provide only positive integer");
											scanner.nextLine();
										}
									} while (true);
								} catch (Exception e) {
									System.out.println("Invalid Credentials");
								}
								break;

							case 3:
								doStart();
								break;

							default:
								System.out.println("Invalid Choice");
								break;
							}
						} catch (InputMismatchException e) {
							System.out.println("Invalid entry please provide only positive integer");
							scanner.nextLine();
						}
					} while (true);

				case 2:
					UserService service1 = LibraryFactory.getUserService();
					do {
						try {
							System.out
									.println("<--------------------------------------------------------------------->");
							System.out.println("PRESS-1 TO STUDENT REGISTER");
							System.out.println("PRESS-2 TO STUDENT LOGIN");
							System.out.println("PRESS-3 TO RETURN BACK TO MAIN");
							System.out
									.println("<--------------------------------------------------------------------->");
							int choice = scanner.nextInt();
							switch (choice) {
							case 1:
								do {
									try {
										System.out.println("Enter ID to register as Student : ");
										regId1 = scanner.nextInt();
										validation.validateId(regId1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Id should contains only digits");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Name to register : ");
										regName1 = scanner.next();
										validation.validateName(regName1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Name should contains only Alphabates");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter MobileNumber to register : ");
										regMobile = scanner.nextLong();
										validation.validateMobileNo(regMobile);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Mobile Number  should contains only numbers");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Email to register : ");
										regEmail1 = scanner.next();
										validation.validateEmail(regEmail1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Email should be proper ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								do {
									try {
										System.out.println("Enter Password to register : ");
										regPassword1 = scanner.next();
										validation.validatePassword(regPassword1);
										flag = true;
									} catch (InputMismatchException e) {
										flag = false;
										System.err.println("Enter correct Password ");
									} catch (LMSException e) {
										flag = false;
										System.err.println(e.getMessage());
									}
								} while (!flag);

								User bean1 = new User();
								bean1.setUserId(regId1);
								bean1.setUserName(regName1);
								bean1.setMobileNo(regMobile1);
								bean1.setEmail(regEmail1);
								bean1.setPassword(regPassword1);

								boolean check = service1.registerUser(bean1);
								if (check) {
									System.out.println("REGISTERED SUCCESSFULLY");
								} else {
									System.out.println("Email already exist");
								}
								break;

							case 2:
								System.out.println("Enter registered email to login : ");
								String email = scanner.next();
								System.out.println("Enter registered Password to login : ");
								String password = scanner.next();
								try {
									@SuppressWarnings("unused")
									User StudentBean = service1.authUser(email, password);
									System.out.println("LOGGED IN SUCCESSFULLY");
									do {
										try {
											User studentBean = service1.authUser(email, password);
											System.out.println("Logged in");
											do {
												System.out.println("--------------------------------------------");
												System.out.println("Press 1 to Search the Book by Author");
												System.out.println("Press 2 to Search the Book by Title");
												System.out.println("Press 3 to Search the Book by Category");
												System.out.println("Press 4 to Get the Books Information");
												System.out.println("Press 5 to Request the Book");
												System.out.println("Press 6 to Return the Book");
												System.out.println("Press 7 to main");
												System.out.println("--------------------------------------------");
												int choice2 = scanner.nextInt();
												switch (choice2) {
												case 1:
													System.out.println("Search the book by the Author Name :");
													String author = scanner.next();

													BookDetails bean2 = new BookDetails();
													bean2.setAuthorName(author);
													List<BookDetails> bookauthor = service1.searchBookByAuthor(author);
													for (BookDetails bookBean : bookauthor) {

														if (bookBean != null) {
															System.out.println(
																	"*****************************************************");
															System.out.println(bookBean.toString());
															System.out.println("-----------------------------------");

														} else {
															System.out.println("No books are available written by this author");
														}
													}
												break;

											case 2:
												System.out.println("Search the book by the Book_Title :");
												String book_Name = scanner.next();

												BookDetails bean3 = new BookDetails();
												bean3.setBookName(book_Name);
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookCategory", "AuthorName", "PublisherName"));
												List<BookDetails> booktitle = service1.searchBookByTitle(book_Name);
												for (BookDetails bookBean : booktitle) {
													if (bookBean != null) {
														System.out.println(
																"*****************************************************");
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available with this title.");
													}
												}
												break;

											case 3:
												System.out.println("  Search the book by the Book_Category :");
												String book_Category = scanner.next();

												BookDetails bean4 = new BookDetails();
												bean4.setBookCategory(book_Category);

												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookCategory", "AuthorName", "PublisherName"));
												List<BookDetails> bookIds = service1.searchBookBycategory(book_Category);
												for (BookDetails bookBean : bookIds) {
													if (bookBean != null) {

														System.out.println(
																"*****************************************************");
														System.out.println(bookBean.toString());
													} else {
														System.out.println("No books are available with this Id.");
													}
												}
												break;

											case 4:
												LinkedList<BookDetails> info = service1.getBooksInfo();
												System.out.println(String.format("%-10s %-10s %-13s %-15s %s", "BookId",
														"BookName", "BookCategory", "AuthorName", "PublisherName"));
												for (BookDetails bookBean : info) {

													if (bookBean != null) {
														System.out.println(
																"*****************************************************");
														System.out.println(bookBean.toString());
													} else {
														System.out.println("Books info is not present");
													}
												}
												break;

											case 5:
												System.out.println("Enter book id");
												int bId = scanner.nextInt();
												BookDetails bookBean = new BookDetails();
												bookBean.setBookId(bId);

												System.out.println("Enter user id");
												int userId = scanner.nextInt();
												User userBean = new User();
												userBean.setUserId(userId);
												;

												try {
													Request request = service1.bookRequest(userBean, bookBean);
													System.out.println("Request placed to admin");
													System.out.println("-----------------------------------");
													System.out.println("User Id-----" + request.getUserInfo().getUserId());
													System.out.println("User name---" + request.getUserInfo().getUserName());
													System.out.println("Book Id-----" + request.getBookDetails().getBookId());
													System.out.println("Book Name---" + request.getBookDetails().getBookName());

												} catch (Exception e) {

													System.out.println("Enter valid data or Invalid Request");
												}
												break;

											case 6:
												System.out.println("Returning a book:");
												System.out.println("------------------");
												System.out.println("Enter User Id :");
												int user = scanner.nextInt();
												System.out.println("Enter Book Id : ");
												int book = scanner.nextInt();
												User userBean7 = new User();
												userBean7.setUserId(user);
												
												BookDetails bookBean7 = new BookDetails();
												bookBean7.setBookId(book);
												

												try {
													Request requestInfo = service1.bookReturn(userBean7, bookBean7);
													System.out.println("Book is Returning to Admin");
													System.out.println("-----------------------------------");
													System.out
															.println("User Id ------" + requestInfo.getUserInfo().getUserId());
													System.out.println(
															"Book Id ------" + requestInfo.getBookDetails().getBookId());
													System.out.println("Is Returning --" + requestInfo.isReturned());

												} catch (Exception e) {
													System.out.println("Invalid Return");
												}
												break;

											case 7:
												doStart();

											default:
												break;
											}
											
										}while(true);
										}
											catch (InputMismatchException e) {
											System.out.println("Invalid entry please provide only positive integer");
											scanner.nextLine();
										}
									} while (true);
								} catch (Exception e) {
									System.out.println("Invalid Credentials");
								}
								break;
							case 3:
								doStart();
								break;

							default:
								System.out.println("Invalid Choice");
								System.err.println("Choice must be 1 or 2");
								break;
							}
						} catch (InputMismatchException e) { //if we give string in 1 n 2 n 3
							System.out.println("Invalid entry please provide only positive integer");
							scanner.nextLine();
						}
					} while (true);
				}
			} catch (InputMismatchException e) {  ////if we give string in 1 n 2
				System.out.println("Invalid entry please provide only positive integer");
				scanner.nextLine();
			}
		} while (true);
	
	}

}
