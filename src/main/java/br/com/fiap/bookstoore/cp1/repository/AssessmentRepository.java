package br.com.fiap.bookstoore.cp1.repository;

import br.com.fiap.bookstoore.cp1.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
