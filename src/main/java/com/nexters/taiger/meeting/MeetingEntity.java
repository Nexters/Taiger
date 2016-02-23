package com.nexters.taiger.meeting;
// Generated 2016. 1. 23 ���� 3:44:35 by Hibernate Tools 4.0.0

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
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







import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nexters.taiger.departure.DepartureEntity;
import com.nexters.taiger.user.UserEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "meeting", catalog = "taiger")
public class MeetingEntity implements java.io.Serializable {

	
	
	public MeetingEntity(){
		super();
		
	}
	
	public MeetingEntity(MeetingDto meetingDto){
		super();
		this.id=meetingDto.getId();
		this.user=new UserEntity();
		user.setId(meetingDto.getCreateUserId());
		this.departure=new DepartureEntity();
		departure.setId(meetingDto.getDepartureId());
		this.title=meetingDto.getTitle();
		this.startDate=meetingDto.getStartDate();
		this.genderType=meetingDto.getGenderType();
		this.maxUser=meetingDto.getMaxUser();
		this.depLat=meetingDto.getDepLat();
		this.depLng=meetingDto.getDepLng();
		this.depName=meetingDto.getDepName();
		this.destLat=meetingDto.getDestLat();
		this.destLng=meetingDto.getDestLng();
		this.destName=meetingDto.getDestName();
		this.createdAt=meetingDto.getCreatedAt();
	}
	
	
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
	
	@Column(name = "dep_lat", precision = 22, scale = 0)
	private Double depLat;
	
	@Column(name = "dep_lng", precision = 22, scale = 0)
	private Double depLng;
	
	@Column(name = "dep_name", length = 128)
	private String depName;
	
	@Column(name = "dest_lat", precision = 22, scale = 0)
	private Double destLat;
	
	@Column(name = "dest_lng", precision = 22, scale = 0)
	private Double destLng;
	
	@Column(name = "dest_name", length = 128)
	private String destName;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	private Date createdAt;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "meeting")
	private Set<MeetingCommentEntity> meetingComments = new HashSet<MeetingCommentEntity>(0);
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "meeting_user", catalog = "taiger", joinColumns = {
			@JoinColumn(name = "meeting_id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "user_id", nullable = false) })
	private List<UserEntity> users = new ArrayList<UserEntity>(0);
}
