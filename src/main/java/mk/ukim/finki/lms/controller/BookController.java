package mk.ukim.finki.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.dto.ReservationDTO;
import mk.ukim.finki.lms.dto.UnreturnedBooksDTO;
import mk.ukim.finki.lms.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;

    @PostMapping("/bookBorrow")
    public String bookBorrow(@RequestParam("userId") Integer userId,
                             @RequestParam("bookCopyId") Integer bookCopyId,
                             @RequestParam("bookCheckout") LocalDate bookCheckout,
                             @RequestParam("checkoutLibrarianId") Integer checkoutLibrarianId) {

        // Invoke the register_patron function
        bookRepository.borrowBook(userId, bookCopyId, bookCheckout, checkoutLibrarianId);

        // Return a response indicating success
        return null;
    }

    @PostMapping("/calculateFee")
    public String calculateFee(@RequestParam("cardNumber") Integer cardNumber,
                             @RequestParam("bookTitle") String bookTitle) {

        // Invoke the register_patron function
        BigDecimal fee = bookRepository.calculateLateFee(cardNumber, bookTitle);

        // Return a response indicating success
        return null;
    }

    @GetMapping("/allReservations")
    public String allReservations(@RequestParam("firstName") String firstName,
                                  @RequestParam("lastName") String lastName,
                                  @RequestParam("cardNumber") Integer cardNumber) {

        // Invoke the register_patron function
        List<ReservationDTO> reservations = bookRepository.getReservations(firstName, lastName, cardNumber);

        // Return a response indicating success
        return null;
    }

    @GetMapping("/unreturnedBooks")
    public String unreturnedBooks(@RequestParam("cardNumber") Integer cardNumber) {

        // Invoke the register_patron function
        List<UnreturnedBooksDTO> unreturnedBooks = bookRepository.getUnreturnedBooks(cardNumber);

        // Return a response indicating success
        return null;
    }


}
