package mk.ukim.finki.lms.dto;

import lombok.Builder;

@Builder
public class UserDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
}
