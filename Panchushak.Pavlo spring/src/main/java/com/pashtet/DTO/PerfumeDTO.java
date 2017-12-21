package com.pashtet.DTO;

import com.pashtet.controller.ProdavecController;
import com.pashtet.domain.Perfume;
import com.pashtet.exceptions.NoSuchBookException;
import com.pashtet.exceptions.NoSuchPersonException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PerfumeDTO extends ResourceSupport {
    Perfume perfume;
    public PerfumeDTO(Perfume perfume, Link selfLink) throws NoSuchBookException, NoSuchPersonException {
        this.perfume = perfume;
        add(selfLink);
        add(linkTo(methodOn(ProdavecController.class).getProdavecsByPerfumeID(perfume.getId())).withRel("vlasnyks"));
    }

    public Long getPerfumeId() {
        return perfume.getId();
    }

    public String getPerfumename() {
        return perfume.getPerfumeName();
    }

    public String getAuthor() {
        return perfume.getAuthor();
    }

    public String getSeller() {
        return perfume.getSeller();
    }

    public Integer getYear_of_creating() {
        return perfume.getYear_of_creating();
    }

    public Integer getAmount() {
        return perfume.getAmount();
    }
}
