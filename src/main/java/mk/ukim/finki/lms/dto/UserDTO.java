package mk.ukim.finki.lms.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
}
