package ru.rustamosmanov.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.rustamosmanov.spring.rest.entity.EmployeeBD;
import ru.rustamosmanov.spring.rest.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    public EmployeeService  employeeService;

    @GetMapping
    public String api() {
        return "API is working";
    }

    @RequestMapping("/xx")
    public String showFirstView(){
        return "first-view";
    }

    @GetMapping("/employees")
    public List<EmployeeBD> showAllEmployees(){
        return employeeService.getAllEmployees();
    }

}
