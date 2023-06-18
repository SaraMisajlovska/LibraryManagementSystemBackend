package mk.ukim.finki.lms.dto;

import lombok.Builder;

import java.time.LocalDate;

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
