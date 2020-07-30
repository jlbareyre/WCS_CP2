package com.wcs_cp2.kiakwa.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.wcs_cp2.kiakwa.model.Materiel;
import com.wcs_cp2.kiakwa.model.User;
import com.wcs_cp2.kiakwa.repository.MaterielRepository;
import com.wcs_cp2.kiakwa.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class KiakwaController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MaterielRepository materielRepo;

    @GetMapping("/")
    private String goHome() {

        return "index";
    }

    @GetMapping("/base")
    private String goBase(Model model, @ModelAttribute("userId") String userId) {
   
        User user = userRepo.findById(UUID.fromString(userId)).get();

        model.addAttribute("nom", user.getName());
        model.addAttribute("user", user);
        model.addAttribute("userId", user.getUserID().toString());


        List<Materiel> ListMateriel = materielRepo.findAll().stream().filter(m -> user.equals(m.getUser()))
                .collect(Collectors.toList());

        model.addAttribute("materiel", ListMateriel);

        return "base";
    }

}