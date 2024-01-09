package com.foodOrderingApp.addDish;

import java.util.List;

import com.foodOrderingApp.dto.Dish;
import com.foodOrderingApp.dto.Hotel;
import com.foodOrderingApp.dto.Product;
import com.foodOrderingApp.repository.Repository;

public class AddDishViewModel {

	private AddDishView addDishView;
	private Repository repository = Repository.getInstance();
	public AddDishViewModel(AddDishView addDishView) {
		this.addDishView = addDishView;
	}

	public List<Hotel> getAllHotels() {
		return repository.getAllHotels();
	}

	public List<Dish> getAllDishes() {
		return repository.getAllDishes();
	}

	public void addDish(Product p, Dish d) {
		repository.addProduct(p,d);
		addDishView.added();
	}

	public List<Dish> getAllDishes(Hotel h) {
		List<Dish> d = repository.getAllDishes(h);
		return d;
	}

	public void addDish(Product p) {
		repository.addProduct(p);
		addDishView.added();
	}
	
}
