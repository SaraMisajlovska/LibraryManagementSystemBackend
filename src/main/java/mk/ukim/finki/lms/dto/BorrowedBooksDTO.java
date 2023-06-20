package mk.ukim.finki.lms.dto;

import lombok.Builder;

import java.time.LocalDate;

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
