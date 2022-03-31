package com.elab.elearning.elearning.authentication;


import com.elab.elearning.elearning.model.UserRegistration;
import com.elab.elearning.elearning.service.MailService;
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

import javax.validation.Valid;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MailService mailService;


    
    public User register(UserRegistration user,UserRole role) {

        String plainPassword = generateRandomPassword();


       User userWithCipherPassword = new User(user.getFirstname(),user.getFamilyname(),user.getBirthDate(), user.getPlaceBirth(),
                       passwordEncoder.encode(plainPassword),role,user.getEmail());
        System.out.println(userWithCipherPassword);
        userRepository.save(userWithCipherPassword);
       Optional<User> userWithPlainPassowrd = userRepository.findByEmail(user.getEmail());
        userWithPlainPassowrd.get().setPassword(plainPassword);
        mailService.sendEmailtoUser(userWithPlainPassowrd.get().getEmail(),userWithPlainPassowrd.get().getPassword(),role.name());

        return userWithPlainPassowrd.get() ;

    }

    public String saveExcelSheet(MultipartFile file,UserRole role) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        // Read student data form excel file sheet.
        XSSFSheet worksheet = workbook.getSheetAt(0);

        for (int index = 0; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);

                UserRegistration user = new UserRegistration();
                user.setFirstname(getCellValue(row, 0));
                user.setFamilyname(getCellValue(row, 1));
                user.setEmail(getCellValue(row, 2));
                user.setBirthDate(Date.valueOf(getCellValue(row, 3)));
                user.setPlaceBirth(getCellValue(row,4));

                //  students.add(student);
                register(user,role);

            }
        }

        return "sucess";
    }

    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);

    }





    public String generateRandomPassword(){
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


    public List<User> getAllUsers() {
          return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }
}
