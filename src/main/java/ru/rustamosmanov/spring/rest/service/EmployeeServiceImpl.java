package ru.rustamosmanov.spring.rest.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rustamosmanov.spring.rest.dao.EmployeeDAO;
import ru.rustamosmanov.spring.rest.entity.EmployeeBD;

import java.util.List;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDAO employeeDAO;

    @Override
    @Transactional
    public List<EmployeeBD> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    @Transactional
    public void saveEmployee(EmployeeBD employee) {
        employeeDAO.saveEmployee(employee);
    }

    @Override
    @Transactional
    public void deleteEmployee(EmployeeBD employee) {
        employeeDAO.deleteEmployee(employee);
    }

    @Override
    @Transactional
    public EmployeeBD getEmployee(int id) {
        return employeeDAO.getEmployee(id);
    }

}
