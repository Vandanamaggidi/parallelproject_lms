package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssue;
import com.capgemini.librarymanagementsystem_jdbc.dto.BooksBorrowed;
import com.capgemini.librarymanagementsystem_jdbc.exception.LMSException;
import com.capgemini.librarymanagementsystem_jdbc.utility.JdbcUtility;
import com.mysql.jdbc.Statement;

public class UserDaoImplements implements UserDao {

	Connection connection = null;
	ResultSet rs = null;
	Statement stmt = null;


	@Override
	public boolean request(int bookId, int id) {

		connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.requestQuery);) {

			pstmt.setInt(1, id);
			pstmt.setInt(2, id);
			pstmt.setInt(3, bookId);
			pstmt.setInt(4, bookId);
			int count = pstmt.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public BookDetails borrow(int bookId) {

		connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.bookBorrowQuery);) {
			ResultSet rs = pstmt.executeQuery();
			pstmt.setInt(1, bookId);
			if (rs.next()) {
				BookDetails bean = new BookDetails();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setBookCategory(rs.getString("bookCategory"));
				bean.setPublisherName(rs.getString("publisherName"));
				bean.setCopies(rs.getInt("copies"));
				return bean;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}



	@Override
	public boolean returnBook(int bookId, int id, String status) {
		connection = JdbcUtility.getConnection();
		try (PreparedStatement pst = connection.prepareStatement(QueryMapper.returnQuery1);) {
			pst.setInt(1, bookId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.returnQuery2);) {
					pstmt.setInt(1, bookId);
					pstmt.setInt(2, id);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						Date issueDate = rs.getDate("issueDate");
						Calendar cal = Calendar.getInstance();
						Date returnDate = rs.getDate("returnDate");
						long difference = issueDate.getTime() - returnDate.getTime();
						float daysBetween = (difference / (1000 * 60 * 60 * 24));
						if (daysBetween > 7) {
							float fine = daysBetween * 5;
							System.out.println("The user has to pay the fine of the respective book of Rs:" + fine);
							if (status == "yes") {
								try (PreparedStatement pstmt1 = connection
										.prepareStatement(QueryMapper.returnQuery3);) {
									pstmt1.setInt(1, bookId);
									pstmt1.setInt(2, id);
									int count = pstmt1.executeUpdate();
									if (count != 0) {
										try (PreparedStatement pstmt2 = connection
												.prepareStatement(QueryMapper.returnQuery4);) {
											pstmt2.setInt(1, bookId);
											pstmt2.setInt(2, id);
											int isReturned = pstmt2.executeUpdate();
											if (isReturned != 0) {
												try (PreparedStatement pstmt3 = connection
														.prepareStatement(QueryMapper.retuenQuery5);) {
													pstmt3.setInt(1, bookId);
													pstmt3.setInt(2, id);
													int isRequestDeleted = pstmt3.executeUpdate();
													if (isRequestDeleted != 0) {
														return true;
													} else {
														return false;
													}
												}
											} else {
												return false;
											}
										}
									} else {
										return false;
									}
								}
							} else {
								throw new LMSException("The User has to pay fine for delaying book return");
							}
						} else {
							try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapper.returnQuery6);) {
								pstmt1.setInt(1, bookId);
								pstmt1.setInt(2, id);
								int count = pstmt1.executeUpdate();
								if (count != 0) {
									try (PreparedStatement pstmt2 = connection
											.prepareStatement(QueryMapper.retuenQuery7);) {
										pstmt2.setInt(1, bookId);
										pstmt2.setInt(2, id);
										int isReturned = pstmt2.executeUpdate();
										if (isReturned != 0) {
											try (PreparedStatement pstmt3 = connection
													.prepareStatement(QueryMapper.returnQuery8);) {
												pstmt3.setInt(1, bookId);
												pstmt3.setInt(2, id);
												int isRequestDeleted = pstmt3.executeUpdate();
												if (isRequestDeleted != 0) {
													return true;
												} else {
													return false;
												}
											}
										} else {
											return false;
										}
									}
								} else {
									return false;
								}
							}
						}
					} else {
						throw new LMSException("This respective user hasn't borrowed any book");
					}
				}

			} else {
				throw new LMSException("No book exist with bookId" + bookId);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}



	@Override
	public LinkedList<BookIssue> bookHistoryDetails(int uId) {

		connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.bookIssueQuery);) {
			pstmt.setInt(1, uId);
			ResultSet rs = pstmt.executeQuery();
			LinkedList<BookIssue> beans = new LinkedList<BookIssue>();
			while (rs.next()) {
				BookIssue issueDetails = new BookIssue();
				issueDetails.setId(rs.getInt("id"));
				beans.add(issueDetails);
			}
			return beans;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BooksBorrowed> borrowedBook(int id) {

		connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.bookBorrowQuery1);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			LinkedList<BooksBorrowed> beans = new LinkedList<BooksBorrowed>();
			while (rs.next()) {
				BooksBorrowed listOfbooksBorrowed = new BooksBorrowed();
				listOfbooksBorrowed.setId(rs.getInt("id"));
				listOfbooksBorrowed.setBookId(rs.getInt("bookId"));
				listOfbooksBorrowed.setEmail(rs.getString("email"));
				beans.add(listOfbooksBorrowed);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}


}
