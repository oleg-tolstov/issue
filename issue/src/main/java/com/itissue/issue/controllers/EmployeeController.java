package com.itissue.issue.controllers;

import com.itissue.issue.models.Employee;
import com.itissue.issue.repo.EmployeeRepo;
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
public class EmployeeController {

    @Autowired
    private EmployeeRepo EmployeeRepo;

    @GetMapping("/employee")
    public String employeeMain(Model model) {
        Iterable<Employee> Employees = EmployeeRepo.findAll();
        model.addAttribute("Employees", Employees);
        return "employee-main";
    }
    @GetMapping("/employee/add")
    public String employeedd(Model model) {
        return "employee-add";
    }

    @PostMapping("/employee/add")
    public String employeePostAdd(@RequestParam String name,@RequestParam Long tel, @RequestParam String mail, Model model){
        Employee employee = new Employee(name);
        EmployeeRepo.save(employee);
        return "redirect:/employee";
    }

    @GetMapping("/employee/{id}")
    public String employeeDetails(@PathVariable(value = "id") long id, Model model) {
        if (!EmployeeRepo.existsById(id)){
            return "redirect:/employee";
        }
        Optional <Employee> employee = EmployeeRepo.findById(id);
        ArrayList<Employee> res = new ArrayList<>();
        employee.ifPresent(res::add);
        model.addAttribute("employee", res);
        return "employee-details";
    }

    @GetMapping("/employee/{id}/edit")
    public String employeeEdit(@PathVariable(value = "id") long id, Model model) {
        if (!EmployeeRepo.existsById(id)){
            return "redirect:/employee";
        }
        Optional <Employee> employee = EmployeeRepo.findById(id);
        ArrayList <Employee> res = new ArrayList<>();
        employee.ifPresent(res::add);
        model.addAttribute("employee", res);
        return "employee-edit";
    }

    @PostMapping("/employee/{id}/edit")
    public String employeePostUpdate(@PathVariable(value = "id") long id,@RequestParam String name, @RequestParam Long tel, @RequestParam String mail, Model model) throws Exception {
        Employee employee = EmployeeRepo.findById(id).orElseThrow(() -> new Exception());
        employee.setName(name);
        employee.setTel(tel);
        employee.setMail(mail);
        EmployeeRepo.save(employee);
        return "redirect:/employee";
    }

    @PostMapping("/employee/{id}/remove")
    public String employeePostDelete(@PathVariable(value = "id") long id, Model model) throws Exception {
        Employee employee = EmployeeRepo.findById(id).orElseThrow(() -> new Exception());
        EmployeeRepo.delete(employee);
        return "redirect:/employee";
    }

}
