package com.nexters.taiger.meeting;



import java.util.Date;

import lombok.Data;

@Data
public class MeetingSearch {

	private int id;
	private int departureId;
	private String destName;
	private Date createdAt;
	private int search;
	public MeetingSearch(){
		
	}
	public MeetingSearch(int id,int departureId,String destName,Date createdAt,int search){
		this.id=id;
		this.departureId=departureId;
		this.destName=destName;
		this.createdAt=createdAt;
		this.search=search;
	}
	
}
