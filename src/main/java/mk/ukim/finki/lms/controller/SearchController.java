package mk.ukim.finki.lms.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.lms.dto.AuthorDTO;
import mk.ukim.finki.lms.dto.BookDTO;
import mk.ukim.finki.lms.dto.EventDTO;
import mk.ukim.finki.lms.repository.SearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SearchController {

  private final SearchRepository searchRepository;

  @GetMapping("/searchBooks")
  public String displaySearchBooks(Model model) {
    model.addAttribute("page", 1);

    return "search/books";
  }

  @PostMapping("/searchBooks")
  public String searchBooksBy(Model model,
                              @RequestParam(value = "author_name", required = false) String authorName,
                              @RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "categoryName", required = false) String categoryName,
                              @RequestParam(value = "page", defaultValue = "1") int page) {

    int pageSize = 10;
    int offset = (page - 1) * pageSize;

    List<BookDTO> books = searchRepository.searchBooks(authorName, title, categoryName);
    int totalBooks = books.size();

    // Apply pagination
    List<BookDTO> paginatedBooks = books.stream()
        .skip(offset)
        .limit(pageSize)
        .collect(Collectors.toList());

    model.addAttribute("books", paginatedBooks);
    model.addAttribute("totalBooks", totalBooks);
    model.addAttribute("page", page);
    model.addAttribute("authorName", authorName);
    model.addAttribute("title", title);
    model.addAttribute("categoryName", categoryName);
    return "search/books";
  }

  @GetMapping("/searchAuthors")
  public String displaySearchAuthors(Model model) {

    model.addAttribute("page", 1);
    return "search/authors";
  }

  @PostMapping("/searchAuthors")
  public String searchAuthorsBy(Model model,
                                @RequestParam(value = "author_name") String authorName,
                                @RequestParam(value = "page", defaultValue = "1") int page) {

    int pageSize = 10;
    int offset = (page - 1) * pageSize;

    List<AuthorDTO> authors = searchRepository.searchAuthors(authorName);
    int totalAuthors = authors.size();

    // Apply pagination
    List<AuthorDTO> paginatedAuthors = authors.stream()
        .skip(offset)
        .limit(pageSize)
        .collect(Collectors.toList());

    model.addAttribute("authors", paginatedAuthors);
    model.addAttribute("totalAuthors", totalAuthors);
    model.addAttribute("page", page);
    model.addAttribute("authorName", authorName);
    return "search/authors";
  }


  @GetMapping("/searchEvents")
  public String displaySearchEvents(Model model) {
    model.addAttribute("page", 1);
    return "search/events";
  }

  //NOTE: event time format yyyy-mm-dd
  @PostMapping("/searchEvents")
  public String searchEventsBy(Model model,
                               @RequestParam(value = "event_name", required = false) String eventName,
                               @RequestParam(value = "event_time", required = false) String eventTime,
                               @RequestParam(value = "page", defaultValue = "1") int page) {

    int pageSize = 10; // Number of events per page
    int offset = (page - 1) * pageSize;

    List<EventDTO> events = searchRepository.searchEvents(eventName, eventTime);
    int totalEvents = events.size();

    // Apply pagination
    List<EventDTO> paginatedEvents = events.stream()
        .skip(offset)
        .limit(pageSize)
        .collect(Collectors.toList());

    model.addAttribute("events", paginatedEvents);
    model.addAttribute("totalEvents", totalEvents);
    model.addAttribute("page", page);
    model.addAttribute("eventName", eventName);
    model.addAttribute("eventTime", eventTime);

    return "search/events";
  }

}
