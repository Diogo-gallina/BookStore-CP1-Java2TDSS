package br.com.fiap.bookstoore.cp1.controller;

import br.com.fiap.bookstoore.cp1.dto.customer.CreateCustomerDTO;
import br.com.fiap.bookstoore.cp1.dto.customer.CustomerDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.customer.UpdateCustomerDTO;
import br.com.fiap.bookstoore.cp1.model.Customer;
import br.com.fiap.bookstoore.cp1.service.CustomerService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDetailsDTO> create(
            @RequestBody CreateCustomerDTO customerDTO,
            UriComponentsBuilder uriBuilder
    ) {
        Customer customer = customerService.createCustomer(customerDTO);
        var url = uriBuilder.path("customers/{id}").buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(url).body(new CustomerDetailsDTO(customer));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDetailsDTO>> findAll(Pageable pageable) {
        var customerList = customerService.getAll(pageable);
        return ResponseEntity.ok(customerList);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<CustomerDetailsDTO> findOne(@PathVariable("id") Long id){
        var customer = customerService.getOne(id);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("{id}")
    public ResponseEntity<CustomerDetailsDTO> update(
            @PathVariable("id") Long id,
            @RequestBody UpdateCustomerDTO customerDTO
    ){
        var customer = customerService.update(id, customerDTO);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

