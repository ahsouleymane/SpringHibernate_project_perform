package net.sancfis.springhibernate_project_perform;

import net.sancfis.springhibernate_project_perform.dao.EtudiantRepository;
import net.sancfis.springhibernate_project_perform.entities.Etudiant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootApplication
public class GestionScolariteApplication {

    public static void main(String[] args) throws Exception{

        ApplicationContext ctx = SpringApplication.run(GestionScolariteApplication.class, args);
        EtudiantRepository etudiantRepository = ctx.getBean(EtudiantRepository.class);

        /*etudiantRepository.save(
                new Etudiant("Farida", "Yahaya"));
        etudiantRepository.save(
                new Etudiant("Hermann", "Savi"));*/

        //Page<Etudiant> etds = etudiantRepository.findAll(PageRequest.of(0, 5));
        Page<Etudiant> etds0 = etudiantRepository.chercherEtudiants("%E%",PageRequest.of(0, 5));
        etds0.forEach(e->System.out.println(e.getNom()));

    }

}
