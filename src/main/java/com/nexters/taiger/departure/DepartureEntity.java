package com.nexters.taiger.departure;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nexters.taiger.meeting.MeetingEntity;
import com.nexters.taiger.meeting.MeetingCommentEntity;
import com.nexters.taiger.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "departure", catalog = "taiger")
public class DepartureEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@Column(name = "name", nullable = false, length = 45)
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "lat", precision = 22, scale = 0)
	private Double lat;
	
	@Column(name = "lng", precision = 22, scale = 0)
	private Double lng;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "departure")
	private Set<MeetingEntity> meetings = new HashSet<MeetingEntity>(0);

}
