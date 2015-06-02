package dao.departmentDAO.hibernate;

import dao.departmentDAO.DepartmentDAO;
import exception.DAOException;
import models.Department;
import org.hibernate.Session;
import utils.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 24.04.15.
 */
public class DepartmentHibernateImpl implements DepartmentDAO{

    private List <Department> department;

    public DepartmentHibernateImpl(){
        this.department = new ArrayList<Department>();
    }

    public Department getDepartmentById(Integer id) throws DAOException {

        Session session = null;
        Department department = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            department = (Department) session.get(Department.class, id);
        }
        catch (Exception e) {
            throw new DAOException();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return department;
    }

    public void addDepartment(Department department) throws DAOException {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(department);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            throw new DAOException();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void deleteDepartment(Department department) throws DAOException {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(department);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            throw new DAOException();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void updateDepartment(Department department) throws DAOException {

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(department);
            session.getTransaction().commit();
        }
        catch (Exception e) {
            throw new DAOException();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Department> getAllDepartments() throws DAOException {

        Session session = null;
        List<Department> dep;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            dep = (List<Department>) session.createCriteria(Department.class).list();
            return dep;
        }
        catch (Exception e) {
            throw new DAOException();
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<Department> getAll() {
        return this.department;
    }
}
