package br.com.fiap.bookstoore.cp1.service;

import br.com.fiap.bookstoore.cp1.dto.adress.AdressDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.adress.CreateAdressDTO;
import br.com.fiap.bookstoore.cp1.dto.adress.UpdateAdressDTO;
import br.com.fiap.bookstoore.cp1.handler.InvalidAddressException;
import br.com.fiap.bookstoore.cp1.model.Adress;
import br.com.fiap.bookstoore.cp1.model.Customer;
import br.com.fiap.bookstoore.cp1.repository.AdressRepository;
import br.com.fiap.bookstoore.cp1.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdressService {

    @Autowired
    AdressRepository adressRepository;
    @Autowired
    CustomerRepository customerRepository;


    @Transactional
    public Adress create(Long customerId, CreateAdressDTO adressDTO){
        var customer = customerRepository.getReferenceById(customerId);
        var adress = new Adress(adressDTO);

        adress.setCustomer(customer);

        return adressRepository.save(adress);
    }

    public List<AdressDetailsDTO> getAll(Pageable pageable){
        var listAdress = adressRepository.findAll(pageable)
                .stream().map(AdressDetailsDTO::new).toList();
        return listAdress;
    }

    public AdressDetailsDTO getOne(Long id){
        var adress = adressRepository.getReferenceById(id);
        return new AdressDetailsDTO(adress);
    }

    //TODO: Pesquisar como posso paginar os resultados dos endereços de um cliente
    public List<AdressDetailsDTO> getAllByCustomerId(Pageable pageable, Long customerId){
        var customer = customerRepository.getReferenceById(customerId);
        var listAdress = customer.getAddresses()
                .stream().map(AdressDetailsDTO::new).toList();
        return listAdress;
    }

    @Transactional
    public AdressDetailsDTO update(
            Long adressId,
            Long customerId,
            UpdateAdressDTO adressDTO
    ){
        var customer = customerRepository.getReferenceById(customerId);
        var adress = adressRepository.getReferenceById(adressId);

        var customerAdressesIds = customer.getAddresses().stream()
                .map(Adress::getId)
                .collect(Collectors.toList());

        if(!customerAdressesIds.contains(adressId))
            throw new InvalidAddressException();

        if(adress.getStreet() != null)
            adress.setStreet(adressDTO.street());

        if(adress.getAdressNumber() != null)
            adress.setAdressNumber(adressDTO.adressNumber());

        if(adress.getCep() != null)
            adress.setCep(adressDTO.cep());

        adress.setUpdatedAt(LocalDateTime.now());

        adressRepository.save(adress);

        return new AdressDetailsDTO(adress);
    }

    @Transactional
    public void delete(Long id){
        adressRepository.deleteById(id);
    }

}
