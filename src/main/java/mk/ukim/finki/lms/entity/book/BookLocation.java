package mk.ukim.finki.lms.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "book_location")
public class BookLocation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "section", nullable = false)
  private String section;

  @Column(name = "shelf", nullable = false)
  private Integer shelf;

}
