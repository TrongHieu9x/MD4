package com.example.demo.service.impl;

import com.example.demo.model.City;
import com.example.demo.repository.ICityRepository;
import com.example.demo.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServicce implements ICityService {

    @Autowired
    private ICityRepository iCityRepository;

    @Override
    public Page<City> findAll(Pageable pageable) {
        return iCityRepository.findAll(pageable);
    }

    @Override
    public Page<City> findAllByName(String name, Pageable pageable) {
        return iCityRepository.findAllByNameContaining(name, pageable);
    }

    @Override
    public Optional<City> findCity(Long id) {
        return iCityRepository.findById(id);
    }


    @Override
    public void save(City city) {
        iCityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        iCityRepository.deleteById(id);
    }
}
