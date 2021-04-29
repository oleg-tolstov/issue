package com.itissue.issue.controllers;

import com.itissue.issue.models.Issue;
import com.itissue.issue.repo.EmployeeRepo;
import com.itissue.issue.repo.EquipmentRepo;
import com.itissue.issue.repo.IssueRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class IssueController {

	private final IssueRepo issueRepo;
	private final EmployeeRepo employeeRepo;
	private final EquipmentRepo equipmentRepo;

	@GetMapping("/issue")
	public String issueMain(Model model) {
		Iterable<Issue> Issues = issueRepo.findAll();
		model.addAttribute("Issues", Issues);
		return "issue-main";
	}

	@GetMapping("/issue/add")
	public String issueAdd(Model model) {
		model.addAttribute("empIds", employeeRepo.findAll());
		model.addAttribute("equIds", equipmentRepo.findAllNotIssued());
		return "issue-add";
	}

	@PostMapping("/issue/add")
	public String issuePostAdd(@RequestParam Long employeeId,
	                           @RequestParam Long equipmentId,
	                           Model model) {
		if (!issueRepo.existsIssueByEquipmentId(equipmentId)) {
			Issue issue = new Issue(employeeRepo.findById(employeeId).get(), equipmentRepo.findById(equipmentId).get());
			issueRepo.save(issue);
		} else {
			model.addAttribute("errorIssueIsExist");
		}

		return "redirect:/issue";
	}

	@GetMapping("/issue/{id}")
	public String issueDetails(@PathVariable(value = "id") long id, Model model) {
		if (!issueRepo.existsById(id)) {
			return "redirect:/issue";
		}
		Optional<Issue> issue = issueRepo.findById(id);
		ArrayList<Issue> res = new ArrayList<>();
		issue.ifPresent(res::add);
		model.addAttribute("issue", res);
		return "issue-details";
	}


	@PostMapping("/issue/{id}/remove")
	public String issuePostDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
		Issue equipment = issueRepo.findById(id).orElseThrow(() -> new Exception());
		issueRepo.delete(equipment);
		return "redirect:/issue";
	}

	public IssueController(IssueRepo issueRepo, EmployeeRepo employeeRepo, EquipmentRepo equipmentRepo) {
		this.issueRepo = issueRepo;
		this.employeeRepo = employeeRepo;
		this.equipmentRepo = equipmentRepo;
	}

}
