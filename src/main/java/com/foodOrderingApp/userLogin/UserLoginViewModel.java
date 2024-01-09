package com.foodOrderingApp.userLogin;

import com.foodOrderingApp.dto.User;
import com.foodOrderingApp.repository.Repository;

public class UserLoginViewModel {

	private UserLoginView loginView;
	private Repository repository = Repository.getInstance();
	public UserLoginViewModel(UserLoginView loginView) {
		this.loginView = loginView;
	}

	public void isValidLogin(boolean isAdmin, String mail, String password) {
		if(isAdmin) {
			if(mail.equals(repository.getAdminMail())&&password.equals(repository.getAdminPassword())) {
				loginView.showOptions(isAdmin);
			}else {
				loginView.loginFailed(isAdmin);
			}
		}else {
			User u = repository.getUser(mail);
			if(u!=null) {
				if(password.equals(u.getPassword())) {
					repository.setCurrentUser(u);
					loginView.showOptions(isAdmin);
				}else {
					loginView.loginFailed(isAdmin);
				}
			}else {
				loginView.loginFailed(isAdmin);
			}
		}
	}
	
	
}
