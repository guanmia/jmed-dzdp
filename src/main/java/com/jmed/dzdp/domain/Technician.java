package com.jmed.dzdp.domain;

import java.util.List;

public class Technician {

	 private String technicianId;
     private String name;
     private int cityId;
     private String sex;
     private int age;
     private String photoURL;
     private int workingAge;
     private int orderAmount;
     private int level;
     private float longitude;
     private float latitude;
     private String phone;
     private String description;
     private List tags;
	public String getTechnicianId() {
		return technicianId;
	}
	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}
	public int getWorkingAge() {
		return workingAge;
	}
	public void setWorkingAge(int workingAge) {
		this.workingAge = workingAge;
	}
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public float getLongitude() {
		return longitude;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List getTags() {
		return tags;
	}
	public void setTags(List tags) {
		this.tags = tags;
	}
	public Technician(String technicianId, String name, int cityId, String sex, int age, String photoURL,
			int workingAge, int orderAmount, int level, float longitude, float latitude, String phone,
			String description, List tags) {
		this.technicianId = technicianId;
		this.name = name;
		this.cityId = cityId;
		this.sex = sex;
		this.age = age;
		this.photoURL = photoURL;
		this.workingAge = workingAge;
		this.orderAmount = orderAmount;
		this.level = level;
		this.longitude = longitude;
		this.latitude = latitude;
		this.phone = phone;
		this.description = description;
		this.tags = tags;
	}
}
