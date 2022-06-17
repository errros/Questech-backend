package com.elab.elearning.elearning.repository;

import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.model.Promo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends JpaRepository<Module,String> {

    @Query
    List<Module> findByPromo(Promo promo);




}
