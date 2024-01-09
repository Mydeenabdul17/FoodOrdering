package com.foodOrderingApp.addDish;

import java.util.List;
import java.util.Scanner;

import com.foodOrderingApp.dto.Dish;
import com.foodOrderingApp.dto.Hotel;
import com.foodOrderingApp.dto.Product;

public class AddDishView {

	private AddDishViewModel model;
	private Scanner sc = new Scanner(System.in);
	public AddDishView() {
		model = new AddDishViewModel(this);
	}
	public void addDish() {
		while (true) {
			System.out.println("1.View Hotels\n2.back");
			int choice = sc.nextInt();
			if(choice == 1) {
				Hotel h = viewHotels();
				if(h!=null) {
					loop:while (true) {
						System.out.println("1.Add New Dish\n2.Add Existing Dish\n3.View Existing Dishes in Hotel\n4.Back");
						switch(sc.nextInt()) {
						case 1:{
							sc.nextLine();
							System.out.println("Enter the Dish Name");
							String dishName = sc.nextLine();
							System.out.println("Enter the Cuisine");
							String cuisine = sc.nextLine();
							System.out.println("Enter the Cost Of Dish");
							float cost = sc.nextFloat();
							Dish d = new Dish();
							d.setDishName(dishName);
							d.setCuisine(cuisine);
							d.setCost(cost);
							Product p = new Product();
							p.setHotel(h);
							p.setDish(d);
							model.addDish(p,d);
							break;
						}
						case 2:{
							Dish d = viewDishes();
							if(d!=null) {
								Product p = new Product();
								p.setDish(d);
								p.setHotel(h);
								model.addDish(p);
							}
							break;
						}
						case 3:{
							viewDishesInHotel(h);
							break;
						}
						case 4:{
							break loop;
						}
						}
					}
				}
			}else if(choice == 2) {
				break;
			}else {
				System.out.println("Input Mismatch");
			}
		}
	}
	private void viewDishesInHotel(Hotel h) {
		List<Dish> dishes = model.getAllDishes(h);
		if(dishes!=null) {
			System.out.println("-----------------------------------------");
			for (Dish dish : dishes) {
				System.out.println(dish.getId()+"    "+dish.getDishName());
			}
			System.out.println("-----------------------------------------");
		}else {
			System.out.println("No Dish to List");
		}
	}
	private Dish viewDishes() {
		List<Dish> dishes = model.getAllDishes();
		if(dishes!=null) {
			System.out.println("-----------------------------------------");
			int i=1;
			for (Dish dish : dishes) {
				System.out.println(i++ +"   "+dish.getDishName());
			}
			System.out.println("-----------------------------------------");
			System.out.println("Choose the hotel to add Dish");
			int choice = sc.nextInt();
			return dishes.get(choice-1);
		}else {
			System.out.println("No Dishes To List");
			return null;
		}
	}
	private Hotel viewHotels() {
		List<Hotel> hotels = model.getAllHotels();
		if(hotels!=null) {
			System.out.println("-----------------------------------------");
			int i=1;
			for (Hotel hotel : hotels) {
				System.out.println(i++ +"   "+hotel.getHotelName());
			}
			System.out.println("-----------------------------------------");
			System.out.println("Choose the hotel to add Dish");
			int choice = sc.nextInt();
			return hotels.get(choice-1);
		}else {
			System.out.println("No Hotels To List");
			return null;
		}
	}
	public void added() {
		System.out.println("Product Added");
	}
}
