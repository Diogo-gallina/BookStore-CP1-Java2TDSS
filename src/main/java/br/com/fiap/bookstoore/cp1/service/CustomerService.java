package br.com.fiap.bookstoore.cp1.service;

import br.com.fiap.bookstoore.cp1.dto.customer.CreateCustomerDTO;
import br.com.fiap.bookstoore.cp1.model.Customer;
import br.com.fiap.bookstoore.cp1.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(CreateCustomerDTO customerDTO) {
        Customer customer = new Customer(customerDTO);
        return customerRepository.save(customer);
    }

}
