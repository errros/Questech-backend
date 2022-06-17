package com.elab.elearning.elearning.repository;

import com.elab.elearning.elearning.entity.Session;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.model.SessionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.Set;

@Repository
public interface SessionRepository extends JpaRepository<Session, SessionId> {

    @Transactional
    @Modifying
    @Query(value = "DELETE  FROM Session s WHERE s.sessionId.day = ?1 AND s.sessionId.groupId.promo = ?2 AND s.sessionId.groupId.id = ?3")
    void deleteGroupDaySessions(DayOfWeek day , Promo promo , Long id);

    @Query(value = "SELECT s FROM Session s WHERE  s.sessionId.day = ?1 AND s.sessionId.groupId.promo = ?2 AND s.sessionId.groupId.id = ?3")
    Set<Session> findByDayAndPromoAndId(DayOfWeek day,Promo promo , Long id);

    @Query(value = "SELECT s FROM Session s WHERE s.professor.id = ?1 AND s.sessionId.day = ?2")
    Set<Session> findByProfessorAndDay(Long id,DayOfWeek day);

    @Query(value = "SELECT s FROM Session s WHERE s.sessionId.groupId.promo = ?1 AND s.sessionId.groupId.id = ?2 AND s.sessionId.day = ?3 ")
    Set<Session> findByGroupAndDay(Promo promo,Long id , DayOfWeek day);

    @Query(value = "SELECT s FROM Session s WHERE s.sessionId.groupId.promo = ?1 AND s.sessionId.groupId.id = ?2")
    Set<Session> findByGroup(Promo promo, Long id);


}
