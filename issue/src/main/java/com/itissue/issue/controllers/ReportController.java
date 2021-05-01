package com.itissue.issue.controllers;

import com.itissue.issue.models.Employee;
import com.itissue.issue.models.Issue;
import com.itissue.issue.repo.EmployeeRepo;
import com.itissue.issue.repo.EquipmentRepo;
import com.itissue.issue.repo.IssueRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReportController {

    private final IssueRepo issueRepo;
    private final EmployeeRepo employeeRepo;
    private final EquipmentRepo equipmentRepo;

    public ReportController(IssueRepo issueRepo, EmployeeRepo employeeRepo, EquipmentRepo equipmentRepo) {
        this.issueRepo = issueRepo;
        this.employeeRepo = employeeRepo;
        this.equipmentRepo = equipmentRepo;
    }

    @GetMapping("/reports")
    public String reportMain(Model model) {
        model.addAttribute("empIds", employeeRepo.findAll());
        model.addAttribute("equIds", equipmentRepo.findAll());
	    model.addAttribute("employee", new Employee());
        return "reports";
    }

	@PostMapping("/reports")
	public String reportEmp(@ModelAttribute("employee") Employee employee, Model model) {
		Long empId = employee.getId();

		List<Issue> issue = issueRepo.findAllByEmployeeId(empId);
		model.addAttribute("issuesByEmployee", issue);

		String empName = employeeRepo.findById(empId).get().getName();
		model.addAttribute("employeeName", empName);

		return "report-emp";
	}
}


