package controllers.hendlers.employeeHandlers;

import controllers.hendlers.EmployeesHandle;
import controllers.hendlers.creators.EmployeeFromRequest;
import dao.departmentDAO.DepartmentDAO;
import dao.departmentDAO.jdbc.DepartmentJDBCImplementation;
import dao.employeeDAO.EmployeeDAO;
import exception.EmailFormatException;
import exception.EmployeeNullFieldsException;
import exception.SalaryFormatException;
import exception.SameEmailException;
import models.Employee;
import services.EmployeeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by pavel on 23.04.15.
 */
public class AddEmployeeHandler implements EmployeesHandle {

    private final String DriverException = "You probably will never see this message, " +
            "but if it`s happen you must to know that you have not jdbc.mysql.Driver!";
    private final String DatabaseException = "We have some trouble with Database, sorry for that!";

    public void handle(HttpServletRequest request, HttpServletResponse response, EmployeeDAO employeeDAO) throws ServletException, IOException, SQLException, ClassNotFoundException {
        Employee employee = EmployeeFromRequest.createEmployee(request);
        DepartmentDAO departmentDAO = new DepartmentJDBCImplementation();
        try {
            EmployeeService.service(employee, employeeDAO, true);
            RequestDispatcher rd = request.getRequestDispatcher("employees.html");
            rd.forward(request, response);
        } catch (EmployeeNullFieldsException e) {
            departmentDAO.getAllDepartments();
            request.setAttribute("departments", departmentDAO.getAll());
            request.setAttribute("employee", employee);
            request.setAttribute("errorMessage", e.getErrorMessage());
            RequestDispatcher rd = request.getRequestDispatcher("add_employee.jsp");
            rd.forward(request,response);
        } catch (SameEmailException e) {
            departmentDAO.getAllDepartments();
            request.setAttribute("departments", departmentDAO.getAll());
            request.setAttribute("employee", employee);
            request.setAttribute("sameEmailError", "Employee with this email is already exist");
            RequestDispatcher rd = request.getRequestDispatcher("add_employee.jsp");
            rd.forward(request, response);
        } catch (EmailFormatException e) {
            departmentDAO.getAllDepartments();
            request.setAttribute("departments", departmentDAO.getAll());
            request.setAttribute("employee", employee);
            request.setAttribute("emailFormatError", "Email format is not correct");
            RequestDispatcher rd = request.getRequestDispatcher("add_employee.jsp");
            rd.forward(request, response);
        } catch (SalaryFormatException e) {
            departmentDAO.getAllDepartments();
            request.setAttribute("departments", departmentDAO.getAll());
            request.setAttribute("employee", employee);
            request.setAttribute("salaryError", "Salary cannot be less then 0");
            RequestDispatcher rd = request.getRequestDispatcher("add_employee.jsp");
            rd.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("errorMessage",DatabaseException);
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        } catch (ClassNotFoundException e) {
            request.setAttribute("errorMessage",DriverException);
            RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
            rd.forward(request, response);
        }

    }
}
