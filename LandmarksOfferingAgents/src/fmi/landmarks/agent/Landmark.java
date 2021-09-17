package fmi.landmarks.agent;

import jade.util.leap.Serializable;

public class Landmark implements Serializable {
	private int id;
	private String name;
	private String imagePath;
	private String description;
	private int categoryId;
	private int typeId;
	private int regionId;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	private double latitude;
	private double longitude;

	public Landmark() {};
	
	public Landmark(int id, String name, int categoryId, int typeId, int regionId, double latitude, double longitude) {
		this.id = id;
		this.name = name;
		this.categoryId = categoryId;
		this.typeId = typeId;
		this.regionId = regionId;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) { this.id = id; }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
}


