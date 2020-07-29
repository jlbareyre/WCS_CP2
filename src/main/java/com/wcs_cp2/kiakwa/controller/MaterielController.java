package com.wcs_cp2.kiakwa.controller;

import java.util.UUID;

import com.wcs_cp2.kiakwa.model.Materiel;
import com.wcs_cp2.kiakwa.model.User;
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

    @PostMapping("/materiel")
    public String newMateriel(Model model, @ModelAttribute("userId") String userId,
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

        Materiel mat = new Materiel(UUID.randomUUID(), newName, "", detail, user);

        materielRepo.save(mat);

        ra.addAttribute("userId", userId);
        return "redirect:/base";

    }
    
    @GetMapping("/deleteMateriel/{id}")
    public String deleteConfigMap(Model model, @PathVariable UUID id, 
                    RedirectAttributes ra) {

        Materiel mat = materielRepo.findById(id).get();
        UUID userId = mat.getUser().getUserID();

        materielRepo.deleteById(id);

        ra.addAttribute("userId", userId);
        return "redirect:/base";
    }


}