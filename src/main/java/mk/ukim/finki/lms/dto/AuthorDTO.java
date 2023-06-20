package mk.ukim.finki.lms.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class AuthorDTO {

  private Long id;
  private String authorName;
  private String biography;
  private LocalDate birthDate;
}
