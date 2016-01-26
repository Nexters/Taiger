package com.nexters.taiger.hello;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * Created by BoBinLee on 2016. 1. 14..
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

@Table(schema = "taiger", name = "hello_info")
public class Hello {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	
	@Column(name = "message")
	String message;
	

	
}

