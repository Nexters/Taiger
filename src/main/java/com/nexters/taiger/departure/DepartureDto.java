package com.nexters.taiger.departure;

import lombok.Data;

@Data
public class DepartureDto {
	private int id;
	private String name;
	private String description;
	private Double lat;
	private Double lng;
}