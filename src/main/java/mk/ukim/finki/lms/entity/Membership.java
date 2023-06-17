package mk.ukim.finki.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import mk.ukim.finki.lms.enums.MembershipPackage;

@Getter

@Entity
@Table(name = "membership")
public class Membership {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "price", nullable = false)
  private Double price;

  @Column(name = "active", columnDefinition = "boolean default true")
  private Boolean active;

  @Enumerated(EnumType.STRING)
  @Column(name = "package", nullable = false)
  private MembershipPackage membershipPackage;
}
