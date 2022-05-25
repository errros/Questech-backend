package com.elab.elearning.elearning.service;


import com.elab.elearning.elearning.entity.Professor;
import com.elab.elearning.elearning.entity.Student;
import com.elab.elearning.elearning.entity.User;
import com.elab.elearning.elearning.model.StudentRegistration;
import com.elab.elearning.elearning.repository.StudentRepository;
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
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;



    public Optional<Student> register(StudentRegistration user) {

        String plainpasswrod = generateRandomPassword();
        Student s = new Student(user.getFirstname(),user.getFamilyname(),user.getBirthDate(),user.getPlaceBirth()
        ,passwordEncoder.encode(plainpasswrod),user.getEmail());

        studentRepository.save(s);

        return studentRepository.findByEmail(s.getEmail());

    }


    public String saveExcelSheet(MultipartFile file) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        // Read student data form excel file sheet.
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                 StudentRegistration user = new StudentRegistration();
                user.setFirstname(getCellValue(row, 0));
                user.setFamilyname(getCellValue(row, 1));
                user.setEmail(getCellValue(row, 2));
                user.setBirthDate(Date.valueOf(getCellValue(row, 3)));
                user.setPlaceBirth(getCellValue(row, 4));

                //  students.add(student);
                register(user);

            }
        }

        return "sucess";

    }

    public void delete (Long id){

        studentRepository.deleteById(id);
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


    public List<Student> getAllStudents() {
     return studentRepository.findAll();

    }

    public Optional<Student> getStudent(Long id) {

           Optional<Student> student = Optional.of(studentRepository.getById(id));

           return student;
    }

    public Student updateInfos(Optional<String> username, Optional<String> familName, Optional<String> firstName, Optional<Date> birthDate, Optional<String> placeBirth, Optional<String> newPassword, Long userid) {

        Optional<Student> userOpt = studentRepository.findById(userid);

        if(userOpt.isPresent()) {
            Student user = userOpt.get();
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


            studentRepository.save(user);

            return studentRepository.getById(userid);

        } else
            return new Student();



    }
}
