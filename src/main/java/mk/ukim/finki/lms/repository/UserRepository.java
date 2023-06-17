package mk.ukim.finki.lms.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public boolean login(String email, String password) {
    String sql = "SELECT login(?, ?)";
    return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, email, password));
  }

  public void register(String email, String user_password, String first_name,
                       String last_name, LocalDate date_of_birth, String address,
                       String phone_number, Integer card_number, Integer membership_id) {

    String sql = "SELECT register_patron(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, email, user_password, first_name, last_name, date_of_birth,
            address, phone_number, card_number, membership_id);
  }
}
