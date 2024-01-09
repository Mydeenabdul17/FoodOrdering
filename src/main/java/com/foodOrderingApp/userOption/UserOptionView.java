package com.foodOrderingApp.userOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.foodOrderingApp.dto.Bill;
import com.foodOrderingApp.dto.Bill_Dish;
import com.foodOrderingApp.dto.Dish;
import com.foodOrderingApp.dto.Hotel;

public class UserOptionView {

	private UserOptionViewModel model;
	private Scanner sc = new Scanner(System.in);
	public UserOptionView () {
		model = new UserOptionViewModel(this);
	}
	public void options() {
		outer:while (true) {
			System.out.println("1.show Hotels\n2.logout");
			int choice = sc.nextInt();
			if(choice==1) {
				showHotels();
			}else if(choice == 2) {
				break outer;
			}else {
				System.out.println("Input Mismatch");
			}
		}
	}
	private void showHotels() {
		List<Hotel> hotels = model.getHotels();
		while (true) {
			int i = 0;
			for (Hotel hotel : hotels) {
				System.out.println(++i +" . "+ hotel.getHotelName());
			}
			System.out.println(++i+" . back");
			int choice = sc.nextInt();
			if(choice ==0||choice>hotels.size()+1) {
				System.out.println("Input Mismatch");
			}
			else if(choice == hotels.size()+1) {
				break;
			}else {
				viewDishes(hotels.get(choice-1));
			}
		}
	}
	private void viewDishes(Hotel hotel) {
		Bill bill = null;
		List<Dish> dishes = model.getDishes(hotel);
		List<Bill_Dish> bill_Dishs = new ArrayList<Bill_Dish>();
		boolean ordered = false;
		outer:while (true) {
			int i = 1;
			for (Dish dish : dishes) {
				System.out.println(i++ + " . "+dish.getDishName()+"     cost-"+dish.getCost());
			}
			System.out.println(i+" . back");
			int choice = sc.nextInt();
			if(choice == dishes.size()+1) {
				break;
			}else if(choice>dishes.size()+1||choice<1) {
				System.out.println("Input Mismatch");
			}else {
				if(bill == null) {
					bill=new Bill();
					bill.setHotel(hotel);
					bill.setUser(model.getCurrentUser());
					model.addOrder(bill);
				}
//				o.getDishes().add(dishes.get(choice-1));
				Bill_Dish bd = new Bill_Dish();
				bd.setOrder(bill);
				bd.setDish(dishes.get(choice-1));
				bill_Dishs.add(bd);
				bill.setTotal((float)(bill.getTotal()+dishes.get(choice-1).getCost()));
				while (true) {
					System.out.println("1.Add More\n2.Place Order");
					int choice1 = sc.nextInt();
					if(choice1==1) {
						break;
					}
					else if(choice1 ==2) {
						ordered = true;
						model.addOrder(bill_Dishs);
						break outer;
					}else {
						System.out.println("Input Mishmatch");
					}
				}
				
			}
		}
		if(ordered) {
			printBill(bill);
		}
	}
	private void printBill(Bill bill) {
		System.out.println("************************************************************");
		System.out.println("\nOrder ID: " + bill.getId());
        System.out.println("Hotel Name: " + bill.getHotel().getHotelName());
        System.out.println("Location: " + bill.getHotel().getLocation());
        System.out.println("\n************************************************************");
        System.out.println("\nDishes:");
        for (Dish dish : model.getDishes(bill)) {
            System.out.println("  " + dish.getDishName() + ": $" + dish.getCost());
        }
        System.out.println("\n************************************************************");
        System.out.println("\nTotal: $" + bill.getTotal());
        System.out.println("\n************************************************************");
	}
	public void added() {
		System.out.println("Placed");
	}
}
