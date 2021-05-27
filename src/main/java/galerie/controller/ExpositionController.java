package galerie.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/exposition")
public class ExpositionController {

    private final ExpositionRepository dao;

    public ExpositionController(ExpositionRepository dao) {

        this.dao = dao;
    }


    @GetMapping(path = "chiffredAffaire")
    public @ResponseBody
    List<CaDeExposition> listeCA() {
        return dao.listeCA();
    }
}
