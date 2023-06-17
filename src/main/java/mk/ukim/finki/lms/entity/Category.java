package mk.ukim.finki.lms.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.entity.book.Book;
import mk.ukim.finki.lms.enums.CategoryName;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "category")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "category_name", nullable = false)
  private CategoryName categoryName;

  @ManyToMany(mappedBy = "categories")
  private Set<Book> books = new HashSet<>();
}