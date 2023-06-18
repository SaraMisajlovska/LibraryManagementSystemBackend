package mk.ukim.finki.lms.repository;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.lms.dto.BookDTO;
import mk.ukim.finki.lms.dto.ReservationDTO;
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

    public void borrowBook(Integer userId, Integer bookCopyId, LocalDate bookCheckout, Integer checkoutLibrarianId) {

            String sql = "SELECT borrow_book(?, ?, ?, ?)";
            jdbcTemplate.query(sql, params -> {}, userId, bookCopyId, bookCheckout, checkoutLibrarianId);
    }

    public BigDecimal calculateLateFee(Integer cardNumber, String  bookTitle) {

        String sql = "SELECT calculate_total_price_for_unreturned_books(?, ?)";

        return jdbcTemplate.queryForObject(sql,
                new Object[]{cardNumber, bookTitle},
                BigDecimal.class);
    }

    public List<ReservationDTO> getAllReservations(String firstName, String lastName, Integer cardNumber) {

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

}
