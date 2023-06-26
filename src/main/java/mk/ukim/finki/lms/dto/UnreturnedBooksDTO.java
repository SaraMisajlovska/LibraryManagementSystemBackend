package mk.ukim.finki.lms.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
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
