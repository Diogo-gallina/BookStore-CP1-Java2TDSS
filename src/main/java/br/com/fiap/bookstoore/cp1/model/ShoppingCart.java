package br.com.fiap.bookstoore.cp1.model;

import br.com.fiap.bookstoore.cp1.dto.shoppingCart.CreateShoppingCartDTO;
import br.com.fiap.bookstoore.cp1.repository.BookRepository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "java_cp1_shopping_cart")
@EntityListeners(AuditingEntityListener.class)
public class ShoppingCart {

    @Id
    @GeneratedValue
    @Column(name = "shopping_cart_id")
    private Long id;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "java_cp1_shopping_cart_books",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Book> books = new HashSet<>();

    @Transient
    private Double amount;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }


}
