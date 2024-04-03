package br.com.fiap.bookstoore.cp1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private List<Book> items;

    @Transient
    private Double amount;

}
