package net.sancfis.springhibernate_project_perform.web;

import net.sancfis.springhibernate_project_perform.dao.EtudiantRepository;
import net.sancfis.springhibernate_project_perform.entities.Etudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping(value = "/Etudiant")
public class EtudiantController {

    //Pour injecter un object
    @Autowired
    private EtudiantRepository etudiantRepository;

    
    @RequestMapping(value = "/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int p,
                        @RequestParam(name = "motCle", defaultValue = "") String mc){

        /* afficher la list des etudiants page par page */
        Page<Etudiant> pageEtudiants = etudiantRepository.chercherEtudiants
                ("%"+mc+"%", PageRequest.of(p, 10));

        /* afficher la totalite des page sur la page web */
        int pagesCount = pageEtudiants.getTotalPages();

        /* creer un tableau de page et attribuer un numero a chaque page */
        int[] pages = new int[pagesCount];
        for(int i = 0; i < pagesCount; i++) pages[i] = i;

        model.addAttribute("pages", pages);
        model.addAttribute("pageEtudiants", pageEtudiants);
        model.addAttribute("pageCourante", p);
        model.addAttribute("motCle", mc);
        return  "etudiants";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String formEtudiant(Model model){
        model.addAttribute("etudiant", new Etudiant());
        return "FormEtudiant";
    }

    @RequestMapping(value = "/saveEtudiant", method = RequestMethod.POST)
    public String save(@Valid Etudiant et,
                       BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()){
            return "FormEtudiant";
        }

        etudiantRepository.save(et);

        return "redirect:index";

    }

    @RequestMapping(value = "/supprimer")
    public String supprimer(Long id){
        etudiantRepository.deleteById(id);
        return "redirect:index";
    }

    @RequestMapping(value = "/editer")
    public String editer(Long id, Model model){
        Etudiant et = etudiantRepository.getById(id);
        model.addAttribute("etudiant", et);
        return "EditEtudiant";
    }

    @RequestMapping(value = "/updateEtudiant", method = RequestMethod.POST)
    public String update(@Valid Etudiant et,
                       BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return "EditEtudiant";
        }

        etudiantRepository.save(et);

        return "redirect:index";
    }

}
