package mk.ukim.finki.lms.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class EventDTO {

  private Long id;
  private String eventName;
  private String description;
  private LocalDate eventTime;
  private Integer numAttendees;
}
