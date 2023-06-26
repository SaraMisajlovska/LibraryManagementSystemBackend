package mk.ukim.finki.lms.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class EventDTO {

  private Long id;
  private String eventName;
  private String description;
  private LocalDate eventTime;
  private Long numAttendees;
}
