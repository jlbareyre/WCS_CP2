package com.wcs_cp2.kiakwa.controller;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.wcs_cp2.kiakwa.model.Materiel;
import com.wcs_cp2.kiakwa.model.User;
import com.wcs_cp2.kiakwa.repository.MaterielRepository;
import com.wcs_cp2.kiakwa.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MaterielController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MaterielRepository materielRepo;

    @PostMapping("/materiel")
    public String newMateriel(Model model, @ModelAttribute("userId") String userId,
            @ModelAttribute("matNom") String matNom, @ModelAttribute("detail") String detail) {

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

        // User user = userRepo.findById(UUID.fromString(userId)).get();
        User user = userRepo.findById(UUID.fromString(userId)).get();

        Materiel mat = new Materiel(UUID.randomUUID(), newName, "", detail, user);

        materielRepo.save(mat);

        model.addAttribute("nom", user.getName());
        model.addAttribute("user", user);

        List<Materiel> ListMateriel = materielRepo.findAll().stream().filter(m -> user.equals(m.getUser()))
                .collect(Collectors.toList());

        model.addAttribute("materiel", ListMateriel);

        return "base";

    }

}