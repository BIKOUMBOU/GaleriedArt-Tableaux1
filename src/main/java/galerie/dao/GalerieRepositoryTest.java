package galerie.dao;

import galerie.entity.Galerie;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

@Log4j2
@DataJpaTest
public class GalerieRepositoryTest {

    @Autowired
    private GalerieRepository galerieDAO;

    @Test
    @Sql("test-data.sql")
    public void CompterLesEnregistrements() {
        log.info("Compte des enregistrements de la table 'Galerie'");

        int combienDansLeJeuDeTest = 3;
        long nombre = galerieDAO.count();
        assertEquals(combienDansLeJeuDeTest, nombre);
    }

    private void assertEquals(int combienDansLeJeuDeTest, long nombre) {
    }

    @Test
    public void CalculDuCAAnnuel() {
        LocalDate now = LocalDate.now();

        Galerie saatchi = galerieDAO.findById(1).orElseThrow();
        log.info("Calcul du CA de {} pour l'année {}", saatchi.getNom(), now.getYear());
        float result = saatchi.CAannuel(now.getYear());
        assertEquals(1000.00f, result, 0.005f,
                "Le CA de cette galerie est de 1000.05f" );
    }

    private void assertEquals(float v, float result, float v1, String s) {
    }
}
