package mk.ukim.finki.lms.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class BookDTO {

  private Long id;
  private String title;
  private String authorName;
  private String categoryName;
  private LocalDate publicationDate;
}
