package mk.ukim.finki.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "publisher")
public class Publisher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "publisher_name", nullable = false)
  private String publisherName;

  @Column(name = "publisher_address", nullable = false)
  private String publisherAddress;

  @Column(name = "contact", nullable = false)
  private String contact;
}
