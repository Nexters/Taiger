package com.nexters.taiger.meeting;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.taiger.common.constant.UserSortType;
import com.nexters.taiger.departure.DepartureEntity;

/**
 * Created on Baek on 2016. 1. 23...
 */
@Repository
public interface MeetingRepository extends CrudRepository<MeetingEntity, Integer> {



	  //기본정렬


	  @Query("SELECT new com.nexters.taiger.meeting.MeetingDto(m.id, m.departure.id, m.destName, m.createdAt,(CASE WHEN m.departure.id = ?1 AND m.destName = ?2 THEN 1 ELSE 0 END) as search)  FROM MeetingEntity m WHERE m.departure.id =?1 ORDER BY search DESC, m.createdAt ASC")
	  List<MeetingDto> findAllByDepartureIdAndDestName(int departureId,String destName);


	  //임박순
	  @Query(value="SELECT * from meeting where departure_id=?1 order by created_at asc;",nativeQuery=true)
	  List<MeetingEntity> findAllByDepartureId(int departureId);

	  
	  @Modifying
	  @Transactional
	  @Query(value="delete from meeting_user where user_id=?1 and meeting_id=?2 ",nativeQuery=true)
	  void deleteMeetingUser(int userId,int meetingId);
	
}
