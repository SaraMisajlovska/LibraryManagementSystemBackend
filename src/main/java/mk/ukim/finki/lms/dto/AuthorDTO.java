package mk.ukim.finki.lms.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class AuthorDTO {

  private Long id;
  private String authorName;
  private String biography;
  private LocalDate birthDate;
}
