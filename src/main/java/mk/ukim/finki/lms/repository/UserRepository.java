package mk.ukim.finki.lms.repository;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.lms.dto.UserInfoDTO;
import mk.ukim.finki.lms.dto.UserReservationDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

  private final JdbcTemplate jdbcTemplate;

  public boolean login(String email, String password) {
    String sql = "SELECT login(?, ?)";
    return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class, email, password));
  }

  public void register(String email, String user_password, String first_name,
                       String last_name, LocalDate date_of_birth, String address,
                       String phone_number, Integer card_number, Integer membership_id) {

    String sql = "SELECT register_patron(?, ?, ?, ?, ?, ?, ?, ?, ?)";
    jdbcTemplate.query(sql, params -> {
        }, email, user_password, first_name, last_name, date_of_birth,
        address, phone_number, card_number, membership_id);
  }

  public List<UserReservationDTO> userActiveReservations(String firstName, String lastName, Integer cardNumber) {

    String sql = "SELECT * FROM user_active_reservations_view WHERE " +
        "(first_name LIKE ? )" +
        "AND (last_name LIKE ?) " +
        "AND (card_number::varchar LIKE ?) ";

    Object[] queryParams = new Object[]{
        "%" + firstName + "%",
        "%" + lastName + "%",
        "%" + cardNumber + "%"
    };

    return jdbcTemplate.query(sql,
        queryParams,
        (resultSet, rowNum) -> mapToUserReservation(resultSet));
  }

  public List<UserInfoDTO> getUserInfo(String firstName, String lastName) {

    String sql = "SELECT * FROM user_info_view WHERE " +
            "(first_name LIKE ? )" +
            "AND (last_name LIKE ?) ";

    Object[] queryParams = new Object[]{
            "%" + firstName + "%",
            "%" + lastName + "%"
    };

    return jdbcTemplate.query(sql,
            queryParams,
            (resultSet, rowNum) -> mapToUserInfo(resultSet));
  }

  private UserReservationDTO mapToUserReservation(ResultSet resultSet) {
    try {
      return UserReservationDTO.builder()
          .firstName(resultSet.getString("first_name"))
          .lastName(resultSet.getString("last_name"))
          .cardNumber(Integer.valueOf(resultSet.getString("card_number")))
          .bookTitle(resultSet.getString("book_title"))
          .bookAuthor(resultSet.getString("book_author"))
          .bookCategory(resultSet.getString("book_category"))
          .reservationDate(resultSet.getDate("reservation_date").toLocalDate())
          .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  private UserInfoDTO mapToUserInfo(ResultSet resultSet) {
    try {
      return UserInfoDTO.builder()
              .firstName(resultSet.getString("first_name"))
              .lastName(resultSet.getString("last_name"))
              .dateOfBirth(resultSet.getDate("date_of_birth").toLocalDate())
              .address(resultSet.getString("address"))
              .phoneNumber(resultSet.getString("phone_number"))
              .cardNumber(resultSet.getString("card_number"))
              .membershipPackage(resultSet.getString("membership_package"))
              ._case(resultSet.getString("case"))
              .build();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

}
