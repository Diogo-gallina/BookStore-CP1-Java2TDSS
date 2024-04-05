package br.com.fiap.bookstoore.cp1.controller;

import br.com.fiap.bookstoore.cp1.dto.adress.AdressDetailsDTO;
import br.com.fiap.bookstoore.cp1.dto.adress.CreateAdressDTO;
import br.com.fiap.bookstoore.cp1.dto.adress.UpdateAdressDTO;
import br.com.fiap.bookstoore.cp1.service.AdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/adresses")
public class AdressController {

    @Autowired
    AdressService adressService;

    @PostMapping("{customer_id}")
    public ResponseEntity<AdressDetailsDTO> create(
            @PathVariable("customer_id") Long customerId,
            @RequestBody CreateAdressDTO adressDTO,
            UriComponentsBuilder uriBuilder
    ){
        var adress = adressService.create(customerId, adressDTO);
        var url = uriBuilder.path("adresses/{customer_id}").buildAndExpand(adress.getId()).toUri();

        return ResponseEntity.created(url).body(new AdressDetailsDTO(adress));
    }

    @GetMapping
    public ResponseEntity<List<AdressDetailsDTO>> getAllAdresses(Pageable pageable){
        var adressList = adressService.getAll(pageable);
        return ResponseEntity.ok(adressList);
    }

    @GetMapping("{adress_id}")
    public ResponseEntity<AdressDetailsDTO> getOneAdress(
            @PathVariable("adress_id") Long adressId
    ){
        var adress = adressService.getOne(adressId);
        return ResponseEntity.ok(adress);
    }

    @GetMapping("customer/{customer_id}")
    public ResponseEntity<List<AdressDetailsDTO>> getAllAdressesByCustomer(
            @PathVariable("customer_id") Long customerId,
            Pageable pageable
    ){
        var adressList = adressService.getAllByCustomerId(pageable, customerId);
        return ResponseEntity.ok(adressList);
    }

    @PutMapping("customer/{customer_id}/adress/{adress_id}")
    public ResponseEntity<AdressDetailsDTO> update(
            @PathVariable("adress_id") Long adressId,
            @PathVariable("customer_id") Long customerId,
            @RequestBody UpdateAdressDTO adressDTO
    ){
        var adress = adressService.update(adressId, customerId, adressDTO);
        return ResponseEntity.ok(adress);
    }


    @DeleteMapping("{adress_id}")
    public ResponseEntity<Void> delete(
            @PathVariable("adress_id") Long adressId
    ){
        adressService.delete(adressId);
        return ResponseEntity.noContent().build();
    }
}
