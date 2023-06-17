package mk.ukim.finki.lms.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InsertRepository {

    private final JdbcTemplate jdbcTemplate;

    public void insertBookCopy(Integer book_id, Integer location_id, Integer edition, String book_format) {

        String sql = "SELECT insert_book_copy(?, ?, ?, ?)";
        jdbcTemplate.query(sql, params -> {}, book_id, location_id, edition, book_format);
    }

    public void insertBookReview(Integer patron_id, Integer book_id, String review) {

        String sql = "SELECT insert_book_review(?, ?, ?)";
        jdbcTemplate.query(sql, params -> {}, patron_id , book_id, review);
    }
}

