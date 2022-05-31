package com.elab.elearning.elearning.repository;


import com.elab.elearning.elearning.entity.Group;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.model.GroupId;
import com.elab.elearning.elearning.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, GroupId> {


    @Query
    List<Group> findByGroupIdPromo(Promo promo);


}
