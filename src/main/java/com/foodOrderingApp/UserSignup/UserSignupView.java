package com.foodOrderingApp.UserSignup;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.foodOrderingApp.dto.User;

public class UserSignupView {

	private UserSignupViewModel model;
	private Scanner sc = new Scanner(System.in);
	public UserSignupView() {
		model = new UserSignupViewModel(this);
	}
	
	public void signup() {
		System.out.println("Enter your name");
		String name = sc.nextLine();
		System.out.println("Enter your Email");
		String mail = sc.next();
		LocalDate dob = getUserDob();
		System.out.println("Enter Phone Number");
		long phno = sc.nextLong();
		System.out.println("Enter your Password");
		String password = sc.next();
		User u = new User();
		u.setName(name);
		u.setMail(mail);
		u.setDob(dob);
		u.setPhno(phno);
		u.setPassword(password);
		model.addUser(u);
	}
	LocalDate getUserDob() {
		System.out.print("Enter your date of birth (yyyy-MM-dd): ");
		String userEnteredDob = "";
		LocalDate dob;
		
		do {
			try {
				userEnteredDob = sc.next();
				dob = model.isValidDob(userEnteredDob);
				if (dob == null) {
					showWrongSelectionMessage("Enter Valid date of birth (yyyy-MM-dd): ");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				showWrongSelectionMessage("Enter Valid date of birth (yyyy-MM-dd): ");
				sc.next();
				continue;
			}
		} while (true);

		return dob;
	}

	private void showWrongSelectionMessage(String string) {
		System.out.println(string);
	}

	public void added() {
		System.out.println("User Added");
	}
}
