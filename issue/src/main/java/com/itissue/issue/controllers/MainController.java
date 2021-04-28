package com.itissue.issue.controllers;

import com.itissue.issue.models.Issue;
import com.itissue.issue.repo.IssueRepo;
import com.itissue.issue.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class MainController {

    private final IssueRepo issueRepo;
    private final ReportService service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Медвед");
        return "home";
    }

    @GetMapping("/getIs")
    public List<Issue> getIssues() {

        return (List<Issue>) issueRepo.findAll();

    }

    @GetMapping("/report/{format}")
    public String generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        return service.exportReport(format);

    }

    public MainController(IssueRepo issueRepo, ReportService service) {
        this.issueRepo = issueRepo;
        this.service = service;
    }

}