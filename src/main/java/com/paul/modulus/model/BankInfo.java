package com.paul.modulus.model;

public class BankInfo {

	private int UKSortCode;
	private String Name;
	private String streeAddress;
	private String city;
	private String country;
		
	public int getUKSortCode() {
		return UKSortCode;
	}
	public void setUKSortCode(int uKSortCode) {
		UKSortCode = uKSortCode;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getStreeAddress() {
		return streeAddress;
	}
	public void setStreeAddress(String streeAddress) {
		this.streeAddress = streeAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("UkCode:"+getUKSortCode());
		sb.append(", name:"+getName());
		sb.append(", streetAddr:"+getStreeAddress());
		sb.append(", city:"+getCity());
		sb.append(", country:"+getCountry());
		return sb.toString();
	}
}
