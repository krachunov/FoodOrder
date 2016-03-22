package com.levins.food.menu.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "levins_food")
public class Food implements FoodMenuUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "date")
	private Date date;

	@Column(name = "value")
	private String foodName;

	@Column(name = "singlePrice")
	private Double price;

	 @Column(name = "count")
	 private Integer quantity;
	
	 @ManyToOne(fetch = FetchType.EAGER)
	 @JoinColumn(name = "food_id")
	 private MyOrder order;
	
	 @Column(name = "totalPrice")
	 private Double totalPrice;

	public Food() {
	}

	public Food(Date date, String foodName, Double price, Integer quantity) {
		this.date = date;
		this.foodName = foodName;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = price * quantity;
	}

	public Food(Date date, String foodName, Double price) {
		super();
		this.date = date;
		this.foodName = foodName;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getValue() {
		return foodName;
	}

	public void setValue(String value) {
		this.foodName = value;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	 public Integer getQuantity() {
	 return quantity;
	 }
	
	 public void setQuantity(Integer quantity) {
	 this.quantity = quantity;
	 }

	@Override
	public String toString() {
		return getId() + ";" + getDate() + ";" + getValue() + ";" + getPrice();
	}
}
