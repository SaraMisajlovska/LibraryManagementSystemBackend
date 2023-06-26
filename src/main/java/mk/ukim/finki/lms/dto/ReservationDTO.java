package mk.ukim.finki.lms.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ReservationDTO {
    private String patronFirstName;
    private String patronLastName;
    private String patronCardNumber;
    private String bookTitle;
    private String bookAuthor;
    private String bookCategory;
    private LocalDate reservationDate;
    private String reservationStatus;
}
