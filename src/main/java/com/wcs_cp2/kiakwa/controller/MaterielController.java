package com.wcs_cp2.kiakwa.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.wcs_cp2.kiakwa.model.Emprunt;
import com.wcs_cp2.kiakwa.model.Materiel;
import com.wcs_cp2.kiakwa.model.User;
import com.wcs_cp2.kiakwa.repository.EmpruntRepository;
import com.wcs_cp2.kiakwa.repository.MaterielRepository;
import com.wcs_cp2.kiakwa.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MaterielController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MaterielRepository materielRepo;

    @Autowired
    private EmpruntRepository empruntRepo;    

    @PostMapping("/materiel")
    public String newMateriel(Model model, 
            @ModelAttribute("matId") String matId, @ModelAttribute("userId") String userId,
            @ModelAttribute("matNom") String matNom, @ModelAttribute("detail") String detail, 
            RedirectAttributes ra) {

        String newName = matNom;
        /*
         * int idx = 1;
         * 
         * List<Materiel> allMat = materielRepo.findAll().stream().filter(m ->
         * user.equals(m.getUser())).collect(Collectors.toList());
         * 
         * while( allMat.stream().filter(m ->
         * newName.equals(m.getName())).findAny().isPresent() ) { newName = name + "_" +
         * idx; idx++; }
         */

        User user = userRepo.findById(UUID.fromString(userId)).get();

        Materiel mat;
        if ( (matId == null) || (matId.equals("") ) ) {
            mat = new Materiel(UUID.randomUUID(), newName, "", detail, user);
        } else {
            mat = materielRepo.findById( UUID.fromString(matId) ).get();
            mat.setNomMat(newName);
            mat.setDetail(detail);
        }

        materielRepo.save(mat);

        ra.addAttribute("userId", userId);
        return "redirect:/base";

    }
    
    @GetMapping("/deleteMateriel/{id}")
    public String deleteMAteriel(Model model, @PathVariable UUID id, 
                    RedirectAttributes ra) {

        Materiel mat = materielRepo.findById(id).get();
        UUID userId = mat.getUser().getUserID();

        materielRepo.deleteById(id);

        ra.addAttribute("userId", userId);
        return "redirect:/base";
    }
        
    @GetMapping("/materiel/{id}")
    public String detailMateriel(Model model, @PathVariable UUID id, 
                    RedirectAttributes ra) {

        Materiel mat = materielRepo.findById(id).get();
        User user = mat.getUser();

        List<Emprunt> lstEmp = empruntRepo.findAll().stream().filter(e -> mat.equals(e.getMateriel())).collect(Collectors.toList());

        model.addAttribute("materiel", mat);
        model.addAttribute("emprunts", lstEmp);
        model.addAttribute("userId", user.getUserID());

        return "materiel";
    }

    @GetMapping("/emprunt/{id}")
    public String empruntMateriel(Model model, @PathVariable UUID id, 
                    RedirectAttributes ra) {

        Materiel mat = materielRepo.findById(id).get();
        User user = mat.getUser();

        List<Emprunt> lstEmp = empruntRepo.findAll().stream().filter(e -> mat.equals(e.getMateriel())).collect(Collectors.toList());

        model.addAttribute("materiel", mat);
        model.addAttribute("emprunts", lstEmp);
        model.addAttribute("userId", user.getUserID());

        return "materiel";
    }

    @GetMapping("/recupEmprunt/{id}")
    public String recupEmpruntMateriel(Model model, @PathVariable Long id, 
                    RedirectAttributes ra) {

        Emprunt emp = empruntRepo.findById(id).get();
                    
        Date dt = new Date();
        emp.setRetour(dt);
        empruntRepo.save(emp);

        Materiel mat = materielRepo.findById(emp.getMateriel().getMatId()).get();
        User user = mat.getUser();

        List<Emprunt> lstEmp = empruntRepo.findAll().stream().filter(e -> mat.equals(e.getMateriel())).collect(Collectors.toList());

        model.addAttribute("materiel", mat);
        model.addAttribute("emprunts", lstEmp);
        model.addAttribute("userId", user.getUserID());

        return "materiel";
    }

    @PostMapping("/emprunt")
    public String nouvelEmpruntMateriel(Model model,
                    @ModelAttribute("matId") String matId,
                    @ModelAttribute("qui") String qui,
                    @ModelAttribute("quand") String quand,
                    RedirectAttributes ra) {

        Date quandDate;
		try {
			quandDate = new SimpleDateFormat("yyyy-MM-dd").parse(quand);
		} catch (ParseException e1) {
			quandDate = new Date();
		}
        
        Emprunt emprunt = new Emprunt( qui, quandDate, materielRepo.findById(UUID.fromString(matId)).get() );
        
        empruntRepo.save(emprunt);

        Materiel mat = emprunt.getMateriel();
        List<Emprunt> lstEmp = empruntRepo.findAll().stream().filter(e -> mat.equals(e.getMateriel())).collect(Collectors.toList());

        model.addAttribute("materiel", mat);
        model.addAttribute("emprunts", lstEmp);
        model.addAttribute("userId", mat.getUser().getUserID());
        
        return "materiel";
    }


}