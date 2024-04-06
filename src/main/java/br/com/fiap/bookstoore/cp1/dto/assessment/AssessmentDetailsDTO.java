package br.com.fiap.bookstoore.cp1.dto.assessment;

import br.com.fiap.bookstoore.cp1.model.Assessment;

public record AssessmentDetailsDTO(
        Long id,
        Long bookId,
        Long customerId,
        Double rating,
        String comment
) {
    public AssessmentDetailsDTO(Assessment assessment){
        this(
                assessment.getId(),
                assessment.getBook().getId(),
                assessment.getCustomer().getId(),
                assessment.getRating(),
                assessment.getComment()
        );
    }
}
