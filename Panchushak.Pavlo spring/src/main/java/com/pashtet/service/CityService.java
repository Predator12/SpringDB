package com.pashtet.service;

import com.pashtet.Repository.CityRepository;
import com.pashtet.Repository.ProdavecRepository;
import com.pashtet.domain.City;
import com.pashtet.exceptions.ExistsPersonsForCityException;
import com.pashtet.exceptions.NoSuchCityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;
    private boolean ascending;

    @Autowired
    ProdavecRepository prodavecRepository;

    public List<City> getAllCity() {
        return cityRepository.findAll();
    }

    public City getCity(Long city_id) throws NoSuchCityException {
//        City city =cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7
        if (city == null) throw new NoSuchCityException();
        return city;
    }

    @Transactional
    public void createCity(City city) {
        cityRepository.save(city);
    }

    @Transactional
    public void updateCity(City uCity, Long city_id) throws NoSuchCityException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7

        if (city == null) throw new NoSuchCityException();
        city.setCity(uCity.getCity());
        cityRepository.save(city);
    }

    @Transactional
    public void deleteCity(Long city_id) throws NoSuchCityException, ExistsPersonsForCityException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7
        if (city == null) throw new NoSuchCityException();
        if (city.getProdavecs().size() != 0) throw new ExistsPersonsForCityException();
        cityRepository.delete(city);
    }
}
