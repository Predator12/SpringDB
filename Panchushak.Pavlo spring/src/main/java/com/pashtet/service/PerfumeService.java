package com.pashtet.service;

import com.pashtet.Repository.PerfumeRepository;
import com.pashtet.Repository.ProdavecRepository;
import com.pashtet.domain.Perfume;
import com.pashtet.domain.Prodavec;
import com.pashtet.exceptions.ExistsPersonForBookException;
import com.pashtet.exceptions.NoSuchBookException;
import com.pashtet.exceptions.NoSuchPersonException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class PerfumeService {
    @Autowired
    PerfumeRepository perfumeRepository;

    @Autowired
    ProdavecRepository prodavecRepository;

    public Set<Perfume> getPerfumesByProdavecId(Long prodavec_id) throws NoSuchPersonException {
//        Prodavec prodavec = personRepository.findOne(prodavec_id);//1.5.9
        Prodavec prodavec = prodavecRepository.findById(prodavec_id).get();//2.0.0.M7
        if (prodavec == null) throw new NoSuchPersonException();
        return prodavec.getPerfumes();
    }

    public Perfume getPerfume(Long perfume_id) throws NoSuchBookException {
//        Perfume perfume = bookRepository.findOne(perfume_id);//1.5.9
        Perfume perfume = perfumeRepository.findById(perfume_id).get();//2.0.0.M7
        if (perfume == null) throw new NoSuchBookException();
        return perfume;
    }

    public List<Perfume> getAllPerfumes() {
        return perfumeRepository.findAll();
    }

    @Transactional
    public void createPerfume(Perfume perfume) {
        perfumeRepository.save(perfume);
    }

    @Transactional
    public void updatePerfume(Perfume uPerfume, Long perfume_id) throws NoSuchBookException {
//        Perfume perfume = bookRepository.findOne(perfume_id);//1.5.9
        Perfume perfume = perfumeRepository.findById(perfume_id).get();//2.0.0.M7
        if (perfume == null) throw new NoSuchBookException();
        //update
        perfume.setPerfumeName(uPerfume.getPerfumeName());
        perfume.setAuthor(uPerfume.getAuthor());
        perfume.setSeller(uPerfume.getSeller());
        perfume.setYear_of_creating(uPerfume.getYear_of_creating());
        perfume.setAmount(uPerfume.getAmount());
    }

    @Transactional
    public void deletePerfume(Long perfume_id) throws NoSuchBookException, ExistsPersonForBookException {
//        Perfume perfume = bookRepository.findOne(perfume_id);//1.5.9
        Perfume perfume = perfumeRepository.findById(perfume_id).get();//2.0.0.M7

        if (perfume == null) throw new NoSuchBookException();
        if (perfume.getProdavecs().size() != 0) throw new ExistsPersonForBookException();
        perfumeRepository.delete(perfume);
    }
}
