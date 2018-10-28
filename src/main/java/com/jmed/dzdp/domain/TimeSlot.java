package com.jmed.dzdp.domain;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
public class TimeSlot implements Serializable{
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	private char[] timeslot = new char[48];
	public TimeSlot(LocalDate date, char[] timeslot) {
		this.date = date;
		this.timeslot = timeslot;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public String getTimeslot() {
		return String.valueOf(timeslot);
	}
	public void setTimeslot(char[] timeslot) {
		this.timeslot = timeslot;
	}

}
