package mk.ukim.finki.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.dto.AuthorDTO;
import mk.ukim.finki.lms.dto.BookDTO;
import mk.ukim.finki.lms.dto.EventDTO;
import mk.ukim.finki.lms.repository.SearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {

  private final SearchRepository searchRepository;

  @PostMapping("/searchBooks")
  public String searchBooksBy(Model model,
                              @RequestParam(value = "author_name", required = false) String authorName,
                              @RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "categoryName", required = false) String categoryName) {
    List<BookDTO> books = searchRepository.searchBooks(authorName, title, categoryName);

    model.addAttribute("books", books);
    return "search/books";
  }

  @PostMapping("/searchAuthors")
  public String searchAuthorsBy(Model model,
                                @RequestParam(value = "author_name") String authorName) {
    List<AuthorDTO> authors = searchRepository.searchAuthors(authorName);

    model.addAttribute("authors", authors);
    return "search/authors";
  }

  //NOTE: event time format yyyy-mm-dd
  @PostMapping("/searchEvents")
  public String searchEventsBy(Model model,
                               @RequestParam(value = "event_name", required = false) String eventName,
                               @RequestParam(value = "event_time", required = false) String eventTime) {
    List<EventDTO> events = searchRepository.searchEvents(eventName, eventTime);

    model.addAttribute("events", events);
    return "search/events";
  }

}
