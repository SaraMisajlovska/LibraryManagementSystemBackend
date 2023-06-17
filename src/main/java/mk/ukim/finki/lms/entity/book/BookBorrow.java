package mk.ukim.finki.lms.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.entity.Patron;
import mk.ukim.finki.lms.entity.user.Librarian;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "book_borrow")
public class BookBorrow {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private Patron patron;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_copy_id", nullable = false)
  private BookCopy bookCopy;

  @Column(name = "book_checkout", nullable = false)
  private LocalDate bookCheckout;

  @Column(name = "book_return")
  private LocalDate bookReturn;

  @Column(name = "damage")
  private String damage;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "checkout_librarian_id", nullable = false)
  private Librarian checkoutLibrarian;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "return_librarian_id")
  private Librarian returnLibrarian;
}
