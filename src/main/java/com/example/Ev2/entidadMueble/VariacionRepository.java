package com.example.Ev2.entidadMueble;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariacionRepository extends JpaRepository<Variacion, Long> {
}