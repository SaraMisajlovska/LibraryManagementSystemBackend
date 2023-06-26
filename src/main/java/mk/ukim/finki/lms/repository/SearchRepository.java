package mk.ukim.finki.lms.repository;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.lms.dto.AuthorDTO;
import mk.ukim.finki.lms.dto.BookDTO;
import mk.ukim.finki.lms.dto.EventDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearchRepository {

  private final JdbcTemplate jdbcTemplate;

  public List<BookDTO> searchBooks(String authorName, String title, String categoryName) {
    String sql = "SELECT * FROM search_books(?, ?, ?)";
    return jdbcTemplate.query(
        sql,
        new Object[]{authorName, title, categoryName},
        (resultSet, rowNum) -> mapRowToBook(resultSet)
    );
  }

  public List<AuthorDTO> searchAuthors(String authorName) {
    String sql = "SELECT * FROM search_authors(?)";
    return jdbcTemplate.query(
        sql,
        new Object[]{authorName},
        (resultSet, rowNum) -> mapRowToAuthor(resultSet)
    );
  }

  public List<EventDTO> searchEvents(String eventName, String eventTime) {

    Date convertedEventTime = !eventTime.isBlank() ? Date.valueOf(eventTime) : null;

    String sql = "SELECT * FROM search_events(?, ?)";
    return jdbcTemplate.query(
        sql,
        new Object[]{eventName, convertedEventTime},
        (resultSet, rowNum) -> mapRowToEvent(resultSet)
    );
  }

  private BookDTO mapRowToBook(ResultSet resultSet) {
    try {
      return BookDTO.builder()
          .title(resultSet.getString("r_title"))
          .authorName(resultSet.getString("r_author_name"))
          .categoryName(resultSet.getString("r_category_name"))
          .publicationDate(resultSet.getDate("r_publication_date").toLocalDate())
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private AuthorDTO mapRowToAuthor(ResultSet resultSet) {
    try {
      return AuthorDTO.builder()
          .authorName(resultSet.getString("r_author_name"))
          .biography(resultSet.getString("r_biography"))
          .birthDate(resultSet.getDate("r_birth_date").toLocalDate())
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private EventDTO mapRowToEvent(ResultSet resultSet) {
    try {
      return EventDTO.builder()
          .eventName(resultSet.getString("r_event_name"))
          .description(resultSet.getString("r_description"))
          .eventTime(resultSet.getDate("r_event_datetime").toLocalDate())
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
