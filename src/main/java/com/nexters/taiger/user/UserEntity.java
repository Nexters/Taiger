package com.nexters.taiger.user;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0


import com.nexters.taiger.departure.DepartureEntity;
import com.nexters.taiger.meeting.MeetingCommentEntity;
import com.nexters.taiger.meeting.MeetingEntity;

import lombok.*;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", catalog = "taiger")
public class UserEntity implements java.io.Serializable {

	public UserEntity(UserCondition condition) {
		this.setKakaoId(condition.getKakaoId());
		this.setName(condition.getName());
		this.setGender(condition.getGender());
		this.setAge(condition.getAge());
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;

	@Column(name = "kakao_id", length = 512)
	private String kakaoId;
	@Column(name = "kakao_token")
	private String kakaoToken;
	@Column(name = "name", length = 45)
	private String name;
	@Column(name = "gender")
	private Integer gender;
	@Column(name = "age")
	private Integer age;
	@Column(name = "meeting_count")
	private Integer meetingCount;
	@Column(name = "reliability")
	private Integer reliability;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "primary_departure_id")
	private DepartureEntity primaryDeparture;


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "meeting_user", catalog = "taiger", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "meeting_id", nullable = false) })
	private Set<MeetingEntity> meetings = new HashSet<MeetingEntity>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<MeetingCommentEntity> meetingComments = new HashSet<MeetingCommentEntity>(0);

}
