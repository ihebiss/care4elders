package com.example.carecareforeldres.demo;

import com.example.carecareforeldres.Entity.User;
import com.example.carecareforeldres.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  private final UserRepository userRepository;
  private final JavaMailSender emailSender;
  private final PasswordEncoder passwordEncoder;

  private String resetCode;

  @Autowired
  public DemoController(UserRepository userRepository, JavaMailSender emailSender, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.emailSender = emailSender;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping ("/forgotPassword")
  public ResponseEntity<String> forgotPassword(@RequestParam String email) {
    User user = userRepository.findByEmail(email).orElse(null);
    if (user != null) {
      // Generate random code
      resetCode = getRandomNumberString();

      // Send email with code
      try {
        sendEmail(email, resetCode);
        return ResponseEntity.ok("Reset code sent successfully");
      } catch (MailException e) {
        return ResponseEntity.badRequest().body("Failed to send reset code");
      }
    } else {
      return ResponseEntity.badRequest().body("User with provided email not found");
    }
  }

  @PostMapping("/resetPassword")
  public ResponseEntity<String> resetPassword(@RequestParam String email,
                                              @RequestParam String code,
                                              @RequestParam String newPassword) {
    // Verify code
    if (code.equals(resetCode)) {
      System.out.println("ihebiss"+resetCode);
      // Update password
      User user = userRepository.findByEmail(email).orElse(null);
      if (user != null) {
        user.setPasswd(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("Password reset successfully");
      } else {
        return ResponseEntity.badRequest().body("User with provided email not found");
      }
    } else {
      return ResponseEntity.badRequest().body("Invalid reset code");
    }
  }

  private void sendEmail(String email, String code) throws MailException {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setTo(email);
    message.setSubject("Password Reset Code");
    message.setText("Your password reset code is: " + code);
    emailSender.send(message);
  }

  private String getRandomNumberString() {
    Random rnd = new Random();
    int number = rnd.nextInt(999999);
    return String.format("%06d", number);
  }
}
