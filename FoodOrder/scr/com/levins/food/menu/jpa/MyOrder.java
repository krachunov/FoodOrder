package com.levins.food.menu.jpa;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Type;

@Entity(name = "levins_food_order")
public class MyOrder implements FoodMenuUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "date")
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "employeeID")
	private Employee employee;

	// @OneToMany
	@ManyToMany
	@JoinTable(name = "levins_order", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = { @JoinColumn(name = "food_id"), })
	private List<Food> listFood;

	@Column(name = "totalPrice")
	private Double totalAmount;

	public MyOrder() {
	}

	private Long createRandomgID() {
		long range = 1234567L;
		Random r = new Random();
		long number = (long) (r.nextDouble() * range);
		return number;
	}

	public MyOrder(Employee currentEmployee, Date date, List<Food> listFood,
			Double totalAmount) {
		this.employee = currentEmployee;
		this.date = date;
		this.listFood = listFood;
		this.totalAmount = totalAmount;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Food> getListFood() {
		return listFood;
	}

	public void setListFood(List<Food> listFood) {
		this.listFood = listFood;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		MyOrder other = (MyOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Id " + getId() + " date: " + getDate() + " employee ID "
				+ getEmployee().getId() + " total price " + getTotalAmount();
	}
}
