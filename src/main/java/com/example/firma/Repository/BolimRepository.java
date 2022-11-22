package com.example.firma.Repository;

import com.example.firma.Entity.Bolim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BolimRepository extends JpaRepository<Bolim,Integer> {
    Optional <Bolim> findByNomiAndFirmaId(String nomi,Integer id);
    Optional<Bolim> findByIdAndFirmaId(Integer id,Integer firmaId);
}
