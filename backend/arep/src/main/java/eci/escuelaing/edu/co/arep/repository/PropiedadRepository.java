package eci.escuelaing.edu.co.arep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eci.escuelaing.edu.co.arep.model.Propiedad;

@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Integer> {
}
