package mk.ukim.finki.lms.repository;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.lms.dto.BookDTO;
import mk.ukim.finki.lms.dto.BorrowedBooksDTO;
import mk.ukim.finki.lms.dto.ReservationDTO;
import mk.ukim.finki.lms.dto.UnreturnedBooksDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

  private final JdbcTemplate jdbcTemplate;

  public void borrowBook(Integer userId, Integer bookCopyId, String bookTitle, LocalDate bookCheckout, Integer checkoutLibrarianId) {

    String sql = "SELECT borrow_book(?, ?, ?, ?, ?)";
    jdbcTemplate.query(sql, params -> {
    }, userId, bookCopyId, bookTitle, bookCheckout, checkoutLibrarianId);
  }

  public BigDecimal calculateLateFee(Integer cardNumber, String bookTitle) {

    String sql = "SELECT calculate_total_price_for_unreturned_books(?, ?)";

    return jdbcTemplate.queryForObject(sql,
        new Object[]{cardNumber, bookTitle},
        BigDecimal.class);
  }

  public List<ReservationDTO> getReservations(String firstName, String lastName, Integer cardNumber) {

    String sql = "SELECT * FROM all_reservations_view WHERE " +
        "(patron_first_name LIKE ? )" +
        "AND (patron_last_name LIKE ?) " +
        "AND (patron_card_number::varchar LIKE ?) ";

    Object[] queryParams = new Object[]{
        "%" + firstName + "%",
        "%" + lastName + "%",
        "%" + cardNumber + "%"
    };

    return jdbcTemplate.query(sql, queryParams, (resultSet, rowNum) -> mapRowToReservation(resultSet));
  }

  public List<UnreturnedBooksDTO> getUnreturnedBooks(Integer cardNumber) {

    String sql = "SELECT * FROM unreturned_books WHERE " +
        "(card_number::varchar LIKE ?) ";

    Object[] queryParams = new Object[]{
        "%" + cardNumber + "%"
    };

    return jdbcTemplate.query(sql, queryParams, (resultSet, rowNum) -> mapRowToUnreturnedBooks(resultSet));
  }

  public List<BorrowedBooksDTO> getBorrowedBooks(String borrowerName, Integer cardNumber, String bookTitle) {

    String sql = "SELECT * FROM borrowed_books_history WHERE " +
        "(borrower_name LIKE ? ) " +
        "OR (book_title LIKE ?) " +
        "OR (card_number::varchar LIKE ?)";

    Object[] queryParams = new Object[]{
        "%" + borrowerName + "%",
        "%" + bookTitle + "%",
        "%" + cardNumber + "%"
    };

    return jdbcTemplate.query(sql, queryParams, (resultSet, rowNum) -> mapRowToBorrowedBooks(resultSet));
  }

  public List<BookDTO> getBookCopyCount(Long bookCopyId) {

    String sql = "SELECT * FROM copy_borrow_count WHERE " +
        "(book_copy_id::varchar LIKE ?) ";

    Object[] queryParams = new Object[]{
        "%" + bookCopyId + "%"
    };

    return jdbcTemplate.query(sql, queryParams, (resultSet, rowNum) -> mapRowToBookCopyCount(resultSet));
  }


  public void reserveBook(Integer book_copy_id, String book_title, Integer patron_id, LocalDate reservation_date) {

    String sql = "SELECT reserve_book(?, ?, ?, ?)";
    jdbcTemplate.query(sql, params -> {
    }, book_copy_id, book_title, patron_id, reservation_date);
  }

  private BookDTO mapRowToBookCopyCount(ResultSet resultSet) {
    try {
      return BookDTO.builder()
          .id(resultSet.getLong("book_copy_id"))
          .bookCopyCount(resultSet.getInt("borrow_count"))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private ReservationDTO mapRowToReservation(ResultSet resultSet) {
    try {
      return ReservationDTO.builder()
          .patronFirstName(resultSet.getString("patron_first_name"))
          .patronLastName(resultSet.getString("patron_last_name"))
          .patronCardNumber(resultSet.getString("patron_card_number"))
          .bookTitle(resultSet.getString("book_title"))
          .bookAuthor(resultSet.getString("book_author"))
          .bookCategory(resultSet.getString("book_category"))
          .reservationDate(resultSet.getDate("reservation_date").toLocalDate())
          .reservationStatus(resultSet.getString("reservation_status"))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private UnreturnedBooksDTO mapRowToUnreturnedBooks(ResultSet resultSet) {
    try {
      return UnreturnedBooksDTO.builder()
          .patronFirstName(resultSet.getString("first_name"))
          .patronLastName(resultSet.getString("last_name"))
          .patronCardNumber(resultSet.getString("card_number"))
          .bookTitle(resultSet.getString("title"))
          .authors(resultSet.getString("authors"))
          .bookCheckoutDate(resultSet.getDate("book_checkout").toLocalDate())
          .daysBorrowed(resultSet.getInt("days_borrowed"))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private BorrowedBooksDTO mapRowToBorrowedBooks(ResultSet resultSet) {
    try {
      return BorrowedBooksDTO.builder()
          .borrowId(resultSet.getLong("borrow_id"))
          .borrowerName(resultSet.getString("borrower_name"))
          .cardNumber(resultSet.getInt("card_number"))
          .bookTitle(resultSet.getString("book_title"))
          .bookAuthor(resultSet.getString("book_author"))
          .bookFormat(resultSet.getString("book_format"))
          .checkoutDate(resultSet.getDate("checkout_date").toLocalDate())
          .returnDate(resultSet.getObject("return_date", LocalDate.class))
          .damageDescription(resultSet.getString("damage_description"))
          .bookCategory(resultSet.getString("book_category"))
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
