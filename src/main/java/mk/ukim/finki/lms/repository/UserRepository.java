package mk.ukim.finki.lms.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
}
