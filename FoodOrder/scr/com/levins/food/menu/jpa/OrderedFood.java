package com.levins.food.menu.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//@Entity(name = "levins_ordered_food")
public class OrderedFood implements FoodMenuUnit {
	public static final int INDEX_ID = 0;
	public static final int INDEX_DATE = 1;
	public static final int INDEX_FOOD_NAME = 2;
	public static final int INDEX_PIRCE = 3;
	public static final int INDEX_QUANTITY = 4;

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

	@ManyToMany(mappedBy = "listFood")
	private List<MyOrder> order;

	public OrderedFood() {
	}

	public OrderedFood(Date date, String foodName, Double price, Integer quantity) {
		this.date = date;
		this.foodName = foodName;
		this.price = price;
		this.quantity = quantity;
	}

	public OrderedFood(Long id, Date date, String foodName, Double price,
			Integer quantity) {
		this.id = id;
		this.date = date;
		this.foodName = foodName;
		this.price = price;
		this.quantity = quantity;
	}

	public OrderedFood(Date date, String foodName, Double price) {
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
		return getId() + ";" + getDate() + ";" + getValue() + ";" + getPrice()
				+ ";" + getQuantity();
	}

}
