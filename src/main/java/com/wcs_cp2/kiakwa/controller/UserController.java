package com.wcs_cp2.kiakwa.controller;

import java.util.List;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MaterielRepository materielRepo;

    @PostMapping("/user")
    public String newUser(Model model, @ModelAttribute("name") String name, @ModelAttribute("pass") String pass,
            @ModelAttribute("mail") String mail) {

        Optional<User> rechUser = userRepo.findAll().stream().filter(u -> name.equals(u.getName())).findAny();

        if (rechUser.isPresent()) {
            model.addAttribute("err", "exists");
            return "index";
        } else {
            User user = new User(name, pass, mail, "BaseUser");
            userRepo.save(user);

            model.addAttribute("nom", user.getName());
            model.addAttribute("userId", user.getUserID().toString());

            List<Materiel> ListMateriel = materielRepo.findAll().stream().filter(m -> user.equals(m.getUser()))
                    .collect(Collectors.toList());
            model.addAttribute("materiel", ListMateriel);

            return "base";
        }
    }

    @PostMapping("/logIn")
    public String logUser(Model model, @ModelAttribute("logName") String logName,
            @ModelAttribute("logPass") String logPass) {

        Optional<User> rechUser = userRepo.findAll().stream()
                .filter(u -> logName.equals(u.getName()) && logPass.equals(u.getPass())).findAny();

        if (rechUser.isPresent()) {

            User user = rechUser.get();

            model.addAttribute("nom", logName);
            model.addAttribute("userId", rechUser.get().getUserID().toString());

            List<Materiel> ListMateriel = materielRepo.findAll().stream().filter(m -> user.equals(m.getUser()))
                    .collect(Collectors.toList());
            model.addAttribute("materiel", ListMateriel);

            return "base";

        } else {
            model.addAttribute("err", "ident");
            return "index";
        }
    }

}