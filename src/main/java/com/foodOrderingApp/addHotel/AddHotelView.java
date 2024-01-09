package com.foodOrderingApp.addHotel;

import java.util.Scanner;

import com.foodOrderingApp.dto.Hotel;

public class AddHotelView {

	private AddHotelViewModel model;
	private Scanner sc = new Scanner(System.in);
	public AddHotelView() {
		model = new AddHotelViewModel(this);
	}
	
	public void addHotel() {
		System.out.println("Enter the Hotel Name");
		String hotelName = sc.nextLine();
		System.out.println("Enter the Hotel Location");
		String location = sc.nextLine();
		Hotel h = new Hotel();
		h.setHotelName(hotelName);
		h.setLocation(location);
		model.addHotel(h);
	}

	public void added() {
		System.out.println("Hotel Added");
	}
}
