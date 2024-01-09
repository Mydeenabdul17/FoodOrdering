package com.foodOrderingApp.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.foodOrderingApp.dto.Bill;
import com.foodOrderingApp.dto.Bill_Dish;
import com.foodOrderingApp.dto.Dish;
import com.foodOrderingApp.dto.Hotel;
import com.foodOrderingApp.dto.Product;
import com.foodOrderingApp.dto.User;

public class Repository {

	private String adminMail="admin@gmail.com";
	private String adminPassword = "admin@123";
	private User currentUser;
	private static Repository repository;
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("dev");
	private EntityManager em = emf.createEntityManager();
	private Repository() {
		
	}
	public static Repository getInstance() {
		if(repository==null) {
			repository = new Repository();
		}
		return repository;
	}
	public void addHotel(Hotel h) {
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(h);
		et.commit();
	}
	public String getAdminMail() {
		return adminMail;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public List<Hotel> getAllHotels() {
		Query q = em.createQuery("select h from Hotel h");
		return q.getResultList();
	}
	public List<Dish> getAllDishes() {
		Query q = em.createQuery("select d from Dish d");
		return q.getResultList();
	}
	public void addProduct(Product p, Dish d) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(d);
		em.persist(p);
		et.commit();
	}
	public List<Dish> getAllDishes(Hotel h) {
		List<Dish> dishes = new ArrayList<Dish>();
		Query q = em.createQuery("select p from Product p where hotel_id = :hotel_id");
		q.setParameter("hotel_id", h.getId());
		List<Product> products = q.getResultList();
		for (Product product : products) {
			dishes.add(getDish(product));
		}
		return dishes;
	}
	private Dish getDish(Product product) {
		Query q = em.createQuery("select d from Dish d where id = :id");
		q.setParameter("id", product.getDish().getId());
		return (Dish)q.getResultList().get(0);
	}
	public void addProduct(Product p) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(p);
		et.commit();
	}
	public void addUser(User u) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(u);
		et.commit();
	}
	public User getUser(String mail) {
		Query q = em.createQuery("select u from User u where mail = :mail");
		q.setParameter("mail", mail);
		return (User)q.getResultList().get(0);
	}
	public void addOrder(Bill bill) {
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(bill);
		et.commit();
	}
	public void setCurrentUser(User u) {
		currentUser = u;
	}
	public User getCurrentUser() {
		return currentUser;
	}
	public void addBill_Dish(Bill_Dish bill_Dish) {
		EntityTransaction et = em.getTransaction();
		
		et.begin();
		em.persist(bill_Dish);
		et.commit();
	}
	public List<Dish> getDishes(Bill o) {
		List<Dish> dishes = new ArrayList<Dish>();
		Query q = em.createQuery("select o from Bill_Dish o where bill_id = :bill_id");
		q.setParameter("bill_id", o.getId());
		List<Bill_Dish> bill_Dishes = q.getResultList();
		for (Bill_Dish bill_Dish : bill_Dishes) {
			dishes.add(getDish(bill_Dish));
		}
		return dishes;
	}
	private Dish getDish(Bill_Dish bill_Dish) {
		Query q = em.createQuery("select d from Dish d where id = :id");
		q.setParameter("id", bill_Dish.getDish().getId());
		return (Dish)q.getResultList().get(0);
	}
	
}
