package com.capgemini.librarymanagementsystem_jdbc.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import com.capgemini.librarymanagementsystem_jdbc.dto.BookDetails;
import com.capgemini.librarymanagementsystem_jdbc.dto.User;
import com.capgemini.librarymanagementsystem_jdbc.exception.LMSException;
import com.capgemini.librarymanagementsystem_jdbc.utility.JdbcUtility;
import com.mysql.jdbc.Statement;

public class AdminUserDaoImplements implements AdminUserDao{

	
	Connection connection = null;
	// PreparedStatement pstmt = null;
	ResultSet rs = null;
	Statement stmt = null;
	
	@Override
	public boolean registerUser(User user) {
		
		connection = JdbcUtility.getConnection();

		// String query = pro.getProperty("userLoginQuery");
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.userregisterQuery);) {
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getFirstName());
			pstmt.setString(3, user.getLastName());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getPassword());
			pstmt.setLong(6, user.getMobileNo());
			pstmt.setString(7, user.getRole());

			int count = pstmt.executeUpdate();

			if (user.getEmail().isEmpty() && count == 0) {
				return false;
			} else {

				return true;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

	@Override
	public User authUser(String email, String password) {
		
		
		connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.userloginQuery);) {
			pstmt.setString(1, email);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				User bean = new User();
				bean.setId(rs.getInt("id"));
				bean.setFirstName(rs.getString("firstName"));
				bean.setLastName(rs.getString("lastName"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setMobileNo(rs.getLong("mobileNo"));
				bean.setRole(rs.getString("role"));
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
	public List<BookDetails> searchBookByTitle(String bookName) {
		
		
		Connection connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.byNameQuery);) {
			pstmt.setString(1, bookName);
			try (ResultSet rs = pstmt.executeQuery();) {
				LinkedList<BookDetails> beans = new LinkedList<BookDetails>();
				while (rs.next()) {
					BookDetails bean = new BookDetails();
					bean.setBookId(rs.getInt("bookId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthorName(rs.getString("authorName"));
					bean.setPublisherName(rs.getString("publisherName"));
					bean.setCopies(rs.getInt("copies"));
					bean.setBookCategory(rs.getString("bookCategory"));
					beans.add(bean);
				}
				return beans;
			}

		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookDetails> searchBookByAuthor(String authorName) {
		
		
		Connection connection = JdbcUtility.getConnection();
		try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.byAuthorQuery);) {
			pstmt.setString(1, authorName);
			try (ResultSet rs = pstmt.executeQuery();) {
				LinkedList<BookDetails> beans = new LinkedList<BookDetails>();
				while (rs.next()) {
					BookDetails bean = new BookDetails();
					bean.setBookId(rs.getInt("bookId"));
					bean.setBookName(rs.getString("bookName"));
					bean.setAuthorName(rs.getString("authorName"));
					bean.setPublisherName(rs.getString("publisherName"));
					bean.setCopies(rs.getInt("copies"));
					bean.setBookCategory(rs.getString("bookCategory"));
					beans.add(bean);
				}
				return beans;

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public List<BookDetails> searchBookById(int bookId) {
		
		
		connection = JdbcUtility.getConnection();

		try (PreparedStatement statement = connection.prepareStatement(QueryMapper.searchIdQuery);) {
			statement.setInt(1, bookId);
			rs = statement.executeQuery();
			LinkedList<BookDetails> beans = new LinkedList<BookDetails>();
			while (rs.next()) {
				BookDetails bean = new BookDetails();
				bean.setBookId(rs.getInt("bookId"));
				bean.setBookName(rs.getString("bookName"));
				bean.setAuthorName(rs.getString("authorName"));
				bean.setBookCategory(rs.getString("bookCategory"));
				bean.setPublisherName(rs.getString("publisherName"));
				beans.add(bean);
			}
			return beans;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<BookDetails> getBooksInfo() {
	
		
		try (FileInputStream info = new FileInputStream("db.properties");) {
			Properties pro = new Properties();
			pro.load(info);
			Class.forName(pro.getProperty("path"));
			String dburl = pro.getProperty("dburl");
			try (Connection conn = DriverManager.getConnection(dburl, pro);) {

				String query = pro.getProperty("booksInfoQuery");
				PreparedStatement pstmt = conn.prepareStatement(query);
				try (ResultSet rs = pstmt.executeQuery();) {
					LinkedList<BookDetails> beans = new LinkedList<BookDetails>();

					while (rs.next()) {
						BookDetails book = new BookDetails();
						book.setBookId(rs.getInt("bookId"));
						book.setBookName(rs.getString("bookName"));
						book.setBookCategory(rs.getString("bookCategory"));
						book.setAuthorName(rs.getString("authorName"));
						book.setPublisherName(rs.getString("publisherName"));
						book.setCopies(rs.getInt("copies"));

						beans.add(book);
					}
					return beans;

				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	@Override
	public boolean updatePassword(String email, String password, String newPassword, String role) {

		connection = JdbcUtility.getConnection();
		try (PreparedStatement pst = connection.prepareStatement(QueryMapper.selectUpdateQuery)) {
			pst.setString(1, email);
			pst.setString(2, role);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				try (PreparedStatement pstmt = connection.prepareStatement(QueryMapper.updatePasswordQuery);) {
					pstmt.setString(1, newPassword);
					pstmt.setString(2, email);
					pstmt.setString(3, password);
					int count = pstmt.executeUpdate();
					if (count != 0) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				throw new LMSException("User doesnt exist");
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return false;
		}
	}

}
