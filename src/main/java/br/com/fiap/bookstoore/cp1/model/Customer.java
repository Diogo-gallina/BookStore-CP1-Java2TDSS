package br.com.fiap.bookstoore.cp1.model;

import br.com.fiap.bookstoore.cp1.dto.customer.CreateCustomerDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name="java_cp1_customer")
@EntityListeners(AuditingEntityListener.class)
public class Customer {

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "email", nullable = false, length = 70)
    private String email;

    @Column(name = "password", nullable = false, length = 40)
    private String password;

    @Column(name = "birthday_date", nullable = false)
    private LocalDate birthdayDate;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "customer")
    private List<ShoppingCart> shoppingCart;

    @ManyToMany
    @JoinTable(name = "java_cp1_customer_book",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Set<Book> books = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private List<Adress> addresses;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

    public Customer(CreateCustomerDTO customerDTO) {
        this.name = customerDTO.name();
        this.email = customerDTO.email();
        this.password = customerDTO.password();
        this.birthdayDate = customerDTO.birthdayDate();
    }
}
