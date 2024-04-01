package br.com.fiap.bookstoore.cp1.model;

import jakarta.persistence.*;
import jakarta.websocket.ClientEndpoint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "JAVA_CP1_ADRESS")
@EntityListeners(AuditingEntityListener.class)
public class Adress {

    @Id
    @Column(name = "adress_id")
    private Long id;

    @Column(name = "cep", nullable = false, length = 11)
    private String cep;

    @Column(name = "street", nullable = false, length = 70)
    private String street;

    @Column(name = "number", nullable = false)
    private int number;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
