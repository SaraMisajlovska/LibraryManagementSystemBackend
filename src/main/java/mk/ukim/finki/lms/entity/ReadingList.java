package mk.ukim.finki.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.entity.book.Book;
import mk.ukim.finki.lms.entity.user.Patron;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "reading_list")
public class ReadingList {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @MapsId("userId")
  private Patron patron;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;
}
