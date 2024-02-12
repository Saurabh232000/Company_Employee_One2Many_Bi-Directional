package com.jsp.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jsp.DTO.Company;
import com.jsp.DTO.Employee;

public class EmployeeDao {

	EntityManager manager = new Persistence().createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction tx = manager.getTransaction();

	// Save Employee By Company ID
	public Employee saveEmpByCompId(Employee employee, int id) {
		Company find = this.manager.find(Company.class, id);
		if (find != null) {
			employee.setCompany(find);
			find.getEmployee().add(employee);
			this.manager.persist(find);
			tx.begin();
			tx.commit();
		} else {
			return null;
		}
		return employee;
	}

	// update Employee By Company ID
	public Employee updateEmpByCompId(Employee employee, int id) {
		Company find = this.manager.find(Company.class, id);
		if (find != null) {
			employee.setCompany(find);
			find.getEmployee().add(employee);
			this.manager.merge(find);
			tx.begin();
			tx.commit();
		}
		return employee;
	}

	// Find Employee By Company ID
	public List<Employee> findEmpByCompId(int id) {
		String qry = "select c.employee from Company c where c.id=?1";
		Query createQuery = this.manager.createQuery(qry);
		createQuery.setParameter(1, id);
		List resultList = createQuery.getResultList();
		return resultList;
	}

	// Find Employee By Company Name
	public List<Employee> findEmpByCompName(String name) {
		String qry = "select c.employee from Company c where c.name=?1";
		Query createQuery = this.manager.createQuery(qry);
		createQuery.setParameter(1, name);
		List resultList = createQuery.getResultList();
		return resultList;
	}

	// Find Employee By Id And password
	public List<Employee> findByIdAndPass(int id, String password) {
		String query = "select e from Employee e where e.id=?1 and e.password=?2";
		Query query2 = this.manager.createQuery(query);
		query2.setParameter(1, id);
		query2.setParameter(2, password);
		List resultList = query2.getResultList();
		return resultList;
	}

	// Find Employee by Phone And Password
	public List<Employee> findByPhoneAndPass(long phone, String password) {
		String query = "select e from Employee e where e.phone=?1 and e.password=?2";
		Query query2 = this.manager.createQuery(query);
		query2.setParameter(1, phone);
		query2.setParameter(2, password);
		List resultList = query2.getResultList();
		return resultList;
	}

	// Find Employee By Email And Password
	public List<Employee> findByEmailAndPass(String email, String password) {
		String query = "select e from Employee e where e.email=?1 and e.password=?2";
		Query query2 = this.manager.createQuery(query);
		query2.setParameter(1, email);
		query2.setParameter(2, password);
		List resultList = query2.getResultList();
		return resultList;
	}

	// Find All Employee
	public List<Employee> findAllEmp() {
		String query = "select e from Employee e";
		Query createQuery = this.manager.createQuery(query);
		List resultList = createQuery.getResultList();
		return resultList;
	}

}
