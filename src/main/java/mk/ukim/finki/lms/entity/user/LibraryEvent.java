package mk.ukim.finki.lms.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "library_event")
public class LibraryEvent {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "event_name", nullable = false)
  private String eventName;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "event_datetime", nullable = false)
  private LocalDateTime eventDateTime;
}
