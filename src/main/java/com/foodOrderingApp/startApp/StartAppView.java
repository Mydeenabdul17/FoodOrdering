package com.foodOrderingApp.startApp;

import java.util.Scanner;

import com.foodOrderingApp.UserSignup.UserSignupView;
import com.foodOrderingApp.userLogin.UserLoginView;

public class StartAppView {

	Scanner sc = new Scanner(System.in);
	public void start() {
		System.out.println("Welcome to Zomato");
		outer:while(true) {
			System.out.println("1.Admin\n2.User\n3.exit");
			switch (sc.nextInt()) {
			case 1:{
				new UserLoginView().userLogin(true);
				break;
			}
				
			case 2:{
				loop:while(true) {
					System.out.println("1.Signup\n2.Login\n3.Exit");
					switch (sc.nextInt()) {
					case 1:{
						new UserSignupView().signup();
						break;
					}
					case 2:{
						new UserLoginView().userLogin(false);
						break;
					}	
					case 3:{
						break loop;
					}	

					default:{
						System.out.println("Input Mismatch");
						break;
					}
						
					}
				}
				break;
			}	
			case 3:{
				break outer;
			}
			default:
				break;
			}
		}
	}
}
