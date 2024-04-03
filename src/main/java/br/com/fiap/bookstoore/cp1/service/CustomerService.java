package br.com.fiap.bookstoore.cp1.service;

import br.com.fiap.bookstoore.cp1.dto.customer.CreateCustomerDTO;
import br.com.fiap.bookstoore.cp1.dto.customer.CustomerDetailsDTO;
import br.com.fiap.bookstoore.cp1.handler.InvalidEmailException;
import br.com.fiap.bookstoore.cp1.handler.WeakPasswordException;
import br.com.fiap.bookstoore.cp1.model.Customer;
import br.com.fiap.bookstoore.cp1.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(CreateCustomerDTO customerDTO) {
        var customer = new Customer(customerDTO);

        if(!validateStrongPassword(customer.getPassword()))
            throw new WeakPasswordException();

        if(!validateEmail(customer.getEmail()))
            throw new InvalidEmailException();

        return customerRepository.save(customer);
    }

    public List<CustomerDetailsDTO> getAll(Pageable pageable){
        var customerList = customerRepository.findAll(pageable)
                .stream().map(CustomerDetailsDTO::new).toList();;
        return customerList;
    }

    private Boolean validateStrongPassword(String password){
        String STRONG_PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(STRONG_PASSWORD_REGEX);
    }


    private Boolean validateEmail(String email){
        String EMAIL_REGEX = "^[a-zA-Z0-9._]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(EMAIL_REGEX);
    }

}
