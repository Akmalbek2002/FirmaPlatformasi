package com.example.firma.Repository;

import com.example.firma.Entity.Ishchi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IshchiRepository extends JpaRepository<Ishchi,Integer> {
    Optional<Ishchi> findByTelRaqam(String telRaqam);
}
