package mk.ukim.finki.lms.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.entity.LibraryEvent;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "event_users")
public class EventUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "event_id", nullable = false)
  private LibraryEvent event;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private LibraryUser user;

  @Column(name = "user_type", nullable = false)
  private String userType;
}
