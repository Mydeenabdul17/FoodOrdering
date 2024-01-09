package com.foodOrderingApp.addHotel;

import com.foodOrderingApp.dto.Hotel;
import com.foodOrderingApp.repository.Repository;

public class AddHotelViewModel {

	private Repository repository = Repository.getInstance();
	private AddHotelView addHotelView;
	public AddHotelViewModel(AddHotelView addHotelView) {
		this.addHotelView = addHotelView;
	}
	public void addHotel(Hotel h) {
		repository.addHotel(h);
		addHotelView.added();
	}

}
