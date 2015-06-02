package services.impl;

import dao.employeeDAO.EmployeeDAO;
import exception.DAOException;
import exception.SameEmailException;
import models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import services.EmployeeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 28.04.15.
 */
@Service
public class EmployeeSpringServiceImpl implements EmployeeService {

    private List<Employee> employees;

    private EmployeeDAO employeeDAO;

    @Autowired
    public void setEmployeeDAO(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public EmployeeSpringServiceImpl() {
        this.employees = new ArrayList<Employee>();
    }

    @Transactional
    public Employee getById(Integer id) throws DAOException {
        return employeeDAO.getEmployeeById(id);
    }

    @Transactional
    public List<Employee> getByDepartmentId(Integer id) throws DAOException {
        return employeeDAO.getEmployeesByDepartmentId(id);
    }

    @Transactional
    public void add(Employee employee) throws /*EmployeeNullFieldsException, EmailFormatException,*/ /*SalaryFormatException,*/ SameEmailException, DAOException {
        /*Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(employee);*/
      /*  if (violations.size() > 0) {
            throw new EmployeeNullFieldsException(violations);
        }else if (!EmailValidator.check(employee.getEmail())){
            throw new EmailFormatException(employee.getEmail());
        }else if (employee.getSalary() < 0){*/
            /*throw new SalaryFormatException(employee.getSalary().toString());
        }else*/ if(chekEmail(employee)) {
            throw new SameEmailException(employee.getEmail());
        }else {
            employeeDAO.addEmployee(employee);
        }
    }

    @Transactional
    public void delete(Employee employee) throws DAOException {
        employeeDAO.deleteEmployee(employee);
    }

    @Transactional
    public void update(Employee employee) throws/* EmployeeNullFieldsException, EmailFormatException, SalaryFormatException,*/ SameEmailException, DAOException {
       /* Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(employee);
        if (violations.size() > 0) {
            throw new EmployeeNullFieldsException(violations);
        }else if (!EmailValidator.check(employee.getEmail())){
            throw new EmailFormatException(employee.getEmail());
        }else if (employee.getSalary() < 0){
            throw new SalaryFormatException(employee.getSalary().toString());
        }else */if(chekEmail(employee)) {
            throw new SameEmailException(employee.getEmail());
        }else {
            employeeDAO.updateEmployee(employee);
        }
    }

    @Transactional
    public List<Employee> getAll() throws DAOException {
        return employeeDAO.getAllEmployee();
        //this.employees = employeeDAO.getAll();
    }

    public List<Employee> getList() {
        return this.employees;
    }

    private  boolean chekEmail(Employee employee) throws DAOException {
        employeeDAO.getAllEmployee();
        for(Employee chek : employeeDAO.getAll()){
            if(chek.getEmail().equals(employee.getEmail())) {
                if(!chek.isEquals(employee)) {
                    return true;
                }else{
                   // employeeDAO.delete(employee);
                }
            }
        }
        return false;
    }

}
