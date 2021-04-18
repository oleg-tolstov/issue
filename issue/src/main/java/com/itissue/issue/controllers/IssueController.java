package com.itissue.issue.controllers;

import com.itissue.issue.models.Issue;
import com.itissue.issue.repo.IssueRepo;
import com.itissue.issue.repo.IssueRepo;
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
public class IssueController {

    @Autowired
    private IssueRepo IssueRepo;

    @GetMapping("/issue")
    public String equipmentMain(Model model) {
        Iterable<Issue> Issues = IssueRepo.findAll();
        model.addAttribute("Issues", Issues);
        return "issue-main";
    }
    @GetMapping("/issue/add")
    public String issueAdd(Model model) {
        return "issue-add";
    }

/*    @PostMapping("/equipment/add")
    public String issuePostAdd(@RequestParam Long id_emp , Model model){
        Issue issue = new Issue(id_emp);
        IssueRepo.save(issue);
        return "redirect:/issue";
    }*/

    @GetMapping("/issue/{id}")
    public String issueDetails(@PathVariable(value = "id") long id, Model model) {
        if (!IssueRepo.existsById(id)){
            return "redirect:/issue";
        }
        Optional <Issue> employee = IssueRepo.findById(id);
        ArrayList<Issue> res = new ArrayList<>();
        employee.ifPresent(res::add);
        model.addAttribute("issue", res);
        return "issue-details";
    }


    @PostMapping("/issue/{id}/remove")
    public String employeePostDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        Issue equipment = IssueRepo.findById(id).orElseThrow(() -> new Exception());
        IssueRepo.delete(equipment);
        return "redirect:/issue";
    }

}
