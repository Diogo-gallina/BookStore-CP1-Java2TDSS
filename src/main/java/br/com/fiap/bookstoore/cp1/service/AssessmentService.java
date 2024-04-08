package br.com.fiap.bookstoore.cp1.service;

import br.com.fiap.bookstoore.cp1.dto.assessment.AssessmentDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.assessment.CreateAssessmentDTO;
import br.com.fiap.bookstoore.cp1.dto.assessment.UpdateAssessmentDTO;
import br.com.fiap.bookstoore.cp1.model.Assessment;
import br.com.fiap.bookstoore.cp1.repository.AssessmentRepository;
import br.com.fiap.bookstoore.cp1.repository.BookRepository;
import br.com.fiap.bookstoore.cp1.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssessmentService {

    @Autowired
    AssessmentRepository assessmentRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BookRepository bookRepository;

    @Transactional
    public Assessment create(
            Long bookId,
            Long customerId,
            CreateAssessmentDTO assessmentDTO
    ){
        var customer = customerRepository.getReferenceById(customerId);
        var book = bookRepository.getReferenceById(bookId);
        var assesment = new Assessment(assessmentDTO);

        assesment.setCustomer(customer);
        assesment.setBook(book);

        assessmentRepository.save(assesment);

        return assesment;
    }

    public List<AssessmentDetailsDTO> getAllAssessments(Pageable pageable){
        var assessmentList = assessmentRepository.findAll(pageable)
                .stream().map(AssessmentDetailsDTO::new).toList();

        return assessmentList;
    }

    public List<AssessmentDetailsDTO> getAllAssessmentsByBook(Pageable pageable, Long bookId){
        var book = bookRepository.getReferenceById(bookId);
        var bookAssessmentList = book.getAssessments()
                .stream().map(AssessmentDetailsDTO::new).toList();
        return bookAssessmentList;
    }

    public AssessmentDetailsDTO getOneAssessment(Long assessmentId){
        var assessment = assessmentRepository.getReferenceById(assessmentId);

        return new AssessmentDetailsDTO(assessment);
    }

    @Transactional
    public AssessmentDetailsDTO update(
            Long assessmentId,
            UpdateAssessmentDTO assessmentDTO
    ){
        var assessment = assessmentRepository.getReferenceById(assessmentId);

        if(assessment.getRating() != null)
            assessment.setRating(assessmentDTO.rating());

        if(!assessment.getComment().isEmpty())
            assessment.setComment(assessmentDTO.comment());

        assessment.setUpdatedAt(LocalDateTime.now());

        return new AssessmentDetailsDTO(assessment);

    }

    @Transactional
    public void delete(Long assessmentId){
        assessmentRepository.deleteById(assessmentId);
    }

}
