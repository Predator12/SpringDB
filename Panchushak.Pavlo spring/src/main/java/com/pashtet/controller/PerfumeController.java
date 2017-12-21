package com.pashtet.controller;


import com.pashtet.DTO.PerfumeDTO;
import com.pashtet.domain.Perfume;
import com.pashtet.exceptions.ExistsPersonForBookException;
import com.pashtet.exceptions.NoSuchBookException;
import com.pashtet.exceptions.NoSuchPersonException;
import com.pashtet.service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class PerfumeController {
    @Autowired
    PerfumeService perfumeService;

    @GetMapping(value = "/api/perfume/prodavec/{prodavec_id}")
    public ResponseEntity<List<PerfumeDTO>> getPerfumesByProdavecID(@PathVariable Long prodavec_id) throws NoSuchPersonException, NoSuchBookException {
        Set<Perfume> perfumeList = perfumeService.getPerfumesByProdavecId(prodavec_id);
        Link link = linkTo(methodOn(PerfumeController.class).getAllPerfumes()).withSelfRel();

        List<PerfumeDTO> perfumesDTO = new ArrayList<>();
        for (Perfume entity : perfumeList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            PerfumeDTO dto = new PerfumeDTO(entity, selfLink);
            perfumesDTO.add(dto);
        }

        return new ResponseEntity<>(perfumesDTO, HttpStatus.OK);
    }


    @GetMapping(value = "/api/perfume/{perfume_id}")
    public ResponseEntity<PerfumeDTO> getPerfume(@PathVariable Long perfume_id) throws NoSuchBookException, NoSuchPersonException {
        Perfume perfume = perfumeService.getPerfume(perfume_id);
        Link link = linkTo(methodOn(PerfumeController.class).getPerfume(perfume_id)).withSelfRel();

        PerfumeDTO perfumeDTO = new PerfumeDTO(perfume, link);

        return new ResponseEntity<>(perfumeDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/perfume")
    public ResponseEntity<List<PerfumeDTO>> getAllPerfumes() throws NoSuchBookException, NoSuchPersonException {
        List<Perfume> perfumeList = perfumeService.getAllPerfumes();
        Link link = linkTo(methodOn(PerfumeController.class).getAllPerfumes()).withSelfRel();

        List<PerfumeDTO> carsDTO = new ArrayList<>();
        for (Perfume entity : perfumeList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            PerfumeDTO dto = new PerfumeDTO(entity, selfLink);
            carsDTO.add(dto);
        }

        return new ResponseEntity<>(carsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/perfume")
    public ResponseEntity<PerfumeDTO> addPerfume(@RequestBody Perfume newPerfume) throws NoSuchBookException, NoSuchPersonException {
        perfumeService.createPerfume(newPerfume);
        Link link = linkTo(methodOn(PerfumeController.class).getPerfume(newPerfume.getId())).withSelfRel();

        PerfumeDTO perfumeDTO = new PerfumeDTO(newPerfume, link);

        return new ResponseEntity<>(perfumeDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/perfume/{perfume_id}")
    public ResponseEntity<PerfumeDTO> updatePerfume(@RequestBody Perfume uPerfume, @PathVariable Long perfume_id) throws NoSuchBookException, NoSuchPersonException {
        perfumeService.updatePerfume(uPerfume, perfume_id);
        Perfume perfume = perfumeService.getPerfume(perfume_id);
        Link link = linkTo(methodOn(PerfumeController.class).getPerfume(perfume_id)).withSelfRel();

        PerfumeDTO perfumeDTO = new PerfumeDTO(perfume, link);

        return new ResponseEntity<>(perfumeDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/perfume/{perfume_id}")
    public  ResponseEntity deletePerfume(@PathVariable Long perfume_id) throws ExistsPersonForBookException, NoSuchBookException {
        perfumeService.deletePerfume(perfume_id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
