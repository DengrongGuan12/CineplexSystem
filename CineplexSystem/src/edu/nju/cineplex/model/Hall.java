package edu.nju.cineplex.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="hall")
public class Hall implements Serializable{
	private Integer id;
	private Integer seatNum;
	private String seatPlan;
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSeatNum() {
		return seatNum;
	}
	public void setSeatNum(Integer seatNum) {
		this.seatNum = seatNum;
	}
	public String getSeatPlan() {
		return seatPlan;
	}
	public void setSeatPlan(String seatPlan) {
		this.seatPlan = seatPlan;
	}
	

}
