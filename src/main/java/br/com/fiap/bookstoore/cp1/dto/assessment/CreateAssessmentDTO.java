package br.com.fiap.bookstoore.cp1.dto.assessment;

public record CreateAssessmentDTO(
        Double rating,
        String comment
) {
}
