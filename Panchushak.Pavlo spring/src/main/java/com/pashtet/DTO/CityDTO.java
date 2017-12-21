package com.pashtet.DTO;

import com.pashtet.controller.ProdavecController;
import com.pashtet.domain.City;
import com.pashtet.exceptions.NoSuchBookException;
import com.pashtet.exceptions.NoSuchCityException;
import com.pashtet.exceptions.NoSuchPersonException;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class CityDTO extends ResourceSupport {
    City city;
    public CityDTO(City city, Link selfLink) throws NoSuchPersonException, NoSuchBookException, NoSuchCityException {
        this.city=city;
        add(selfLink);
        add(linkTo(methodOn(ProdavecController.class).getProdavecsByCityID(city.getId())).withRel("vlasnyks"));
    }

    public Long getCityId() { return city.getId(); }

    public String getCity() {
        return city.getCity();
    }
}
