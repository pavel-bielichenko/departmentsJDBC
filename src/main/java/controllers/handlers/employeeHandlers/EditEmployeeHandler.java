package controllers.handlers.employeeHandlers;

import controllers.handlers.Handle;
import exception.DAOException;
import exception.SalaryFormatException;
import exception.SameEmailException;
import models.Employee;
import services.DepartmentService;
import services.EmployeeService;
import services.impl.DepartmentSpringServiceImpl;
import services.impl.EmployeeSpringServiceImpl;
import utils.NumberParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by pavel on 23.04.15.
 */
public class EditEmployeeHandler implements Handle {

    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentService departmentService = new DepartmentSpringServiceImpl(); /*new DepartmentServiceImpl();*/
        Employee employee = new Employee();
        try {
        employee.setId(Integer.valueOf(request.getParameter("id")));
        employee.setName(request.getParameter("name"));
        employee.setSurname(request.getParameter("surname"));
        employee.setAddress(request.getParameter("address"));
        employee.setSalary(NumberParser.parseDouble(request.getParameter("salary")));
        employee.setEmail(request.getParameter("email"));
        employee.setDepartment(departmentService.getById(Integer.valueOf(request.getParameter("department_id"))));
       // employee.setBirthday(Date.valueOf(request.getParameter("birthday")));
            EmployeeService employeeServiceImpl = new EmployeeSpringServiceImpl(); /*new EmployeeServiceImpl();*/
            employeeServiceImpl.update(employee);
            RequestDispatcher rd = request.getRequestDispatcher("employees.html");
            rd.forward(request, response);
        } /*catch (EmployeeNullFieldsException e) {
            request.setAttribute("employee", employee);
            request.setAttribute("errorMessage", e.getErrorMessage());
            RequestDispatcher rd = request.getRequestDispatcher("edit_employee.jsp");
            rd.forward(request, response);
        }*/ catch (SameEmailException e) {
            request.setAttribute("employee", employee);
            request.setAttribute("sameEmailError", "Employee with this email is already exist");
            RequestDispatcher rd = request.getRequestDispatcher("edit_employee.jsp");
            rd.forward(request, response);
        } /*catch (EmailFormatException e) {
            request.setAttribute("employee", employee);
            request.setAttribute("emailFormatError", "Email format is not correct");
            RequestDispatcher rd = request.getRequestDispatcher("edit_employee.jsp");
            rd.forward(request, response);
        }*/ catch (SalaryFormatException e) {
            request.setAttribute("employee", employee);
            request.setAttribute("salaryError", "Salary cannot be less then 0");
            RequestDispatcher rd = request.getRequestDispatcher("edit_employee.jsp");
            rd.forward(request, response);
        } catch (DAOException e) {
            request.setAttribute("errorMessage",e.getDatabaseException());
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }
    }
}
