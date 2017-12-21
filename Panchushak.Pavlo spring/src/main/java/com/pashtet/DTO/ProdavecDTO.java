package com.pashtet.DTO;

import com.pashtet.controller.PerfumeController;
import com.pashtet.domain.Prodavec;
import com.pashtet.exceptions.NoSuchBookException;
import com.pashtet.exceptions.NoSuchPersonException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProdavecDTO extends ResourceSupport {
    Prodavec prodavec;
    public ProdavecDTO(Prodavec prodavec, Link selfLink) throws NoSuchPersonException, NoSuchBookException {
        this.prodavec = prodavec;
        add(selfLink);
        add(linkTo(methodOn(PerfumeController.class).getPerfumesByProdavecID(prodavec.getId())).withRel("avtosalons"));
    }

    public Long getProdavecId() {
        return prodavec.getId();
    }

    public String getSurname() {
        return prodavec.getSurname();
    }

    public String getName() {
        return prodavec.getName();
    }

    public String getEmail() {
        return prodavec.getEmail();
    }

    public String getCity() {
        if(prodavec.getCity()==null) return "";
        return prodavec.getCity().getCity();
    }

    public String getStreet() {
        return prodavec.getStreet();
    }

    public String getPerfumename() {
        return prodavec.getPerfumename();
    }
}
