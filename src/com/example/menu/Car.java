package com.example.menu;

public class Car {
	
	private String name;
	private String brand;
	private int	hp;
	private int	id;
	
	public Car(String n, String b, int hp, int id)
	{
		this.name=n;
		this.brand=b;
		this.hp=hp;
		this.id=id;
		
		
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


}
