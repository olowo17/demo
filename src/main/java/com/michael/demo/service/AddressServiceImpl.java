package com.michael.demo.service;

import com.michael.demo.dto.AddressDto;
import com.michael.demo.model.Address;
import com.michael.demo.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements  AddressService{
    @Autowired
    AddressRepository addressRepository;
    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findAddressById(Long addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    public void createAddress(AddressDto addressDto) {
       Address newAddress = new Address();
       newAddress.setState(addressDto.state());
       newAddress.setStreet(addressDto.street());
       newAddress.setHouseNumber(addressDto.houseNumber());

       addressRepository.save(newAddress);

    }
}
