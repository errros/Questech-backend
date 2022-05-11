package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.repository.ModuleRepository;
import com.elab.elearning.elearning.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    public Optional<User> add(Professor p) {

        professorRepository.save(p);
        return professorRepository.findByEmail(p.getEmail());
    }


    public Optional<Professor> getProfessor(Long id) {
        return professorRepository.findById(id);
    }

    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();

    }

//    @Transactional
//    public Professor updateInfos(Optional<String> moduleId, Long userid) {
//
//        Professor p = professorRepository.getById(userid);
//        if (moduleId.isPresent()) {
//            Module m = moduleRepository.getById(moduleId.get());
//
//            p.setModule(m);
//            m.setProfessor(p);
//            professorRepository.save(p);
//            moduleRepository.save(m);
//        }
//        return professorRepository.getById(userid);
//    }
}