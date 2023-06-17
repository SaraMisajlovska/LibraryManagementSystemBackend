package mk.ukim.finki.lms.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.enums.BookFormat;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "book_copy")
public class BookCopy {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "location_id", nullable = false)
  private BookLocation location;

  @Column(name = "edition", nullable = false)
  private Integer edition;

  @Enumerated(EnumType.STRING)
  @Column(name = "book_format", nullable = false)
  private BookFormat bookFormat;
}
