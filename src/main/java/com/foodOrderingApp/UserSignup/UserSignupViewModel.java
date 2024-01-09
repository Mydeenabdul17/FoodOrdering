package com.foodOrderingApp.UserSignup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.foodOrderingApp.dto.User;
import com.foodOrderingApp.repository.Repository;

public class UserSignupViewModel {

	private UserSignupView signupView;

	private Repository repository = Repository.getInstance();
	public UserSignupViewModel(UserSignupView signupView) {
		this.signupView = signupView;
	}
	
	public LocalDate isValidDob(String userEnteredDob) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dob;

		try {
			dob = LocalDate.parse(userEnteredDob, formatter);
			if (dob.isAfter(LocalDate.now())) {
				System.out.println("Future dates are not allowed.");
				return null;
			}

			return dob;
		} catch (DateTimeParseException e) {
			System.out.println("Invalid Date of Birth format. Please enter in the format yyyy-MM-dd.");
			return null;
		}
	}

	public void addUser(User u) {
		repository.addUser(u);
		signupView.added();
	}
}
