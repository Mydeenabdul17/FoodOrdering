package com.foodOrderingApp.userOption;

import java.util.List;

import com.foodOrderingApp.dto.Bill;
import com.foodOrderingApp.dto.Bill_Dish;
import com.foodOrderingApp.dto.Dish;
import com.foodOrderingApp.dto.Hotel;
import com.foodOrderingApp.dto.User;
import com.foodOrderingApp.repository.Repository;

public class UserOptionViewModel {
	
	private UserOptionView userOptionView;

	private Repository repository = Repository.getInstance();
	public UserOptionViewModel(UserOptionView userOptionView) {
		this.userOptionView = userOptionView;
	}

	public List<Hotel> getHotels() {
		return repository.getAllHotels();
	}

	public List<Dish> getDishes(Hotel hotel) {
		return repository.getAllDishes(hotel);
	}

	public void addOrder(Bill bill) {
		repository.addOrder(bill);
	}

	public User getCurrentUser() {
		return repository.getCurrentUser();
	}

	public void addOrder(List<Bill_Dish> bill_Dishes) {
		for (Bill_Dish bill_Dish : bill_Dishes) {
			repository.addBill_Dish(bill_Dish);
		}
		userOptionView.added();
	}

	public List<Dish> getDishes(Bill bill) {
		return repository.getDishes(bill);
	}

}
