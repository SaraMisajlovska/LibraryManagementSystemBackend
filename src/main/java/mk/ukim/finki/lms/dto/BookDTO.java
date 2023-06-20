package mk.ukim.finki.lms.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class BookDTO {

  private Long id;
  private String title;
  private String authorName;
  private String categoryName;
  private LocalDate publicationDate;
  private Integer bookCopyCount;
}
