package com.example.firma.Repository;

import com.example.firma.Entity.Manzil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManzilRepository extends JpaRepository<Manzil,Integer> {
}
