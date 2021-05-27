package galerie.dao;

import galerie.entity.Exposition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.test.context.jdbc.Sql;

@Log4j2 //
@DataJpaTest
public class ExpositionRepositoryTest {

    @Autowired
    private ExpositionRepository expositionDAO;

    @Test // en JAVA
    public void CalculDuCADuneExpositionEnJava() {
        Exposition painters = expositionDAO.findById(1).orElseThrow();
        log.info("Faire le calcule du CA de l'exposition {} en java", painters.getIntitule());
        assertEquals(45000.00f, painters.CA(), 0.02f,
                "Pour cette exposition, le CA est de : 45000.00f" );
    }

    @Test // en JPQL
    public void CalculDuCADuneExpositionEnJPQL() {
        int idExposition = 1;
        log.info("Faire le calcul du CA de l'exposition {} en JPQL", idExposition);
        assertEquals(45000.00f, expositionDAO.chiffreAffairePourId(idExposition), 0.02f,
                "Pour cette exposition, le CA est de : 45000.00f" );
    }

    @Test //
    public void CalculDuCADuneExpositionAvecJointure() {
        String intitule = "Painters' painters";
        log.info("On calcul le CA de l'exposition {} en JPQL", intitule);
        assertEquals(45000.00f, expositionDAO.chiffreAffairePourIntitule(intitule), 0.02f,
                " Pour cette exposition, le CA est de : 45000.00f" );
    }

    @Test //
    public void renvoieNullSiEnregistrement=0() {
        int idExposition = 001;
        log.info("Faire le calcul du CA de l'exposition  {} en JPQL", idExposition);
        assertNull(expositionDAO.chiffreAffairePourId(idExposition),
                "Il n'existe aucune exposition" );
    }

}
