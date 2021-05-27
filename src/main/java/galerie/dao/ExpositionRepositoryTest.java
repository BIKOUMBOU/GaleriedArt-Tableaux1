package galerie.dao;

import galerie.entity.Exposition;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@Log4j2

@DataJpaTest
public class ExpositionRepositoryTest {

    private void assertEquals(float v, float ca, float v1, String s) {
    }

    private void assertNull(Float chiffreAffairePourId, String s) {
    }


    @Autowired
    private ExpositionRepository expositionDAO;

    @Test
    public void CalculeDuCADuneExpositionEnJava () {
        Exposition painters = expositionDAO.findById(1).orElseThrow();
        log.info("Calcul du CA de l'exposition {}", painters.getIntitule());
        assertEquals(2000.08f, painters.CA(), 0.001f,
                "Le CA de cette exposition est de 2000.08f");
    }

    @Test
    public void CalculeDuCADuneExpositionEnJPQL() {
        int idExposition = 1;
        log.info("Calcul du CA de l'exposition {} ", idExposition);
        assertEquals(2000.08f, expositionDAO.chiffreAffairePourId(idExposition), 0.001f,
                "Le CA de cette exposition est de 2000.08f" );
    }


    @Test
    public void CalculDuCADuneExpositionAvecJointure() {
        String intitule = "Painters' painters";
        log.info("Calcul le CA de l'exposition {} en JPQL", intitule);
        assertEquals(2000.08f, expositionDAO.chiffreAffairePourIntitule(intitule), 0.001f,
                "Le CA de cette exposition est de 2000.08f" );
    }


    @Test
    public void NullSiPasDEnregistrement() {
        int idExposition = 999;
        log.info("Calcul Du CA de l'exposition {} en JPQL", idExposition);
        assertNull(expositionDAO.chiffreAffairePourId(idExposition),
            "Cette expo n'existe pas, le résultat est null, et non pas 0.0f" );
    }


}
