package mk.ukim.finki.lms.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class ReadingListDTO {

  private String cardNumber;
  private String title;
  private Long publisherId;
  private LocalDate publicationDate;
}
