package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.BookIssue;
import com.capgemini.librarymanagementsystem_jdbc.dto.RequestDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;
import com.capgemini.librarymanagementsystem_jdbc.exception.LMSException;
import com.capgemini.librarymanagementsystem_jdbc.utility.JdbcUtility;
import com.mysql.jdbc.Statement;

public class AdminDaoImplements implements AdminDao{

	
	Connection connection = null;
	ResultSet rs = null;
	Statement stmt = null;
	@Override
	public boolean addBook(BookDetails bookDetail) {
		connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.bookInsertQuery);) {
			pstmt.setInt(1, bookDetail.getBookId());
			pstmt.setString(2, bookDetail.getBookName());
			pstmt.setString(3, bookDetail.getAuthorName());
			pstmt.setString(4, bookDetail.getPublisherName());
			pstmt.setInt(5, bookDetail.getCopies());
			pstmt.setString(6, bookDetail.getBookCategory());

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
	public boolean removeBook(int bookId) {
		
		connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.removeBookQuery);) {
			pstmt.setInt(1, bookId);
			int count = pstmt.executeUpdate();
			if (count != 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateBook(BookDetails book) {
		
		connection = JdbcUtility.getConnection();
		// String query = pro.getProperty("updateBookQuery");
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.updateBookQuery);) {
			pstmt.setString(1, book.getBookName());
			pstmt.setInt(2, book.getBookId());
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
	public boolean bookIssue(int bookId, int id) {
	
		connection = JdbcUtility.getConnection();
		try (PreparedStatement pst = connection.prepareStatement(QueryMapper.selectIssueQuery);) {
			pst.setInt(1, bookId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.selectIssueQuery2)) {
					pstmt.setInt(1, id);
					pstmt.setInt(2, bookId);
					pstmt.setInt(3, id);
					rs = pstmt.executeQuery();
					if (rs.next()) {
						try (PreparedStatement pstmt1 = connection.prepareStatement(QueryMapper.selectIssueQuery3);) {
							pstmt1.setInt(1, bookId);
							pstmt1.setInt(2, id);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Calendar cal = Calendar.getInstance();
							String issueDate = sdf.format(cal.getTime());
							pstmt1.setDate(3, java.sql.Date.valueOf(issueDate));
							cal.add(Calendar.DAY_OF_MONTH, 7);
							String returnDate = sdf.format(cal.getTime());
							pstmt.setDate(4, java.sql.Date.valueOf(returnDate));
							int count = pstmt1.executeUpdate();
							if (count != 0) {
								try (PreparedStatement pstmt2 = connection
										.prepareStatement(QueryMapper.selectIssueQuery4)) {
									pstmt2.setInt(1, id);
									pstmt2.setInt(2, bookId);
									pstmt2.setInt(3, id);
									int isBorrowed = pstmt2.executeUpdate();
									if (isBorrowed != 0) {
										return true;
									} else {
										return false;
									}
								}
							} else {
								throw new LMSException("Book Not issued");
							}
						}
					} else {
						throw new LMSException("The respective user have not placed any request");
					}
				}
			} else {
				throw new LMSException("There is no book exist with bookId" + bookId);
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public List<RequestDetails> showRequests() {
		
		connection = JdbcUtility.getConnection();
		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.requestDetailsQuery);) {
			LinkedList<RequestDetails> beans = new LinkedList<RequestDetails>();
			while (rs.next()) {
				RequestDetails bean = new RequestDetails();
				bean.setId(rs.getInt("id"));
				bean.setFullName(rs.getString("fullName"));
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookIssue> showIssuedBooks() {
		
		
		connection = JdbcUtility.getConnection();
		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showIssuedBooksQuery);) {
			LinkedList<BookIssue> beans = new LinkedList<BookIssue>();
			while (rs.next()) {
				BookIssue bean = new BookIssue();
				bean.setBookId(rs.getInt("bookId"));
				bean.setId(rs.getInt("id"));
				bean.setIssueDate(rs.getDate("issueDate"));
				bean.setReturnDate(rs.getDate("returnDate"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			// e.printStackTrace();
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<User> showUsers() {
		
		connection = JdbcUtility.getConnection();
		try (Statement stmt = (Statement) connection.createStatement();
				ResultSet rs = stmt.executeQuery(QueryMapper.showUsersQuery);) {
			LinkedList<User> beans = new LinkedList<User>();
			while (rs.next()) {
				User bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setMobileNo(rs.getLong("mobileNo"));
				bean.setRole(rs.getString("role"));
				beans.add(bean);
			}
			return beans;

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
		
	}

	

}
