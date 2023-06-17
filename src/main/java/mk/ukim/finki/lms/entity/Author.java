package mk.ukim.finki.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter

@Entity
@Table(name = "author")
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "author_name", nullable = false)
  private String authorName;

  @Column(name = "birth_date", nullable = false)
  private LocalDate birthDate;

  @Column(name = "biography")
  private String biography;
}
