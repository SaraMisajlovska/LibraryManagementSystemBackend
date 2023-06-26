package mk.ukim.finki.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.dto.BookDTO;
import mk.ukim.finki.lms.dto.BorrowedBooksDTO;
import mk.ukim.finki.lms.dto.ReservationDTO;
import mk.ukim.finki.lms.dto.UnreturnedBooksDTO;
import mk.ukim.finki.lms.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

  @GetMapping("bookBorrow")
  public String getBookBorrow(Model model) {
    model.addAttribute("successMessage", false);

    return "book/book-borrow";
  }

  @PostMapping("/bookBorrow")
  public String bookBorrow(@RequestParam("userId") Integer userId,
                           @RequestParam("bookCopyId") Integer bookCopyId,
                           @RequestParam("bookTitle") String bookTitle,
                           @RequestParam("bookCheckout") LocalDate bookCheckout,
                           @RequestParam("checkoutLibrarianId") Integer checkoutLibrarianId,
                           Model model) {

    bookRepository.borrowBook(userId, bookCopyId, bookTitle, bookCheckout, checkoutLibrarianId);

    // Set success message
    String successMessage = "Book borrow successful!";
    model.addAttribute("successMessage", successMessage);

    // Return the same view with the success message
    return "book/book-borrow";
  }

  @GetMapping("/calculateFee")
  public String calculateFee() {
    return "book/calculate-fee";
  }

  @PostMapping("/calculateFee")
  public String calculateFee(Model model,
                             @RequestParam("cardNumber") Integer cardNumber,
                             @RequestParam(value = "bookTitle", required = false) String bookTitle) {

    BigDecimal fee = bookRepository.calculateLateFee(cardNumber, bookTitle);
    model.addAttribute("fee", fee + " den.");

    return "book/calculate-fee";
  }

  @GetMapping("/allReservations")
  public String allReservations(Model model,
                                @RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("cardNumber") Integer cardNumber) {

    List<ReservationDTO> reservations = bookRepository.getReservations(firstName, lastName, cardNumber);
    model.addAttribute("reservations", reservations);

    return "book/all-reservations";
  }

  @GetMapping("/unreturnedBooks")
  public String unreturnedBooks(Model model,
                                @RequestParam("cardNumber") Integer cardNumber) {

    List<UnreturnedBooksDTO> unreturnedBooks = bookRepository.getUnreturnedBooks(cardNumber);
    model.addAttribute("unreturnedBooks", unreturnedBooks);

    return "book/unreturned-books";
  }

  @GetMapping("/borrowedBooksHistory")
  public String borrowedBooksHistory(Model model,
                                     @RequestParam(value = "borrowerName", required = false) String borrowerName,
                                     @RequestParam(value = "cardNumber") Integer cardNumber,
                                     @RequestParam(value = "bookTitle", required = false) String bookTitle) {

    List<BorrowedBooksDTO> borrowedBooks = bookRepository.getBorrowedBooks(borrowerName, cardNumber, bookTitle);
    model.addAttribute("borrowedBooks", borrowedBooks);

    return "book/borrowed-books";
  }

  @GetMapping("/bookCopiesCount")
  public String bookCopiesCount(Model model, @RequestParam("bookCopyId") Long bookCopyId) {

    List<BookDTO> bookCopies = bookRepository.getBookCopyCount(bookCopyId);

    Integer count = bookCopies.get(0).getBookCopyCount();
    model.addAttribute("count", "The book copy with id: " + bookCopyId + " has been borrowed " + count + " times.");

    return "book/book-copies-count";
  }

}
