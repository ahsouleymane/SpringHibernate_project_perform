package net.sancfis.springhibernate_project_perform.dao;

import net.sancfis.springhibernate_project_perform.entities.Etudiant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    public List<Etudiant> findByNom(String n);
    public Page<Etudiant> findByNom(String n, Pageable pageable);

    @Query("select e from Etudiant e where e.nom like :x")
    public  Page<Etudiant> chercherEtudiants(@Param("x") String mc, Pageable pageable);

}
