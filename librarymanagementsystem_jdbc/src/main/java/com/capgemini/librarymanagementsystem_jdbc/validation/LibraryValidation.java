package com.capgemini.librarymanagementsystem_jdbc.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.librarymanagementsystem_jdbc.exception.LMSException;

public class LibraryValidation {

	public boolean validateId(int id) throws LMSException {
		String idRegEx = "[1-9]{1}[0-9]{4}";
		boolean result = false;
		if (Pattern.matches(idRegEx, String.valueOf(id))) {
			result = true;
		} else {
			throw new LMSException("Id should contains exactly 5 digits");
		}
		return result;
	}

	public boolean validateName(String name) throws LMSException {
		String nameRegEx = "^(?=.{3,20}$)(?![.-])(?!.*[.-]{2})[a-zA-Z0-9.-]+(?<![.-])";
		boolean result = false;
		Pattern pattern = Pattern.compile(nameRegEx);
		Matcher matcher = pattern.matcher(name);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException(
					"Name should  contains only Alpabates and should be minimun 3 and maximum 20 letters");
		}
		return result;
	}

	public boolean validateMobileNo(long mobileNo) throws LMSException {

		String mobileRegEx = "(0/91)?[6-9][0-9]{9}";
		boolean result = false;
		if (Pattern.matches(mobileRegEx, String.valueOf(mobileNo))) {
			result = true;
		} else {
			throw new LMSException("Mobile Number  will start with  6 or 9 and It should contains 10 numbers");
		}
		return result;
	}

	public boolean validateEmail(String email) throws LMSException {

		String emailRegEx = "^[a-z0-9](\\.?[a-z0-9]){2,}@g(oogle)?mail\\.(com|org)";
		boolean result = false;
		Pattern pattern = Pattern.compile(emailRegEx);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			result = true;
		} else {
			throw new LMSException("Enter proper email it should contain extension of .com or .org");
		}
		return result;

	}

	public boolean validatePassword(String password) throws LMSException {
		String passwordRegEx = "((?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%]).{6,20})";
		boolean result = false;
		if (Pattern.matches(passwordRegEx, String.valueOf(password))) {
			result = true;
		} else {
			throw new LMSException(
					"Password should contain atleast 6 characters ,one uppercase,one lowercase,one number,one special symbol(@#$%) ");
		}

		return result;
	}

	public boolean validateRole(String role) throws LMSException {
		String roleRegEx = "^(?i)(admin|student)$";
		boolean result = false;
		roleRegEx.equalsIgnoreCase(role);
		if (Pattern.matches(roleRegEx, String.valueOf(role))) {
			result = true;
		} else {
			throw new LMSException("Role must be either admin or student ");
		}
		return result;
	}
}
