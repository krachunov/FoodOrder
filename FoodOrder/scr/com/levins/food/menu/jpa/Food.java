package com.levins.food.menu.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "levins_food")
// @SecondaryTable(name = "levins_order", pkJoinColumns={
// @PrimaryKeyJoinColumn(name="FoodID", referencedColumnName="foodID")})
public class Food implements FoodMenuUnit, Serializable {

	private static final long serialVersionUID = 7347912557362269452L;
	public static final int INDEX_ID = 0;
	public static final int INDEX_DATE = 1;
	public static final int INDEX_FOOD_NAME = 2;
	public static final int INDEX_PIRCE = 3;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "food_id")
	private Long foodID;

	@Column(name = "date")
	private Date date;

	@Column(name = "foodName")
	private String foodName;

	@Column(name = "price")
	private Double price;

	// @ManyToOne
	// private MyOrder order;

	public Food() {
	}

	public Food(Long id, Date date, String foodName, Double price) {
		this.foodID = id;
		this.date = date;
		this.foodName = foodName;
		this.price = price;
	}

	public Food(Date date, String foodName, Double price) {
		super();
		this.date = date;
		this.foodName = foodName;
		this.price = price;
	}

	public Long getId() {
		return foodID;
	}

	public void setId(Long id) {
		this.foodID = id;
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

	//
	// public MyOrder getOrder() {
	// return order;
	// }
	//
	// public void setOrder(MyOrder order) {
	// this.order = order;
	// }

	@Override
	public String toString() {
		return getId() + ";" + getDate() + ";" + getValue() + ";" + getPrice();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foodID == null) ? 0 : foodID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Food other = (Food) obj;
		if (foodID == null) {
			if (other.foodID != null)
				return false;
		} else if (!foodID.equals(other.foodID))
			return false;
		return true;
	}

}
