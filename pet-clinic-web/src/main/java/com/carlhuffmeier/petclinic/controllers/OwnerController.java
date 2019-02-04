package com.carlhuffmeier.petclinic.controllers;

import com.carlhuffmeier.petclinic.models.Owner;
import com.carlhuffmeier.petclinic.services.OwnerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"", "/index", "/index.html"})
    public String getAllOwners(Model model) {
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping({"/find/${lastName}"})
    public String findOwners(@PathVariable String lastName, Model model) {
        Owner owner = ownerService.findByLastName(lastName);
        Optional<Owner> ownerOptional = Optional.of(owner);
        if(ownerOptional.isPresent()){
            model.addAttribute("owners", new ArrayList<>(Arrays.asList(owner)));
        }
//        return "notimplemented";
        return "owners/ownerInfo";
    }
}
