package com.codeup.localscene.services;


import com.codeup.localscene.models.Users;
import com.codeup.localscene.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("emailService")
public class EmailService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    public void registerUser(@Valid Users user) {
        // encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set an email verification token
        user.setVerificationCode(UUID.randomUUID().toString());

        // save the user
        userRepository.save(user);

        // send the verification email
        sendVerificationEmail(user);
    }


    private void sendVerificationEmail(Users user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Email Verification");
        mailMessage.setText("To verify your email, please click the following link: "
                + "http://localhost:8080/verify?code=" + user.getVerificationCode());

        mailSender.send(mailMessage);
    }

    public boolean verifyUser(String code) {
        Users user = userRepository.findByVerificationCode(code);

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
    public void sendPasswordResetEmail(String email) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            // Generate a password reset token and save it for the user
            String resetToken = UUID.randomUUID().toString();
            user.setResetPasswordToken(resetToken);
            userRepository.save(user);

            // Send the password reset email
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Password Reset");
            mailMessage.setText("To reset your password, please click the following link: "
                    + "http://localhost:8080/reset-password?token=" + resetToken);

            mailSender.send(mailMessage);
        } else {
            // throw an exception or show an error
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
