package com.nexters.taiger.hello;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by BoBinLee on 2016. 1. 14..
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(schema = "demo", name = "hello_info")
public class Hello {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;
	@Column(name = "message")
	String message;
}
