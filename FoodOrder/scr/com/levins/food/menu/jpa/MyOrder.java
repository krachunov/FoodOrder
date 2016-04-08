package com.levins.food.menu.jpa;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "levins_order")
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

	@OneToMany
	@JoinTable(name = "levins_order", joinColumns = { @JoinColumn(name = "id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "current_id", referencedColumnName = "current_id", unique = true) })
	private List<CurrentOrder> listOrder;

	@Column(name = "totalPrice")
	private Double totalAmount;

	public MyOrder() {
	}

	public MyOrder(Employee currentEmployee, Date date,
			List<CurrentOrder> listCurrentOrder, Double totalAmount) {
		this.employee = currentEmployee;
		this.date = date;
		this.listOrder = listCurrentOrder;
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

	public List<CurrentOrder> getListOrder() {
		return listOrder;
	}

	public void setListOrder(List<CurrentOrder> listOrder) {
		this.listOrder = listOrder;
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
