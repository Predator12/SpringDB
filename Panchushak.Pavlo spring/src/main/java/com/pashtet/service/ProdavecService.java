package com.pashtet.service;

import com.pashtet.Repository.PerfumeRepository;
import com.pashtet.Repository.CityRepository;
import com.pashtet.Repository.ProdavecRepository;
import com.pashtet.domain.Perfume;
import com.pashtet.domain.City;
import com.pashtet.domain.Prodavec;
import com.pashtet.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ProdavecService {
    @Autowired
    ProdavecRepository prodavecRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    PerfumeRepository perfumeRepository;

    public List<Prodavec> getProdavecByCityId(Long city_id) throws NoSuchCityException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7
        if (city == null) throw new NoSuchCityException();
        return city.getProdavecs();
    }

    public Prodavec getProdavec(Long prodavec_id) throws NoSuchPersonException {
//        Prodavec prodavec = personRepository.findOne(prodavec_id);//1.5.9
        Prodavec prodavec = prodavecRepository.findById(prodavec_id).get();//2.0.0.M7
        if (prodavec == null) throw new NoSuchPersonException();
        return prodavec;
    }

    public List<Prodavec> getAllProdavecs() {
        return prodavecRepository.findAll();
    }

    public Set<Prodavec> getProdavecsByPerfumeId(Long perfume_id) throws NoSuchBookException {
//        Perfume perfume = bookRepository.findOne(perfume_id);//1.5.9
        Perfume perfume = perfumeRepository.findById(perfume_id).get();//2.0.0.M7
        if (perfume == null) throw new NoSuchBookException();
        return perfume.getProdavecs();
    }

    @Transactional
    public void createProdavec(Prodavec prodavec, Long city_id) throws NoSuchCityException {
        if (city_id > 0) {
//            City city = cityRepository.findOne(city_id);//1.5.9
            City city = cityRepository.findById(city_id).get();//2.0.0.M7
            if (city == null) throw new NoSuchCityException();
            prodavec.setCity(city);
        }
        prodavecRepository.save(prodavec);
    }

    @Transactional
    public void updateProdavec(Prodavec uProdavec, Long prodavec_id, Long city_id) throws NoSuchCityException, NoSuchPersonException {
//        City city = cityRepository.findOne(city_id);//1.5.9
        City city = cityRepository.findById(city_id).get();//2.0.0.M7
        if (city_id > 0) {
            if (city == null) throw new NoSuchCityException();
        }
//        Prodavec prodavec = personRepository.findOne(prodavec_id);//1.5.9
        Prodavec prodavec = prodavecRepository.findById(prodavec_id).get();//2.0.0.M7
        if (prodavec == null) throw new NoSuchPersonException();
        //update
        prodavec.setSurname(uProdavec.getSurname());
        prodavec.setName(uProdavec.getName());
        prodavec.setEmail(uProdavec.getEmail());
        if (city_id > 0) prodavec.setCity(city);
        else prodavec.setCity(null);
        prodavec.setStreet(uProdavec.getStreet());
        prodavec.setPerfumename(uProdavec.getPerfumename());
        prodavecRepository.save(prodavec);
    }

    @Transactional
    public void deleteProdavec(Long prodavec_id) throws NoSuchPersonException, ExistsBooksForPersonException {
//        Prodavec prodavec = personRepository.findOne(prodavec_id);//1.5.9
        Prodavec prodavec = prodavecRepository.findById(prodavec_id).get();//2.0.0.M7
        if (prodavec == null) throw new NoSuchPersonException();
        if (prodavec.getPerfumes().size() != 0) throw new ExistsBooksForPersonException();
        prodavecRepository.delete(prodavec);
    }

    @Transactional
    public void addPerfumeForProdavec(Long prodavec_id, Long perfume_id)
            throws NoSuchPersonException, NoSuchBookException, AlreadyExistsBookInPersonException, BookAbsentException {
//        Prodavec prodavec = personRepository.findOne(prodavec_id);//1.5.9
        Prodavec prodavec = prodavecRepository.findById(prodavec_id).get();//2.0.0.M7
        if (prodavec == null) throw new NoSuchPersonException();
//        Perfume perfume = bookRepository.findOne(perfume_id);//1.5.9
        Perfume perfume = perfumeRepository.findById(perfume_id).get();//2.0.0.M7
        if (perfume == null) throw new NoSuchBookException();
        if (prodavec.getPerfumes().contains(perfume) == true) throw new AlreadyExistsBookInPersonException();
        if (perfume.getAmount() <= perfume.getProdavecs().size()) throw new BookAbsentException();
        prodavec.getPerfumes().add(perfume);
        prodavecRepository.save(prodavec);
    }

    @Transactional
    public void removePerfumeForProdavec(Long prodavec_id, Long perfume_id)
            throws NoSuchPersonException, NoSuchBookException, PersonHasNotBookException {
//        Prodavec prodavec = personRepository.findOne(prodavec_id);//1.5.9
        Prodavec prodavec = prodavecRepository.findById(prodavec_id).get();//2.0.0.M7
        if (prodavec == null) throw new NoSuchPersonException();
//        Perfume perfume = bookRepository.findOne(perfume_id);//1.5.9
        Perfume perfume = perfumeRepository.findById(perfume_id).get();//2.0.0.M7
        if (perfume == null) throw new NoSuchBookException();
        if (prodavec.getPerfumes().contains(perfume) == false) throw new PersonHasNotBookException();
        prodavec.getPerfumes().remove(perfume);
        prodavecRepository.save(prodavec);
    }
}
