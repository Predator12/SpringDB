package com.pashtet.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "prodavec")
public class Prodavec {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prodavec_id", nullable = false)
    private Long id;
    @Column(name = "surname", nullable = false, length = 25)
    private String surname;
    @Column(name = "name", nullable = false, length = 25)
    private String name;
    @Column(name = "email", nullable = true, length = 45)
    private String email;
    @Column(name = "street", nullable = true, length = 30)
    private String street;
    @Column(name = "pefrumename", nullable = true, length = 10)
    private String perfumename;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;
    @ManyToMany
    @JoinTable(name = "prodavec_perfume",
            joinColumns = @JoinColumn(name = "prodavec_id", referencedColumnName = "prodavec_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "perfume_id", referencedColumnName = "perfume_id", nullable = false))
    private Set<Perfume> perfumes;

    Prodavec(){}
    Prodavec(String surname, String name, String email, String street, String perfumename){
        this.surname=surname;
        this.name=name;
        this.email=email;
        this.street=street;
        this.perfumename=perfumename;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long idProdavec) {
        this.id = idProdavec;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getPerfumename() {
        return perfumename;
    }
    public void setPerfumename(String perfumename) {
        this.perfumename = perfumename;
    }

    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }

    public Set<Perfume> getPerfumes() {
        return perfumes;
    }
    public void setPerfumes(Set<Perfume> perfumes) {
        this.perfumes = perfumes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodavec that = (Prodavec) o;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (perfumename != null ? !perfumename.equals(that.perfumename) : that.perfumename != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (perfumename != null ? perfumename.hashCode() : 0);
        return result;
    }
}
