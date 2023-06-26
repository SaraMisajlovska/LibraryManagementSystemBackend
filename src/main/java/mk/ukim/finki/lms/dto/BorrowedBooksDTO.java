package mk.ukim.finki.lms.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BorrowedBooksDTO {
    private Long borrowId;
    private String borrowerName;
    private Integer cardNumber;
    private String bookTitle;
    private String bookAuthor;
    private String bookFormat;
    private LocalDate checkoutDate;
    private LocalDate returnDate;
    private String damageDescription;
    private String bookCategory;
}
