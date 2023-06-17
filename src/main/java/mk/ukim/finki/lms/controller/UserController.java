package mk.ukim.finki.lms.controller;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.repository.UserRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
@Slf4j
public class UserController {
  private final UserRepository userRepository;

  public UserController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @PostMapping("/login")
  public String login(@RequestParam("email") String email, @RequestParam("password") String password) {
    boolean loggedIn = userRepository.login(email, password);

    if (loggedIn) {
      // User login successful
      log.info("is logged in");
      return null; // Replace "success-template" with the appropriate template
    } else {
      log.info("is not logged in");

      // User login failed
      return null; // Replace "failure-template" with the appropriate template
    }
  }

  @PostMapping("/register")
  public String register(@RequestParam("email") String email,
                               @RequestParam("userPassword") String userPassword,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                               @RequestParam("address") String address,
                               @RequestParam("phoneNumber") String phoneNumber,
                               @RequestParam("cardNumber") Integer cardNumber,
                               @RequestParam("membershipId") Integer membershipId) {

    // Invoke the register_patron function
    userRepository.register(email, userPassword, firstName, lastName, dateOfBirth,
            address, phoneNumber, cardNumber, membershipId);

    // Return a response indicating success
    return null;
  }
}

