package mk.ukim.finki.lms.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.entity.Patron;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "book_request")
public class BookRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private Patron patron;

  @Column(name = "book_title", nullable = false)
  private String bookTitle;

  @Column(name = "author", nullable = false)
  private String author;
}
