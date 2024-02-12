package com.jsp.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.transaction.Transaction;

import com.jsp.DTO.Company;

public class CompanyDao {
	EntityManager manager = new Persistence().createEntityManagerFactory("dev").createEntityManager();
	EntityTransaction tx = manager.getTransaction();

	// Save Company:
	public Company saveCompany(Company company) {
		this.manager.persist(company);
		tx.begin();
		tx.commit();
		return company;
	}

	// Update Company:
	public Company updateCompany(Company company) {
		this.manager.merge(company);
		tx.begin();
		tx.commit();
		return company;
	}

	// find Company By Id:
	public Company findById(int id) {
		Company find = this.manager.find(Company.class, id);
		return find;
	}
}
