package com.example.demo.controller;

import com.example.demo.model.City;
import com.example.demo.model.Country;
import com.example.demo.service.ICityService;
import com.example.demo.service.impl.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/city")
public class CityController {

        @Autowired
        private ICityService iCityService;

        @Autowired
        private CountryService iCountryService;

        @ModelAttribute("countries")
        public Iterable<Country> findAll(){
            return iCountryService.findAll();
        }

        @GetMapping("")
        public ModelAndView showAll(@PageableDefault(value = 3) Pageable pageable,
                                    @RequestParam Optional<String> search) {
            ModelAndView modelAndView = new ModelAndView("list");
            Page<City> cities;
            if (search.isPresent()) {
                cities = iCityService.findAllByName(search.get(), pageable);
                modelAndView.addObject("search", search.get());
            } else {
                cities = iCityService.findAll(pageable);
            }
            modelAndView.addObject("cities", cities);
            return modelAndView;
        }

        @PostMapping("/create")
        public ModelAndView saveCity(@ModelAttribute("city") City city, BindingResult bindingResult,
                                     @SortDefault(sort = {"id"}) Pageable pageable){
            if(bindingResult.hasErrors()){
                ModelAndView modelAndView = new ModelAndView("create");
                modelAndView.addObject("city", city);
                return modelAndView;
            }
            iCityService.save(city);
            Page<City> cities = iCityService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("list");
            modelAndView.addObject("cities", cities);
            modelAndView.addObject("success");
            return modelAndView;
        }

        @GetMapping("/create")
        public ModelAndView createCity(){
            ModelAndView modelAndView = new ModelAndView("create");
            modelAndView.addObject("city", new City());
            return modelAndView;
        }

        @GetMapping("/edit/{id}")
        public ModelAndView editCity(@PathVariable("id") Long id){
            Optional<City> city = iCityService.findCity(id);
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("city", city);
            return modelAndView;
        }

        @PostMapping("/edit/{id}")
        public ModelAndView updateCity(@ModelAttribute("city") City city, BindingResult bindingResult,
                                       @SortDefault(sort = {"id"}) Pageable pageable){
            if(bindingResult.hasErrors()){
                ModelAndView modelAndView = new ModelAndView("edit");
                modelAndView.addObject("city", city);
                return modelAndView;
            }
            iCityService.save(city);
            Page<City> cities = iCityService.findAll(pageable);
            ModelAndView modelAndView = new ModelAndView("list");
            modelAndView.addObject("cities", cities);
            modelAndView.addObject("success");
            return modelAndView;
        }
        @GetMapping("/delete/{id}")
        public ModelAndView deleteCity(@PathVariable("id") Long id, @SortDefault(sort = {"id"}) Pageable pageable){
            iCityService.delete(id);
            ModelAndView modelAndView = new ModelAndView("list");
            Page<City> cities = iCityService.findAll(pageable);
            modelAndView.addObject("cities", cities);
            modelAndView.addObject("success");
            return modelAndView;
        }

        @GetMapping("/view/{id}")
        public ModelAndView viewCity(@PathVariable("id") Long id){
            ModelAndView modelAndView = new ModelAndView("view");
            Optional<City> city = iCityService.findCity(id);
            modelAndView.addObject(city);
            return modelAndView;
        }





}
