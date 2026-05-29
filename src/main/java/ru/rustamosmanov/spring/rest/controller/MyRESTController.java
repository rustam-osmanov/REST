package ru.rustamosmanov.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rustamosmanov.spring.rest.entity.EmployeeBD;
import ru.rustamosmanov.spring.rest.exception.NoSuchEmployeeException;
import ru.rustamosmanov.spring.rest.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    public EmployeeService employeeService;

    @GetMapping
    public String api() {
        return "API is working";
    }

    @GetMapping("/employees")
    public List<EmployeeBD> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public EmployeeBD getEmployeeById(@PathVariable("id") int id) {
        EmployeeBD employeeBD = employeeService.getEmployee(id);
        if (employeeBD == null) {
            throw new NoSuchEmployeeException("Указанный ID =" + id
                    + " не найден в БД!");
        }
        return employeeBD;
    }

    @PostMapping("/employees")
    public EmployeeBD addNewEmployee(@RequestBody EmployeeBD employeeBD) {
        if (employeeBD == null || employeeBD.getId() != null) {
            throw new NoSuchEmployeeException("Неверный формат данных для данного api post /employees ");
        }

        employeeService.saveEmployee(employeeBD);
        return employeeBD;
    }

    @PutMapping("/employees")
    public EmployeeBD updateEmployee(@RequestBody EmployeeBD employeeBD) {
        if (employeeBD == null || employeeBD.getId() == null) {
            throw new NoSuchEmployeeException("Неверный формат данных для данного api put /employees ");
        }
        if (employeeService.getEmployee(employeeBD.getId()) == null) {
            throw new NoSuchEmployeeException("Некорректный формат данных для данного api put /employees ");
        }
        employeeService.saveEmployee(employeeBD);
        return employeeBD;
    }

    @DeleteMapping("/employees/{id}")
    public EmployeeBD deleteEmployeeById(@PathVariable("id") int id) {
        EmployeeBD employeeBD = employeeService.getEmployee(id);
        if (employeeBD == null) {
            throw new NoSuchEmployeeException("Указанный ID =" + id
                    + " не найден в БД!");
        }
        employeeService.deleteEmployee(employeeBD);
        return employeeBD;
    }

}
