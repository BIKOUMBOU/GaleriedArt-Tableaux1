package galerie.dao;

import galerie.entity.Artiste;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring 

public interface ArtisteRepository extends JpaRepository<Artiste, Integer> {

}
