package com.itissue.issue.controllers;

import com.itissue.issue.models.Employee;
import com.itissue.issue.models.Issue;
import com.itissue.issue.repo.EmployeeRepo;
import com.itissue.issue.repo.EquipmentRepo;
import com.itissue.issue.repo.IssueRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Optional;


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
        return "reports";
    }

    @GetMapping("/reports/{id}")
    public String reportEmp(@PathVariable(value = "id") long empId, Model model) {
        if (!employeeRepo.existsById(empId)){
            return "redirect:/reports";
        }
        Optional <Issue> issue = issueRepo.findById(empId);
        ArrayList<Issue> res = new ArrayList<>();
        issue.ifPresent(res::add);
        model.addAttribute("report", res);
        return "report-emp";
    }

    @GetMapping("/reports/epm/{id}")
    public String reportEmp1(@PathVariable(value = "id") long id, Model model) {
        if (!employeeRepo.existsById(id)){
            return "redirect:/reports";
        }
        Optional<Employee> employee = employeeRepo.findById(id);
        ArrayList<Employee> res = new ArrayList<>();
        employee.ifPresent(res::add);
        model.addAttribute("employee", res);
        return "issue-details";
    }
}


