package com.pashtet.Repository;

import com.pashtet.domain.Prodavec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdavecRepository extends JpaRepository<Prodavec, Long> {
}
