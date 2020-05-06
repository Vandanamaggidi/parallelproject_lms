package com.capgemini.librarymanagementsystem_jdbc.dao;

public interface QueryMapper {

	String userregisterQuery = "insert into user values(?,?,?,?,?,?,?)";
	String userloginQuery = "select * from user where email=? and password=?";
	String bookBorrowQuery = "select * from bookdetails where bookId = ?";
	String bookInsertQuery = "insert into bookdetails values(?,?,?,?,?,?)";
	String removeBookQuery = "delete from bookdetails where bookId = ?";
	String updateBookQuery = "update bookdetails set bookName=? where bookId=?";
	String byNameQuery = "select * from bookDetails where bookName = ?";

	String searchIdQuery = "select * from bookDetails where bookId=?";
	String byAuthorQuery = "select * from bookDetails where authorName = ?";
	String byCategoryQuery = "select * from bookDetails where bookCategory = ?";
	String booksInfoQuery = "select * from bookdetails";

	String requestQuery = "insert into requestDetails values(?,(select concat(firstname,'_',lastname) from user where id=?)"
			+ ",?,(select bookname from bookdetails where bookId=?))";
	String bookIssueQuery = "select count(*) as id from bookIssue where id=?";
	String bookBorrowQuery1 = "select * from booksBorrowed where id=?";
	String requestDetailsQuery = "select * from requestDetails";
	String showIssuedBooksQuery = "select * from bookIssue";
	String showUsersQuery = "select * from user";
	String selectUpdateQuery = "select * from user where email=? and role=?";
	String updatePasswordQuery = "update user set password=? where email=? and password=?";
	String selectIssueQuery = "select * from bookdetails where bookId=?";
	String selectIssueQuery2 = "select * from requestDetails where id=? and bookId=? and email=(select email from user where id=?)";

	String selectIssueQuery3 = "insert into bookIssue values(?,?,?,?)";
	String selectIssueQuery4 = "insert into booksBorrowed values(?,?,(select * from booksBorrowed where id=?))";
	String returnQuery1 = "select * from bookdetails where bookId=?";
	String returnQuery2 = "select * from bookIssue where bookId=? and id=?";
	String returnQuery3 = "delete from bookIssue where bookId=? and id=?";
	String returnQuery4 = "delete from booksBorrowed where bookId=? and id=?";
	String retuenQuery5 = "delete from requestDetails where bookId=? and id=?";
	String returnQuery6 = "delete from bookIssue where bookid=? and id=?";
	String retuenQuery7 = "delete from booksBorrowed where bookid=? and id=?";
	String returnQuery8 = "delete from requestDetails where bookid=? and id=?";

}
