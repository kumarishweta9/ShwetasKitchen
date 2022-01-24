package com.kitchen.web;

public class Customer {
	private int id;
	private String firstName;
	private String lastName;
	private String address;
	private String state;
	private String postalCode;
	private String comment;
	
	
	public Customer(int id, String firstName, String lastName, String address, String state, String postalCode, String comment) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.state = state;
		this.postalCode = postalCode;
		this.comment = comment;
	}
	public Customer(String firstName, String lastName, String address, String state, String postalCode, String comment) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.state = state;
		this.postalCode = postalCode;
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
		
	}
	@Override
	public String toString() {
		return "Food [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ ", state=" + state + ", postalCode=" + postalCode + ", comment=" + comment + "]";
	}
	
}
