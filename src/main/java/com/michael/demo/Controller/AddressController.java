package com.michael.demo.Controller;

import com.michael.demo.dto.AddressDto;
import com.michael.demo.model.Address;
import com.michael.demo.service.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressServiceImpl addressService;

    @GetMapping
    public List<Address> getAllAddress(){
        return addressService.getAllAddress();
    }

    @GetMapping("/{addressId}")
    public Optional<Address> getAddressById(@PathVariable Long addressId){
        return addressService.findAddressById(addressId);
    }

    @PostMapping
    public ResponseEntity <String> createAddress(@RequestBody AddressDto addressDto){
        addressService.createAddress(addressDto);
        return  new ResponseEntity<>("Address added successfully", HttpStatus.OK);
    }



}
