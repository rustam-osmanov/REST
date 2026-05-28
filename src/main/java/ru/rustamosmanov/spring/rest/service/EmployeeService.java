package ru.rustamosmanov.spring.rest.service;

import ru.rustamosmanov.spring.rest.entity.EmployeeBD;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeBD> getAllEmployees();

    public void saveEmployee(EmployeeBD employee);

    public void deleteEmployee(EmployeeBD employee);

    public EmployeeBD getEmployee(int id);

}
