package br.com.fiap.bookstoore.cp1.controller;

import br.com.fiap.bookstoore.cp1.dto.assessment.AssessmentDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.assessment.CreateAssessmentDTO;
import br.com.fiap.bookstoore.cp1.dto.assessment.UpdateAssessmentDTO;
import br.com.fiap.bookstoore.cp1.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/assessments")
public class AssessmentController {

    @Autowired
    AssessmentService assessmentService;

    @PostMapping("book/{book_id}/customer/{customer_id}")
    public ResponseEntity<AssessmentDetailsDTO> create(
            @PathVariable("book_id") Long bookId,
            @PathVariable("customer_id") Long customerId,
            @RequestBody CreateAssessmentDTO assessmentDTO,
            UriComponentsBuilder uri
            ){
        var assessment = assessmentService.create(bookId, customerId, assessmentDTO);
        var url = uri.path("assessments/book/{book_id}/customer/{customer_id}")
                .buildAndExpand(bookId, customerId).toUri();
        return ResponseEntity.created(url).body(new AssessmentDetailsDTO(assessment));
    }

    @GetMapping
    public ResponseEntity<List<AssessmentDetailsDTO>> findAllAssessments(Pageable pageable){
        var assessmentList = assessmentService.getAllAssessments(pageable);
        return ResponseEntity.ok(assessmentList);
    }

    @GetMapping("{assessment_id}")
    public ResponseEntity<AssessmentDetailsDTO> findOneAssessment   (
            @PathVariable("assessment_id") Long assessmentId
    ){
        var assessment = assessmentService.getOneAssessment(assessmentId);
        return ResponseEntity.ok(assessment);
    }

    @GetMapping("book/{book_id}")
    public ResponseEntity<List<AssessmentDetailsDTO>> findAllAssessmentsByBook(
            Pageable pageable,
            @PathVariable("book_id") Long bookId
    ){
        var assessmentList = assessmentService.getAllAssessmentsByBook(pageable, bookId);
        return ResponseEntity.ok(assessmentList);
    }

    @PutMapping("{assessment_id}")
    public ResponseEntity<AssessmentDetailsDTO> update(
            @PathVariable("assessment_id") Long assessmentId,
            @RequestBody UpdateAssessmentDTO assessmentDTO
            ){
        var assessment = assessmentService.update(assessmentId, assessmentDTO);
        return ResponseEntity.ok(assessment);
    }

    @DeleteMapping("assessment_id")
    public ResponseEntity<Void> delete(
        @PathVariable("assessment_id") Long assessmentId
    ){
        assessmentService.delete(assessmentId);
        return ResponseEntity.noContent().build();
    }

}
