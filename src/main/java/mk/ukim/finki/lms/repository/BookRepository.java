package mk.ukim.finki.lms.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;

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
}
