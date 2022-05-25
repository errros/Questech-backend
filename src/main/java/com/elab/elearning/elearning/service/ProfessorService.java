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


    public Professor update(Optional<String> username, Optional<String> familName, Optional<String> firstName, Optional<Date> birthDate, Optional<String> placeBirth, Optional<String> phoneNumber, Optional<String> academicLevel, Optional<String> newPassword, Long userid) {

        Optional<Professor> userOpt = professorRepository.findById(userid);
        if (userOpt.isPresent()) {
            Professor user = userOpt.get();
            if (username.isPresent()) {
                user.setUsername(username.get());
            }
            if (familName.isPresent()) {
                user.setFamilyname(familName.get());
            }
            if (firstName.isPresent()) {
                user.setFirstname(firstName.get());
            }
            if (birthDate.isPresent()) {
                user.setBirthDate(birthDate.get());
            }
            if (placeBirth.isPresent()) {
                user.setPlaceBirth(placeBirth.get());
            }
            if (newPassword.isPresent()) {
                user.setPassword(passwordEncoder.encode(newPassword.get()));
            }
            if (phoneNumber.isPresent()) {
                user.setPhoneNumber(phoneNumber.get());
            }
            if (academicLevel.isPresent()) {
                user.setAcademicLevel(academicLevel.get());
            }


            professorRepository.save(user);

            return professorRepository.getById(userid);
        } else {
            return new Professor();
        }


    }

    public Optional<Professor> register(ProfessorRegistration user) {

        String plainpasswrod = generateRandomPassword();
        Professor s = new Professor(user.getFirstname(), user.getFamilyname(), user.getBirthDate(), user.getPlaceBirth()
                , passwordEncoder.encode(plainpasswrod), user.getEmail(), user.getPhoneNumber(), user.getAcademicLevel());

        professorRepository.save(s);

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

