package com.example.demo.service;

import com.example.demo.model.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICityService {

    Page<City> findAll(Pageable pageable);

    Page<City> findAllByName(String name, Pageable pageable);

    void save(City city);

    Optional<City> findCity(Long id);

//    Optional<City> findCityById(Long id);

    void delete(Long id);
}
