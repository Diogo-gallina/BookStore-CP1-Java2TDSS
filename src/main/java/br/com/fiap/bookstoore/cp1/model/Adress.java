package br.com.fiap.bookstoore.cp1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "java_cp1_adress")
public class Adress {

    @Id
    @GeneratedValue
    @Column(name = "adress_id")
    private Long id;

    @Column(name = "cep", nullable = false, length = 11)
    private String cep;

    @Column(name = "street", nullable = false, length = 70)
    private String street;

    @Column(name = "adress_number", nullable = false)
    private Integer adressNumber;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
