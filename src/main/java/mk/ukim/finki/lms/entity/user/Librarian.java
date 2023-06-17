package mk.ukim.finki.lms.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "librarian")
public class Librarian extends LibraryUser {

  @Column(name = "job_title", nullable = false)
  private String jobTitle;

  @Column(name = "hire_date", nullable = false)
  private LocalDate hireDate;
}
