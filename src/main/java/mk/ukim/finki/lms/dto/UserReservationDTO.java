package mk.ukim.finki.lms.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class UserReservationDTO {

  private String firstName;
  private String lastName;
  private String cardNumber;
  private String bookTitle;
  private String bookAuthor;
  private String bookCategory;
  private LocalDate reservationDate;
}
