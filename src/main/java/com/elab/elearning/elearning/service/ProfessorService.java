package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.model.ProfessorRegistration;
import com.elab.elearning.elearning.model.UserRole;
import com.elab.elearning.elearning.repository.ModuleRepository;
import com.elab.elearning.elearning.repository.ProfessorRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class ProfessorService {
    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;


    public void add(Professor p) {
        professorRepository.save(p);
    }


    public Optional<Professor> getProfessor(Long id) {
        return professorRepository.findById(id);
    }



    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();

    }

    public void delete(Long id) {
        professorRepository.deleteById(id);


    }



@Transactional
    public Professor updateInfo(Professor user1) {

        Optional<Professor> userOpt = professorRepository.findById(user1.getId());

        Professor user = userOpt.get();

        user.setUsername(user1.getUsername());
        user.setFamilyname(user1.getFamilyname());

        user.setFirstname(user1.getFirstname());

        user.setBirthDate(user1.getBirthDate());

        user.setPlaceBirth(user1.getPlaceBirth());

        user.setPassword(passwordEncoder.encode(user1.getPassword()));

        user.setPhoneNumber(user1.getPhoneNumber());

        user.setAcademicLevel(user1.getAcademicLevel());

        professorRepository.save(user);

        return professorRepository.getById(user1.getId());
    }




    public Optional<Professor> register(ProfessorRegistration user) {

        String plainpasswrod = generateRandomPassword();
        Professor s = new Professor(user.getFirstname(), user.getFamilyname(), user.getBirthDate(), user.getPlaceBirth()
                , passwordEncoder.encode(plainpasswrod),user.getSex(), user.getEmail(),user.getPhoneNumber(), user.getAcademicLevel());

        professorRepository.save(s);


        mailService.sendEmailtoUser(s.getEmail(), plainpasswrod, UserRole.PROFESSOR.name());
        return professorRepository.findByEmail(s.getEmail());


    }


    public String saveExcelSheet(MultipartFile file) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        // Read student data form excel file sheet.
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                ProfessorRegistration user = new ProfessorRegistration();
                user.setFirstname(getCellValue(row, 0));
                user.setFamilyname(getCellValue(row, 1));
                user.setEmail(getCellValue(row, 2));
                user.setBirthDate(Date.valueOf(getCellValue(row, 3)));
                user.setPlaceBirth(getCellValue(row, 4));
                user.setPhoneNumber(getCellValue(row, 5));
                user.setAcademicLevel(getCellValue(row, 6));
                user.setSex(getCellValue(row,7));

                //  students.add(student);
                register(user);

            }
        }

        return "sucess";
    }

    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);

    }


    public String generateRandomPassword() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 12;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();


        return generatedString;
    }

}

