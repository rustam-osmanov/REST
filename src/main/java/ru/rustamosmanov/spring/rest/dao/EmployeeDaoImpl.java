package ru.rustamosmanov.spring.rest.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.rustamosmanov.spring.rest.entity.EmployeeBD;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<EmployeeBD> getAllEmployees() {
        Session session = sessionFactory.openSession();
        List<EmployeeBD> allEmployees = session.createQuery("from EmployeeBD",EmployeeBD.class)
                .getResultList();
        return allEmployees;
    }

    @Override
    public void saveEmployee(EmployeeBD employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(employee);
        session.getTransaction().commit();
    }

    @Override
    public void deleteEmployee(EmployeeBD employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
    }

    @Override
    public EmployeeBD getEmployee(int id) {
        Session session = sessionFactory.openSession();
        EmployeeBD employee = session.get(EmployeeBD.class,id);
        return employee;
    }
}
