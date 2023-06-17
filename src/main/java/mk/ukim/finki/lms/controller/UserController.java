package mk.ukim.finki.lms.controller;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
      log.info("isloggedin");
      return null; // Replace "success-template" with the appropriate template
    } else {
      log.info("is not logged in");

      // User login failed
      return null; // Replace "failure-template" with the appropriate template
    }
  }
}

