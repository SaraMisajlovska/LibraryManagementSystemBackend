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

  public void register(String p_email, String p_user_password, String p_first_name,
                       String p_last_name, LocalDate p_date_of_birth, String p_address,
                       String p_phone_number, Integer p_card_number, Integer p_membership_id) {

    String sql = "SELECT register_patron(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplate.update(sql, p_email, p_user_password, p_first_name, p_last_name, p_date_of_birth,
            p_address, p_phone_number, p_card_number, p_membership_id);
  }
}
