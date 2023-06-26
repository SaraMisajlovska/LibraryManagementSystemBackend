package mk.ukim.finki.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.repository.InsertRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InsertController {

    private final InsertRepository insertRepository;

    @GetMapping("/insertBookCopy")
    public String getInsertBookCopy(Model model) {
        model.addAttribute("successMessage", false);

        return "insert/insert-book-copy";
    }

    @PostMapping("/insertBookCopy")
    public String insertBookCopy(@RequestParam("book_id") Integer bookId,
                           @RequestParam("location_id") Integer locationId,
                           @RequestParam("edition") Integer edition,
                           @RequestParam("book_format") String bookFormat,
                           Model model) {

        // Invoke the register_patron function
        insertRepository.insertBookCopy(bookId, locationId, edition, bookFormat);

        String successMessage = "Book copy insert successful!";
        model.addAttribute("successMessage", successMessage);

        // Return a response indicating success
        return "insert/insert-book-copy";
    }

    @GetMapping("/insertBookReview")
    public String getInsertBookReview(Model model) {
        model.addAttribute("successMessage", false);

        return "insert/insert-book-review";
    }

    @PostMapping("/insertBookReview")
    public String insertBookReview(@RequestParam("patron_id") Integer patronId,
                                   @RequestParam("book_id") Integer bookId,
                                   @RequestParam("review") String review,
                                   Model model) {

        // Invoke the register_patron function
        insertRepository.insertBookReview(patronId, bookId, review);

        String successMessage = "Book review insert successful!";
        model.addAttribute("successMessage", successMessage);

        return "insert/insert-book-review";
    }

}
