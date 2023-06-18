package mk.ukim.finki.lms.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class UnreturnedBooksDTO {
    private String patronFirstName;
    private String patronLastName;
    private String patronCardNumber;
    private String bookTitle;
    private String authors;
    private LocalDate bookCheckoutDate;
    private Integer daysBorrowed;
}
