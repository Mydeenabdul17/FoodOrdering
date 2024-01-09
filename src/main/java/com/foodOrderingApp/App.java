package com.foodOrderingApp;

import com.foodOrderingApp.startApp.StartAppView;

public class App {

	public static void main(String[] args) {
		new App().init();
	}

	private void init() {
		new StartAppView().start();
	}
}
