package galerie.dao;

import galerie.dto.CaDeExposition;
import galerie.entity.Exposition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring 
public interface ExpositionRepository extends JpaRepository<Exposition, Integer> {

    @Query("SELECT SUM(t.prixVente) FROM Transaction t WHERE t.lieuDeVente.id = :id")

    Float chiffreAffairePourId(Integer id);

    @Query("SELECT SUM(t.prixVente) FROM Transaction t WHERE t.lieuDeVente.intitule = :intitule")
    Float chiffreAffairePourIntitule(String intitule);

    @Query("SELECT t.lieuDeVente.intitule as intitule, SUM(t.prixVente) as CA FROM Transaction t GROUP BY intitule")
    List<CaDeExposition> listeCA();
    
}
