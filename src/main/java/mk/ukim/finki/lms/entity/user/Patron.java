package mk.ukim.finki.lms.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.entity.Membership;
import mk.ukim.finki.lms.entity.user.LibraryUser;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "patron")
public class Patron extends LibraryUser {

  @SequenceGenerator(name = "card_number_seq")
  @Column(name = "card_number", unique = true, nullable = false)
  private Integer cardNumber;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "membership_id", nullable = false)
  private Membership membership;
}
