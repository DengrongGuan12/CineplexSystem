package edu.nju.cineplex.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="member")
public class Member implements Serializable{
	private Integer id;
	private String password;
	private double money;
	private char activated;
	private Integer point;
	private char level;
	private String email;
	private Integer age;
	private char sex;
	private String home;
	private String cardId;
	private Timestamp noMoneyTime;//会费不足的时间
	public Timestamp getNoMoneyTime() {
		return noMoneyTime;
	}
	public void setNoMoneyTime(Timestamp noMoneyTime) {
		this.noMoneyTime = noMoneyTime;
	}
	private char alive;
	public char getAlive() {
		return alive;
	}
	public void setAlive(char alive) {
		this.alive = alive;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, insertable = true, updatable = true)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public char getActivated() {
		return activated;
	}
	public void setActivated(char activated) {
		this.activated = activated;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public char getLevel() {
		return level;
	}
	public void setLevel(char level) {
		this.level = level;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getHome() {
		return home;
	}
	public void setHome(String home) {
		this.home = home;
	}
	
	
	
}
