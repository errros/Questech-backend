package com.elab.elearning.elearning.authentication;


import com.elab.elearning.elearning.model.UserRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserRegistration user) {
        String plainPassword = generateRandomPassword();


       User userWithCipherPassword = new User(user.getUsername(),passwordEncoder.encode(plainPassword),user.getEmail(),user.getRole());
        userRepository.save(userWithCipherPassword);
       Optional<User> userWithPlainPassowrd = userRepository.findByEmail(user.getEmail());
        userWithPlainPassowrd.get().setPassword(plainPassword);
        return userWithPlainPassowrd.get() ;

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
