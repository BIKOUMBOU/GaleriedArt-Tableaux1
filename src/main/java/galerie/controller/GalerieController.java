package galerie.controller;

import galerie.dao.GalerieRepository;
import galerie.entity.Galerie;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping(path = "/galerie")

public class GalerieController {

    private final GalerieRepository dao;

    public GalerieController(GalerieRepository dao) {
        this.dao = dao;
    }


    @GetMapping(path = "show")
    public String afficheToutesLesGaleries(Model model) {
        model.addAttribute("galeries", dao.findAll());
        return "afficheGaleries";
    }

    @GetMapping(path = "add")
    public String montreLeFormulairePourAjout(@ModelAttribute("galerie") Galerie galerie) {
        return "formulaireGalerie";
    }

    @PostMapping(path = "save")
    public String ajouteLaGaleriePuisMontreLaListe(Galerie galerie, RedirectAttributes redirectInfo) {
        String message;
        try {
            // cf. https://www.baeldung.com/spring-data-crud-repository-save
            dao.save(galerie);
            // Le code de la catégorie a été initialisé par la BD au moment de l'insertion
            message = "La galerie '" + galerie.getNom() + "' a été correctement enregistrée";
        } catch (DataIntegrityViolationException e) {
            // Les noms sont définis comme 'UNIQUE' 
            // En cas de doublon, JPA lève une exception de violation de contrainte d'intégrité
            message = "Erreur : La galerie '" + galerie.getNom() + "' existe déjà";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // POST-Redirect-GET : on se redirige vers l'affichage de la liste		
    }


    @GetMapping(path = "delete")
    public String supprimeUneCategoriePuisMontreLaListe(@RequestParam("id") Galerie galerie, RedirectAttributes redirectInfo) {
        String message = "La galerie '" + galerie.getNom() + "' a bien été supprimée";
        try {
            dao.delete(galerie); // Ici on peut avoir une erreur (Si il y a des expositions pour cette galerie par exemple)
        } catch (DataIntegrityViolationException e) {
            // violation de contrainte d'intégrité si on essaie de supprimer une galerie qui a des expositions
            message = "Erreur : Impossible de supprimer la galerie '" + galerie.getNom() + "', il faut d'abord supprimer ses expositions";
        }
        // RedirectAttributes permet de transmettre des informations lors d'une redirection,
        // Ici on transmet un message de succès ou d'erreur
        // Ce message est accessible et affiché dans la vue 'afficheGalerie.html'
        redirectInfo.addFlashAttribute("message", message);
        return "redirect:show"; // on se redirige vers l'affichage de la liste
    }
}
