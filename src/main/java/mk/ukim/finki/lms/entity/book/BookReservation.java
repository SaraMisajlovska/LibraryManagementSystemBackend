package mk.ukim.finki.lms.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.entity.user.Patron;
import mk.ukim.finki.lms.enums.ReservationStatus;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "book_reservation")
public class BookReservation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_copy_id", nullable = false)
  private BookCopy bookCopy;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private Patron patron;

  @Enumerated(EnumType.STRING)
  @Column(name = "reservation_status", nullable = false)
  private ReservationStatus reservationStatus;

  @Column(name = "reservation_date", nullable = false)
  private LocalDate reservationDate;
}
