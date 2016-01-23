package com.nexters.taiger.user;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.nexters.taiger.meeting.MeetingCommentEntity;
import com.nexters.taiger.meeting.MeetingEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "user", catalog = "taiger")
public class UserEntity implements java.io.Serializable {

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "meeting_user", catalog = "taiger", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "meeting_id", nullable = false, updatable = false) })
	private Set<MeetingEntity> meetings = new HashSet<MeetingEntity>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<MeetingCommentEntity> meetingComments = new HashSet<MeetingCommentEntity>(0);

}
