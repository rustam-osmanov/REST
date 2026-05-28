package ru.rustamosmanov.spring.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rustamosmanov.spring.rest.entity.EmployeeBD;
import ru.rustamosmanov.spring.rest.exception.EmployeeIncorrectData;
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

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(
            NoSuchEmployeeException exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorrectData> handleException(
            Exception exception) {
        EmployeeIncorrectData data = new EmployeeIncorrectData();
        data.setInfo("Неверный формат или некорректный запрос!");
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
