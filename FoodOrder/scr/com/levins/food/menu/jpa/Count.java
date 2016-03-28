package com.levins.food.menu.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "levins_food_count")
public class Count {
	@Id
	private long id;

	@Column(name="food_count")
	private Integer count;

	public Count() {
	}

	public Count(Integer count) {
		this.count = count;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
