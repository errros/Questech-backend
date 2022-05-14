package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Module;
import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.model.Promo;
import com.elab.elearning.elearning.repository.ModuleRepository;
import com.elab.elearning.elearning.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {

    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private ProfessorRepository professorRepository;
    public List<Module> getAllModules() {
        return moduleRepository.findAll();

    }



    @Transactional
    public Module add(Module module, Optional<Long> professorId) {
        if(professorId.isPresent()) {
           Professor p = professorRepository.getById(professorId.get());
           Module m = moduleRepository.save(module);
           m.setProfessor(p);
           p.setModule(m);
           professorRepository.save(p);
        return module;
        }
        moduleRepository.save(module);


    return module;
    }

    public void delete(String code) {
        moduleRepository.deleteById(code);
    }

    public Module update(String code, Optional<String> detailedName, Optional<Promo> promo, Optional<Integer> semester,
                         Optional<Integer> coefficient, Optional<Integer> credit, @Valid Optional<Long> professorId) {


      Module m = moduleRepository.getById(code);

        if(detailedName.isPresent()){
            m.setDetailedName(detailedName.get());
        }

        if(promo.isPresent()){
            m.setPromo(promo.get());
        }
        if(semester.isPresent()){
            m.setSemester(semester.get());
        }

        if(coefficient.isPresent()){
            m.setCoefficient(coefficient.get());
        }

        if(credit.isPresent()){
            m.setCredit(credit.get());
        }


        if(professorId.isPresent()){
            Professor p = professorRepository.getById(professorId.get());
            m.setProfessor(p);
            p.setModule(m);
            professorRepository.save(p);
        }

        moduleRepository.save(m);
        return moduleRepository.getById(code);

    }

    public Optional<Module> getModule(String code) {
        Optional<Module> m = Optional.of(moduleRepository.getById(code));
         return m;


    }
}
