package com.nexters.taiger.meeting;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nexters.taiger.departure.DepartureEntity;
import com.nexters.taiger.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "meeting", catalog = "taiger")
public class MeetingEntity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "create_user_id", nullable = false)
	private UserEntity user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "departure_id", nullable = false)	
	private DepartureEntity departure;
	
	@Column(name = "title", length = 64)
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", length = 19)	
	private Date startDate;
	
	@Column(name = "gender_type")
	private Integer genderType;
	
	@Column(name = "max_user")
	private Integer maxUser;
	
	@Column(name = "dest_lat", precision = 22, scale = 0)
	private Double destLat;
	
	@Column(name = "dest_lng", precision = 22, scale = 0)
	private Double destLng;
	
	@Column(name = "dest_name", length = 128)
	private String destName;
	
	@Column(name = "cost_type")
	private Integer costType;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	private Date createdAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meeting")
	private Set<MeetingCommentEntity> meetingComments = new HashSet<MeetingCommentEntity>(0);
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "meeting_user", catalog = "taiger", joinColumns = {
			@JoinColumn(name = "meeting_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", nullable = false, updatable = false) })
	private Set<UserEntity> users = new HashSet<UserEntity>(0);
}
