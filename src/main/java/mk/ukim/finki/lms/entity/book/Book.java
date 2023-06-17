package mk.ukim.finki.lms.entity.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.lms.entity.Author;
import mk.ukim.finki.lms.entity.Category;
import mk.ukim.finki.lms.entity.Publisher;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "book")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "publisher_id", nullable = false)
  private Publisher publisher;

  @Column(name = "publication_date", nullable = false)
  private LocalDate publicationDate;

  @Column(name = "summary", nullable = false)
  private String summary;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_request_id")
  private BookRequest bookRequest;

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "book_author",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "author_id")
  )
  private Set<Author> authors = new HashSet<>();

  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
      name = "book_category",
      joinColumns = @JoinColumn(name = "book_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id")
  )
  //TODO falat rating and review tuka
  private Set<Category> categories = new HashSet<>();
}