package com.pashtet.controller;

import com.pashtet.DTO.ProdavecDTO;
import com.pashtet.domain.Prodavec;
import com.pashtet.exceptions.*;
import com.pashtet.service.ProdavecService;
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
public class ProdavecController {
    @Autowired
    ProdavecService prodavecService;

    @GetMapping(value = "/api/prodavec/city/{city_id}")
    public ResponseEntity<List<ProdavecDTO>> getProdavecsByCityID(@PathVariable Long city_id) throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        List<Prodavec> prodavecList = prodavecService.getProdavecByCityId(city_id);

        Link link = linkTo(methodOn(ProdavecController.class).getAllProdavecs()).withSelfRel();

        List<ProdavecDTO> prodavecsDTO = new ArrayList<>();
        for (Prodavec entity : prodavecList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProdavecDTO dto = new ProdavecDTO(entity, selfLink);
            prodavecsDTO.add(dto);
        }

        return new ResponseEntity<>(prodavecsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/prodavec/{prodavec_id}")
    public ResponseEntity<ProdavecDTO> getProdavec(@PathVariable Long prodavec_id) throws NoSuchPersonException, NoSuchBookException {
        Prodavec prodavec = prodavecService.getProdavec(prodavec_id);
        Link link = linkTo(methodOn(ProdavecController.class).getProdavec(prodavec_id)).withSelfRel();

        ProdavecDTO prodavecDTO = new ProdavecDTO(prodavec, link);

       return new ResponseEntity<>(prodavecDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/prodavec")
    public ResponseEntity<List<ProdavecDTO>> getAllProdavecs() throws NoSuchPersonException, NoSuchBookException {
        List<Prodavec> prodavecList = prodavecService.getAllProdavecs();
        Link link = linkTo(methodOn(ProdavecController.class).getAllProdavecs()).withSelfRel();

        List<ProdavecDTO> prodavecsDTO = new ArrayList<>();
        for (Prodavec entity : prodavecList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProdavecDTO dto = new ProdavecDTO(entity, selfLink);
            prodavecsDTO.add(dto);
        }

        return new ResponseEntity<>(prodavecsDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/api/prodavec/perfume/{perfume_id}")
    public ResponseEntity<List<ProdavecDTO>> getProdavecsByPerfumeID(@PathVariable Long perfume_id) throws NoSuchBookException, NoSuchPersonException {
        Set<Prodavec> prodavecList = prodavecService.getProdavecsByPerfumeId(perfume_id);
        Link link = linkTo(methodOn(ProdavecController.class).getAllProdavecs()).withSelfRel();

        List<ProdavecDTO> prodavecsDTO = new ArrayList<>();
        for (Prodavec entity : prodavecList) {
            Link selfLink = new Link(link.getHref() + "/" + entity.getId()).withSelfRel();
            ProdavecDTO dto = new ProdavecDTO(entity, selfLink);
            prodavecsDTO.add(dto);
        }

        return new ResponseEntity<>(prodavecsDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/api/prodavec/city/{city_id}")
    public  ResponseEntity<ProdavecDTO> addProdavec(@RequestBody Prodavec newProdavec, @PathVariable Long city_id)
            throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        prodavecService.createProdavec(newProdavec, city_id);
        Link link = linkTo(methodOn(ProdavecController.class).getProdavec(newProdavec.getId())).withSelfRel();

        ProdavecDTO prodavecDTO = new ProdavecDTO(newProdavec, link);

        return new ResponseEntity<>(prodavecDTO, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/prodavec/{prodavec_id}/city/{city_id}")
    public  ResponseEntity<ProdavecDTO> updateProdavec(@RequestBody Prodavec uProdavec,
                                                      @PathVariable Long prodavec_id, @PathVariable Long city_id)
            throws NoSuchCityException, NoSuchPersonException, NoSuchBookException {
        prodavecService.updateProdavec(uProdavec, prodavec_id, city_id);
        Prodavec prodavec = prodavecService.getProdavec(prodavec_id);
        Link link = linkTo(methodOn(ProdavecController.class).getProdavec(prodavec_id)).withSelfRel();

        ProdavecDTO prodavecDTO = new ProdavecDTO(prodavec, link);

        return new ResponseEntity<>(prodavecDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/prodavec/{prodavec_id}")
    public  ResponseEntity deleteProdavec(@PathVariable Long prodavec_id) throws NoSuchPersonException, ExistsBooksForPersonException {
        prodavecService.deleteProdavec(prodavec_id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/api/prodavec/{prodavec_id}/perfume/{perfume_id}")
    public  ResponseEntity<ProdavecDTO> addPerfumeForProdavec(@PathVariable Long prodavec_id, @PathVariable Long perfume_id)
            throws NoSuchPersonException, NoSuchBookException, AlreadyExistsBookInPersonException, BookAbsentException {
        prodavecService.addPerfumeForProdavec(prodavec_id,perfume_id);
        Prodavec prodavec = prodavecService.getProdavec(prodavec_id);
        Link link = linkTo(methodOn(ProdavecController.class).getProdavec(prodavec_id)).withSelfRel();
        ProdavecDTO prodavecDTO = new ProdavecDTO(prodavec, link);
        return new ResponseEntity<>(prodavecDTO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/prodavec/{prodavec_id}/perfume/{perfume_id}")
    public  ResponseEntity<ProdavecDTO> removePerfumeForProdavec(@PathVariable Long prodavec_id, @PathVariable Long perfume_id)
            throws NoSuchPersonException, NoSuchBookException, PersonHasNotBookException {
        prodavecService.removePerfumeForProdavec(prodavec_id,perfume_id);
        Prodavec prodavec = prodavecService.getProdavec(prodavec_id);
        Link link = linkTo(methodOn(ProdavecController.class).getProdavec(prodavec_id)).withSelfRel();

        ProdavecDTO prodavecDTO = new ProdavecDTO(prodavec, link);

        return new ResponseEntity<>(prodavecDTO, HttpStatus.OK);
    }

}
