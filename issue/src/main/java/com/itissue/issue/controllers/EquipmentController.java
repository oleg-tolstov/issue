package com.itissue.issue.controllers;

import com.itissue.issue.models.Equipment;
import com.itissue.issue.repo.EquipmentRepo;
import com.itissue.issue.repo.EquipmentTypeRepo;
import com.itissue.issue.repo.ManufacturerRepo;
import com.itissue.issue.service.EquipmentLifeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class EquipmentController {

	private final EquipmentRepo equipmentRepo;
	private final EquipmentTypeRepo equipmentTypeRepo;
	private final ManufacturerRepo manufacturerRepo;
	private final EquipmentLifeService equipmentLifeService;

	@GetMapping("/equipment")
	public String equipmentMain(Model model) {
		Iterable<Equipment> Equipments = equipmentRepo.findAll();
		model.addAttribute("Equipments", Equipments);
		model.addAttribute("comesToTheEndList", equipmentLifeService.getComesToTheEndFormatted());
		model.addAttribute("overdueList", equipmentLifeService.getOverdueFormatted());
		return "equipment-main";
	}

	@GetMapping("/equipment/add")
	public String equipmentAdd(Model model) {
		model.addAttribute("typeIds", equipmentTypeRepo.findAll());
		model.addAttribute("manIds", manufacturerRepo.findAll());
		return "equipment-add";
	}

	@PostMapping("/equipment/add")
	public String equipmentPostAdd(@RequestParam String name,
                                   @RequestParam String serial_number,
                                   @RequestParam String note,
                                   @RequestParam Long equipmentTypeId,
								   @RequestParam Long manufacturerId,
                                   Model model) {
		Equipment equipment = new Equipment(name, serial_number, note, equipmentTypeRepo.findById(equipmentTypeId).get(), manufacturerRepo.findById(manufacturerId).get() );
		equipmentRepo.save(equipment);
		return "redirect:/equipment";
	}

	@GetMapping("/equipment/{id}")
	public String equipmentDetails(@PathVariable(value = "id") long id, Model model) {
		if (!equipmentRepo.existsById(id)) {
			return "redirect:/equipment";
		}
		Optional<Equipment> employee = equipmentRepo.findById(id);
		ArrayList<Equipment> res = new ArrayList<>();
		employee.ifPresent(res::add);
		model.addAttribute("equipment", res);
		return "equipment-details";
	}

	@GetMapping("/equipment/{id}/edit")
	public String equipmentEdit(@PathVariable(value = "id") long id, Model model) {
		if (!equipmentRepo.existsById(id)) {
			return "redirect:/equipment";
		}
		Optional<Equipment> employee = equipmentRepo.findById(id);
		ArrayList<Equipment> res = new ArrayList<>();
		employee.ifPresent(res::add);
		model.addAttribute("equipment", res);
		return "equipment-edit";
	}

	@PostMapping("/equipment/{id}/edit")
	public String equipmentPostUpdate(@PathVariable(value = "id") long id,
									  @RequestParam String name,
									  @RequestParam String serial_number,
									  @RequestParam String note,
									  @RequestParam Long equipmentTypeId,
									  @RequestParam Long manufacturerId,
									  Model model) throws Exception {
		Equipment equipment = equipmentRepo.findById(id).orElseThrow(Exception::new);
		equipment.setName(name);
		equipment.setSerial_number(serial_number);
		equipment.setNote(note);
		equipment.setEquipmetType(equipmentTypeRepo.findById(equipmentTypeId).get());
		equipment.setManufacturer(manufacturerRepo.findById(manufacturerId).get());
		equipmentRepo.save(equipment);
		return "redirect:/equipment";
	}

	@PostMapping("/equipment/{id}/remove")
	public String equipmentPostDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
		Equipment equipment = equipmentRepo.findById(id).orElseThrow(Exception::new);
		equipmentRepo.delete(equipment);
		return "redirect:/employee";
	}

	public EquipmentController(EquipmentRepo EquipmentRepo,
							   EquipmentTypeRepo equipmentTypeRepo,
							   ManufacturerRepo manufacturerRepo,
							   EquipmentLifeService equipmentLifeService) {
		this.equipmentRepo = EquipmentRepo;
		this.equipmentTypeRepo = equipmentTypeRepo;
		this.manufacturerRepo = manufacturerRepo;
		this.equipmentLifeService = equipmentLifeService;
	}

}
