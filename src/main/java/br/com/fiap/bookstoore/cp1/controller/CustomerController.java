package br.com.fiap.bookstoore.cp1.controller;

import br.com.fiap.bookstoore.cp1.dto.customer.CreateCustomerDTO;
import br.com.fiap.bookstoore.cp1.dto.customer.CustomerDetailsDTO;
import br.com.fiap.bookstoore.cp1.model.Customer;
import br.com.fiap.bookstoore.cp1.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDetailsDTO> create(@RequestBody CreateCustomerDTO customerDTO, UriComponentsBuilder uriBuilder) {
        Customer customer = customerService.createCustomer(customerDTO);
        var url = uriBuilder.buildAndExpand("/customer").toUri();
        return ResponseEntity.created(url).body(new CustomerDetailsDTO(customer));
    }

}
