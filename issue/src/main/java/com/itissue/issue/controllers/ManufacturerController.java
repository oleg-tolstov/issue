package com.itissue.issue.controllers;

import com.itissue.issue.models.Manufacturer;
import com.itissue.issue.repo.ManufacturerRepo;
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
public class ManufacturerController {
    private final ManufacturerRepo ManufacturerRepo;

    @GetMapping("/manufacturer")
    public String manufacturerMain(Model model) {
        Iterable<Manufacturer> Manufacturers = ManufacturerRepo.findAll();
        model.addAttribute("Manufacturers", Manufacturers);
        return "manufacturer-main";
    }

    @GetMapping("/manufacturer/add")
    public String manufacturerAdd(Model model) {
        return "manufacturer-add";
    }

    @PostMapping("/manufacturer/add")
    public String manufacturerPostAdd(@RequestParam String name, Model model) {
        Manufacturer manufacturer = new Manufacturer(name);
        ManufacturerRepo.save(manufacturer);
        return "redirect:/manufacturer";
    }

    @GetMapping("/manufacturer/{id}")
    public String manufacturerDetails(@PathVariable(value = "id") long id, Model model) {
        if (!ManufacturerRepo.existsById(id)) {
            return "redirect:/manufacturer";
        }
        Optional<Manufacturer> manufacturer = ManufacturerRepo.findById(id);
        ArrayList<Manufacturer> res = new ArrayList<>();
        manufacturer.ifPresent(res::add);
        model.addAttribute("equipmentType", res);
        return "manufacturer-details";
    }

    @GetMapping("/manufacturer/{id}/edit")
    public String manufacturerEdit(@PathVariable(value = "id") long id, Model model) {
        if (!ManufacturerRepo.existsById(id)) {
            return "redirect:/manufacturer";
        }
        Optional<Manufacturer> manufacturer = ManufacturerRepo.findById(id);
        ArrayList<Manufacturer> res = new ArrayList<>();
        manufacturer.ifPresent(res::add);
        model.addAttribute("manufacturer", res);
        return "manufacturer-edit";
    }

    @PostMapping("/manufacturer/{id}/edit")
    public String manufacturerPostUpdate(@PathVariable(value = "id") long id, @RequestParam String name, Model model) throws Exception {
        Manufacturer manufacturer = ManufacturerRepo.findById(id).orElseThrow(() -> new Exception());
        manufacturer.setName(name);
        ManufacturerRepo.save(manufacturer);
        return "redirect:/manufacturer";
    }

    @PostMapping("/manufacturer/{id}/remove")
    public String manufacturerPostDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        Manufacturer manufacturer = ManufacturerRepo.findById(id).orElseThrow(() -> new Exception());
        ManufacturerRepo.delete(manufacturer);
        return "redirect:/manufacturer";
    }

    public ManufacturerController(ManufacturerRepo ManufacturerRepo) {
        this.ManufacturerRepo = ManufacturerRepo;
    }
}
