package com.example.firma.Repository;

import com.example.firma.Entity.Firma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FirmaRepository extends JpaRepository<Firma,Integer> {
    Optional<Firma> findByFirmaNomi(String nomi);
}
