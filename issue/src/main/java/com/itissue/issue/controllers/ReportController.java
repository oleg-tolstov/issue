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
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
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
/*
    @PostMapping("/reports/{idEmployee}/emp")
    public String reportEmp(@PathVariable long idEmployee, Model model) {
        List<Issue> issue = issueRepo.findIssueByEmployeeId(idEmployee);
        model.addAttribute("issuesByEmployee", issue);
        return "report-emp";
    }*/
    @PostMapping("/reports")
    public String reportEmp(Employee employee, Model model) {

        return "report-emp";
    }

}


