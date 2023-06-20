package mk.ukim.finki.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.dto.*;
import mk.ukim.finki.lms.enums.MembershipPackage;
import mk.ukim.finki.lms.repository.UserRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
  private final UserRepository userRepository;

  @GetMapping("/login")
  public String getLoginPage() {
    return "user/login";
  }

  @PostMapping("/login")
  public String login(@RequestParam("email") String email, @RequestParam("password") String password) {

    return userRepository.login(email, password) ? "user/login-success" : "user/login-fail";
  }

  @GetMapping("/register")
  public String getRegisterPage(Model model) {
    model.addAttribute("memberships", MembershipPackage.values());

    return "user/register";
  }

  @PostMapping("/register")
  public String register(@RequestParam("email") String email,
                         @RequestParam("userPassword") String userPassword,
                         @RequestParam("firstName") String firstName,
                         @RequestParam("lastName") String lastName,
                         @RequestParam("dateOfBirth") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfBirth,
                         @RequestParam("address") String address,
                         @RequestParam("phoneNumber") String phoneNumber,
                         @RequestParam("membership") String membership) {

    // Invoke the register_patron function
    userRepository.register(email, userPassword, firstName, lastName, dateOfBirth,
        address, phoneNumber, membership);

    // Return a response indicating success
    return "redirect:/login";
  }

  @GetMapping("/userReservations")
  public String getActiveReservations(Model model,
                                      @RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName,
                                      @RequestParam("cardNumber") String cardNumber) {
    List<UserReservationDTO> userActiveReservations = userRepository.userActiveReservations(firstName,
        lastName,
        cardNumber);

    // test this
    model.addAttribute("userActiveReservations", userActiveReservations);
    return "user/active-reservations";
  }

  @GetMapping("/userInfo")
  public String getUserInfo(Model model,
                            @RequestParam("firstName") String firstName,
                            @RequestParam("lastName") String lastName) {
    List<UserInfoDTO> userInfo = userRepository.getUserInfo(firstName, lastName);

    // test this
    model.addAttribute("userInfo", userInfo);
    return "user/user-info";
  }

  @GetMapping("/readingList")
  public String getReadingList(Model model,
                               @RequestParam("cardNumber") String cardNumber) {
    List<ReadingListDTO> userReadingList = userRepository.getUserReadingList(cardNumber);

    model.addAttribute("userReadingList", userReadingList);
    return "user/reading-list";
  }

  @GetMapping("/users")
  public String getUsers(Model model,
                         @RequestParam(value = "firstName", required = false) String firstName,
                         @RequestParam(value = "lastName", required = false) String lastName) {
    List<UserDTO> users = userRepository.getUsers(firstName, lastName);

    // test this
    model.addAttribute("users", users);
    return "user/users";
  }

  @GetMapping("/eventAttendance")
  public String eventAttendance(Model model,
                                @RequestParam(value = "eventName", required = false) String eventName) {
    List<EventDTO> eventAttendances = userRepository.getEventAttendance(eventName);

    // test this
    model.addAttribute("eventAttendances", eventAttendances);
    return "user/event-attendance";
  }

}

