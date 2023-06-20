package mk.ukim.finki.lms.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ReadingListDTO {

  private String cardNumber;
  private String title;
  private Long publisherId;
  private LocalDate publicationDate;
}
