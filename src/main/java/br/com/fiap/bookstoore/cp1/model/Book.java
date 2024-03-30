package br.com.fiap.bookstoore.cp1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "JAVA_CP1_BOOK")
@EntityListeners(AuditingEntityListener.class)
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "isbn", nullable = false, length = 20, unique = true)
    private String isbn;

    @Column(name = "author", nullable = false, length = 50)
    private String author;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender_type", nullable = false, length = 50)
    private GenderTypes gender;

    @Column(name = "value", nullable = false)
    private Double value;

    @Column(name = "rating")
    private Double rating;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "books")
    private Set<Customer> customers = new HashSet<>();
}
