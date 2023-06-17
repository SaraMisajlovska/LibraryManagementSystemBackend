package mk.ukim.finki.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.repository.InsertRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InsertController {

    private final InsertRepository insertRepository;

    @PostMapping("/insertBookCopy")
    public String insertBookCopy(@RequestParam("book_id") Integer bookId,
                           @RequestParam("location_id") Integer locationId,
                           @RequestParam("edition") Integer edition,
                           @RequestParam("book_format") String bookFormat) {

        // Invoke the register_patron function
        insertRepository.insertBookCopy(bookId, locationId, edition, bookFormat);

        // Return a response indicating success
        return null;
    }

    @PostMapping("/insertBookReview")
    public String insertBookReview(@RequestParam("patron_id") Integer patronId,
                                   @RequestParam("book_id") Integer bookId,
                                   @RequestParam("review") String review) {

        // Invoke the register_patron function
        insertRepository.insertBookReview(patronId, bookId, review);

        // Return a response indicating success
        return null;
    }

}
