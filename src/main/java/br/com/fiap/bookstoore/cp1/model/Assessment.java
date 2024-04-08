package br.com.fiap.bookstoore.cp1.model;

import br.com.fiap.bookstoore.cp1.dto.assessment.CreateAssessmentDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "java_cp1_assessment")
public class Assessment {

    @Id
    @GeneratedValue
    @Column(name = "assessment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "rating", nullable = false)
    private Double rating;

    @Column(name = "customer_comment", nullable = false, length = 50)
    private String comment;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Assessment(CreateAssessmentDTO assessmentDTO) {
        rating = assessmentDTO.rating();
        comment = assessmentDTO.comment();
    }

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}