package com.michael.demo.service;

import com.michael.demo.dto.AddressDto;
import com.michael.demo.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface AddressService {

    public List<Address> getAllAddress();
    public Optional <Address> findAddressById (Long addressId);
    public  void createAddress(AddressDto addressDto);


}
