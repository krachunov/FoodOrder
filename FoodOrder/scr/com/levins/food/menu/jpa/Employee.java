package com.levins.food.menu.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity(name = "levins_employees")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee implements FoodMenuUnit {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "department")
	private String department;

	@OneToMany(targetEntity = MyOrder.class, mappedBy = "employee", fetch = FetchType.EAGER)
	private List<MyOrder> purchase;

	public Employee() {
	}

	public Employee(String name, String department, List<MyOrder> purchase) {
		this.name = name;
		this.department = department;
		this.purchase = purchase;
	}

	public Employee(String name, String department) {
		this.name = name;
		this.department = department;
		this.purchase = new ArrayList<MyOrder>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<MyOrder> getPurchase() {
		return purchase;
	}

	public void setPurchase(List<MyOrder> purchase) {
		this.purchase = purchase;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((department == null) ? 0 : department.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Employee other = (Employee) obj;
		if (department == null) {
			if (other.department != null)
				return false;
		} else if (!department.equals(other.department))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getName() + ";" + getDepartment();
	}

}
