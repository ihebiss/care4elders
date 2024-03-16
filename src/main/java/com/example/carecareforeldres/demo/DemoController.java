package com.example.carecareforeldres.demo;



import com.example.carecareforeldres.Entity.User;
import com.example.carecareforeldres.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
  @Autowired
  private PasswordEncoder passwordEncoder;


  private static String CodeRecived;
  @Autowired
  private UserRepository UserRepo;


  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint");
  }

  @GetMapping("/profile")
    @PreAuthorize("hasAuthority('AMBILANCIER')")
  public String profile(){
    return "You are on PROFILE page";
  }

  @GetMapping("/Dashboard")
  @PreAuthorize("hasAuthority('ADMIN')")
  public String Dashboard(){
    return "You are on Dashboard page";
  }
  @GetMapping("/SendMailForgetPswd/{mail}")
  //@PreAuthorize("hasAuthority('ADMIN')")
  public String SendMail(@PathVariable String mail){
    this.CodeRecived=getRandomNumberString();
  //  System.out.println("Email lbch nab3ethlou :"+ UserRepo.findByEmail(mail));
//   if (UserRepo.findByEmail(mail) != null){

     return "Email receved";
   //}
  // else
  //   return "-______-  ' Email of this account Not Existe '   -_______-";
  }

  @GetMapping("/Verifier/{mail}/{code}/{newPsw}")
  //@PreAuthorize("hasAuthority('ADMIN')")
  public String SendMail(@PathVariable String mail,@PathVariable String code,@PathVariable String newPsw){
    System.out.println("Code Envoyer est ="+this.CodeRecived);
    System.out.println("Code saiser est ="+ code);

    if (this.CodeRecived.compareTo(code) == 0 ){
    User u= UserRepo.findByEmail(mail).get();
        System.out.println("UserName ="+ u.getLastname()  );

      u.setPasswd( passwordEncoder.encode(newPsw));

      UserRepo.save(u);
      this.CodeRecived="No Code";
      return "Code Correct Password has been Update Succeful";
    }
    else
      return "-______-  ' Failed to Update Psw of this account '   -_______-";
  }

  public static String getRandomNumberString() {
    // It will generate 6 digit random Number.
    // from 0 to 999999
    Random rnd = new Random();
    int number = rnd.nextInt(999999);

    // this will convert any number sequence into 6 character.
    return String.format("%06d", number);
  }


}
