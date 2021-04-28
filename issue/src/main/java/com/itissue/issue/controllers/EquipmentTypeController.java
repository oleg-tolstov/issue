package com.itissue.issue.controllers;

import com.itissue.issue.models.EquipmetType;
import com.itissue.issue.repo.EquipmentTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EquipmentTypeController {

    private final EquipmentTypeRepo EquipmentTypeRepo;

    @GetMapping("/equipmenttype")
    public String equipmenttypeMain(Model model) {
        Iterable<EquipmetType> EquipmetTypes = EquipmentTypeRepo.findAll();
        model.addAttribute("EquipmentTypes", EquipmetTypes);
        return "equipmenttype-main";
    }

    @GetMapping("/equipmenttype/add")
    public String equipmenttypeAdd(Model model) {
        return "equipmenttype-add";
    }

    @PostMapping("/equipmenttype/add")
    public String equipmenttypePostAdd(@RequestParam String name, Model model) {
        EquipmetType equipmetType = new EquipmetType(name);
        EquipmentTypeRepo.save(equipmetType);
        return "redirect:/equipmenttype";
    }

    @GetMapping("/equipmenttype/{id}")
    public String equipmenttypeDetails(@PathVariable(value = "id") long id, Model model) {
        if (!EquipmentTypeRepo.existsById(id)) {
            return "redirect:/equipmenttype";
        }
        Optional<EquipmetType> equipmetType = EquipmentTypeRepo.findById(id);
        ArrayList<EquipmetType> res = new ArrayList<>();
        equipmetType.ifPresent(res::add);
        model.addAttribute("equipmentType", res);
        return "equipmenttype-details";
    }

    @GetMapping("/equipmenttype/{id}/edit")
    public String equipmenttypeEdit(@PathVariable(value = "id") long id, Model model) {
        if (!EquipmentTypeRepo.existsById(id)) {
            return "redirect:/equipmenttype";
        }
        Optional<EquipmetType> equipmetType = EquipmentTypeRepo.findById(id);
        ArrayList<EquipmetType> res = new ArrayList<>();
        equipmetType.ifPresent(res::add);
        model.addAttribute("equipmentType", res);
        return "equipmenttype-edit";
    }

    @PostMapping("/equipmenttype/{id}/edit")
    public String equipmenttypePostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, Model model) throws Exception {
        EquipmetType equipmetType = EquipmentTypeRepo.findById(id).orElseThrow(() -> new Exception());
        equipmetType.setName(name);
        EquipmentTypeRepo.save(equipmetType);
        return "redirect:/equipmenttype";
    }

    @PostMapping("/equipmenttype/{id}/remove")
    public String equipmenttypePostDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        EquipmetType equipmetType = EquipmentTypeRepo.findById(id).orElseThrow(() -> new Exception());
        EquipmentTypeRepo.delete(equipmetType);
        return "redirect:/equipmenttype";
    }

    public EquipmentTypeController(EquipmentTypeRepo EquipmentTypeRepo) {
        this.EquipmentTypeRepo = EquipmentTypeRepo;
    }
}
