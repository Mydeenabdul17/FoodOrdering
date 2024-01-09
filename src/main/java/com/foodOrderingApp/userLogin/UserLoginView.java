package com.foodOrderingApp.userLogin;

import java.util.Scanner;

import com.foodOrderingApp.addDish.AddDishView;
import com.foodOrderingApp.addHotel.AddHotelView;
import com.foodOrderingApp.userOption.UserOptionView;

public class UserLoginView {

	private UserLoginViewModel loginViewModel;
	private Scanner sc = new Scanner(System.in);

	public UserLoginView() {
		loginViewModel = new UserLoginViewModel(this);
	}

	public void userLogin(boolean isAdmin) {
		System.out.println("Enter the mail");
		String mail = sc.next();
		System.out.println("Enter the password");
		String password = sc.next();
		loginViewModel.isValidLogin(isAdmin, mail, password);
	}

	public void loginFailed(boolean isAdmin) {
		System.out.println("Invalid Input");
		userLogin(isAdmin);
	}

	public void showOptions(boolean isAdmin) {
		if (isAdmin) {
			outer: while (true) {
				System.out.println("1.Add Hotel\n2.Add Dish\n3.Logout");
				switch (sc.nextInt()) {
				case 1: {
					new AddHotelView().addHotel();
					break;
				}
				case 2: {
					new AddDishView().addDish();
					break;
				}
				case 3: {
					break outer;
				}
				}

			}
			return;
		} else {
			new UserOptionView().options();
		}
	}
}
