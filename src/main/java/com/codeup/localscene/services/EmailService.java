package com.codeup.localscene.services;


import com.codeup.localscene.models.User;
import com.codeup.localscene.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("mailService")
public class EmailService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public void registerUser(User user) {
        // encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set instrument and bandMember
        user.setInstrument(user.getInstrument());
        user.setBand_member(user.isBand_member());

        // set an email verification token
        user.setVerificationCode(UUID.randomUUID().toString());

        // save the user
        userRepository.save(user);

        // send the verification email
        sendVerificationEmail(user);
    }


    private void sendVerificationEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Email Verification");
        mailMessage.setText("To verify your email, please click the following link: "
                + "http://localhost:8080/verify?code=" + user.getVerificationCode());

        mailSender.send(mailMessage);
    }

    public boolean verifyUser(String code) {
        User user = userRepository.findByVerificationCode(code);

        if (user != null) {
            // verify the user
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        } else {
            // throw an exception or show an error
            throw new IllegalArgumentException("Invalid verification code");
        }
    }
}
