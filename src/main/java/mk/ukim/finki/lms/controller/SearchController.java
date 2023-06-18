package mk.ukim.finki.lms.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.lms.dto.AuthorDTO;
import mk.ukim.finki.lms.dto.BookDTO;
import mk.ukim.finki.lms.dto.EventDTO;
import mk.ukim.finki.lms.repository.SearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

  private final SearchRepository searchRepository;

  @PostMapping("/searchBooks")
  public String searchBooksBy(@RequestParam(value = "author_name", required = false) String authorName,
                              @RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "categoryName", required = false) String categoryName) {


    List<BookDTO> books = searchRepository.searchBooks(authorName, title, categoryName);

    return null;
  }

  @PostMapping("/searchAuthors")
  public String searchAuthorsBy(@RequestParam(value = "author_name") String authorName) {


    List<AuthorDTO> authors = searchRepository.searchAuthors(authorName);

    return null;
  }

  //NOTE: event time format yyyy-mm-dd
  @PostMapping("/searchEvents")
  public String searchEventsBy(@RequestParam(value = "event_name", required = false) String eventName,
                               @RequestParam(value = "event_time", required = false) String eventTime) {

    List<EventDTO> events = searchRepository.searchEvents(eventName, eventTime);

    return null;
  }

}
