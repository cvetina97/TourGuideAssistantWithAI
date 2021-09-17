package fmi.landmarks.agent;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import jade.util.leap.Serializable;

public class TripOffer implements Serializable{
	
	private UUID Id;
	private int userId;
	private double startingPointLatitude;
	private double startingPointLongitude;
	private LocalDate startDate;
	private LocalDate endDate;

	public TripOffer(int userId, double startingPointLatitude, double startingPointLongitude, LocalDate startDate, LocalDate endDate) {
		this.Id = UUID.randomUUID();
		this.userId = userId;
		this.startingPointLatitude = startingPointLatitude;
		this.startingPointLongitude = startingPointLongitude;
		this.startDate = startDate;
		this.endDate = endDate;
		this.landmarks = new ArrayList<Landmark>();
	}

	ArrayList<Landmark> landmarks = new ArrayList<Landmark>();
	
	public UUID getId() {
		return Id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public double getStartingPointLatitude() {
		return startingPointLatitude;
	}

	public void setStartingPointLatitude(double startingPointLatitude) {
		this.startingPointLatitude = startingPointLatitude;
	}

	public double getStartingPointLongitude() {
		return startingPointLongitude;
	}

	public void setStartingPointLongitude(double startingPointLongitude) {
		this.startingPointLongitude = startingPointLongitude;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public ArrayList<Landmark> getLandmarks() {
		return landmarks;
	}

	public void setLandmarks(ArrayList<Landmark> landmarks) {
		this.landmarks = landmarks;
	}

	public void addLandmark(Landmark landmark){
		this.landmarks.add(landmark);
	}

	public void removeLandmark(Landmark landmark){
		this.landmarks.remove(landmark);
	}
}
