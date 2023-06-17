package mk.ukim.finki.lms.entity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "library_user")
public class LibraryUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  //    @Size(min = 8)
  @Column(name = "user_password", nullable = false)
  private String userPassword;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "address", nullable = false)
  private String address;

  //TODO: ova da se sredi
//    @Size(min = 10, max = 10)
  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;
}