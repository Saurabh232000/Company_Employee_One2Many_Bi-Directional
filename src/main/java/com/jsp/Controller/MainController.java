package com.jsp.Controller;

import java.util.List;
import java.util.Scanner;
import com.jsp.DAO.CompanyDao;
import com.jsp.DAO.EmployeeDao;
import com.jsp.DTO.Company;
import com.jsp.DTO.Employee;

public class MainController {
	static Scanner sc = new Scanner(System.in);
	static CompanyDao CompDao = new CompanyDao();
	static EmployeeDao EmpDao = new EmployeeDao();

	public static void main(String[] args) {
		boolean b = true;
		while (true) {
			System.out.println("1.save Company : ");
			System.out.println("2.update Company : ");
			System.out.println("3.save Employee");
			System.out.println("4.update Employee");
			System.out.println("5.Find Employees by Company Id :");
			System.out.println("6.Find Employees by Company Name :");
			System.out.println("7.Verify Employee By Id and Password :");
			System.out.println("8.Verify Employee By phone and Password :");
			System.out.println("9.Verify Employee By email and Password :");
			System.out.println("10.Find Company By Id :");
			System.out.println("11.Find All Employees :");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				saveComp();
				break;
			}
			case 2: {
				updateComp();
				break;
			}
			case 3: {
				saveEmp();
				break;
			}
			case 4: {
				updateEmp();
				break;
			}
			case 5: {
				findEmpsByCompId();
				break;
			}
			case 6: {
				findEmpsByCompName();
				break;
			}
			case 7: {
				verifyEmpById();
				break;
			}
			case 8: {
				verifyEmpByPhone();
				break;
			}
			case 9: {
				verifyEmpByEmail();
				break;
			}
			case 10: {
				findCompById();
				break;
			}
			case 11: {
				findAllEmp();
				break;
			}
			default:
				b = false;
				System.err.println(" Thnak you For Using My Software............");
			}
		}
	}

	public static void saveComp() {
		Company company = new Company();
		System.out.println("Enter Your Company Name Here :");
		company.setName(sc.next());
		System.out.println("ENter Your Current Location :");
		company.setLocation(sc.next());
		company.setEmployee(null);
		Company saveCompany = CompDao.saveCompany(company);
		System.err.println(" One Company Saved :");
		System.out.println(saveCompany.getId());
		System.out.println(saveCompany.getName());
		System.out.println(saveCompany.getLocation());
	}

	public static void updateComp() {
		Company company = new Company();
		System.out.println("Enter Your Company Id To Update  :");
		company.setId(sc.nextInt());
		System.out.println("Enter Your New Company Name Here :");
		company.setName(sc.next());
		System.out.println("Enter Your New Location  :");
		company.setLocation(sc.next());
		Company updateCompany = CompDao.updateCompany(company);
		System.err.println(" One Record Updated :");
		System.out.println(updateCompany.getId());
		System.out.println(updateCompany.getName());
		System.out.println(updateCompany.getLocation());
	}

	public static void saveEmp() {
		System.out.println("Enter the Company id to Add Employee :");
		int comp_id = sc.nextInt();
		Employee e = new Employee();
		System.out.println(" Enter Employee Name:");
		e.setName(sc.next());
		System.out.println(" ENter Employee Phone:");
		e.setPhone(sc.nextLong());
		System.out.println("ENter Employee EmailId:");
		e.setEmail(sc.next());
		System.out.println("ENTer Employee Password:");
		e.setPassword(sc.next());
		Employee saveEmpByCompId = EmpDao.saveEmpByCompId(e, comp_id);
		if (saveEmpByCompId != null) {
			System.err.println(" One EMployee Record Save By Company ID");
			System.out.println(saveEmpByCompId.getCompany().getId());
			System.out.println(saveEmpByCompId.getId());
			System.out.println(saveEmpByCompId.getName());
			System.out.println(saveEmpByCompId.getPhone());
			System.out.println(saveEmpByCompId.getEmail());
			System.out.println(saveEmpByCompId.getPassword());
		} else {
			System.out.println(" Invalid Company Id :");
		}
	}

	public static void updateEmp() {
		System.out.println("Enter the Company id to Update Employee :");
		int comp_id = sc.nextInt();
		Employee e = new Employee();
		System.out.println(" Enter Employee Id to Update :");
		e.setId(sc.nextInt());
		System.out.println(" Enter Employee Name:");
		e.setName(sc.next());
		System.out.println(" ENter Employee Phone:");
		e.setPhone(sc.nextLong());
		System.out.println("ENter Employee EmailId:");
		e.setEmail(sc.next());
		System.out.println("ENTer Employee Password:");
		e.setPassword(sc.next());
		Employee updateEmpByCompId = EmpDao.updateEmpByCompId(e, comp_id);
		System.out.println(" one Employee Record UpdateD:");
		System.out.println(updateEmpByCompId.getId());
		System.out.println(updateEmpByCompId.getName());
		System.out.println(updateEmpByCompId.getPhone());
		System.out.println(updateEmpByCompId.getEmail());
		System.out.println(updateEmpByCompId.getPassword());
	}

	public static void findEmpsByCompId() {
		System.out.println("Enter the company Id to Find the Employee:");
		int c_id = sc.nextInt();
		List<Employee> findEmpByCompId = EmpDao.findEmpByCompId(c_id);
		if (findEmpByCompId.size() > 0) {
			for (Employee e : findEmpByCompId) {
				System.out.println(e.getId());
				System.out.println(e.getName());
				System.out.println(e.getPhone());
				System.out.println(e.getEmail());
				System.out.println(e.getPassword());
			}
		} else {
			System.err.println(" Invalid Company Id:");
		}
	}

	public static void findEmpsByCompName() {
		System.out.println("Enter the Company name to find Employee:");
		String name = sc.next();
		List<Employee> findEmpByCompName = EmpDao.findEmpByCompName(name);
		if (findEmpByCompName.size() > 0) {
			for (Employee e : findEmpByCompName) {
				System.out.println(e.getId());
				System.out.println(e.getName());
				System.out.println(e.getPhone());
				System.out.println(e.getEmail());
				System.out.println(e.getPassword());
			}
		} else {
			System.err.println("Invalid Company Name :");
		}
	}

	public static void verifyEmpById() {
		System.out.println("Enter the Employee id :");
		int id = sc.nextInt();
		System.out.println("Enter the Employee Password:");
		String password = sc.next();
		List<Employee> emp = EmpDao.findByIdAndPass(id, password);
		if (emp.size() > 0) {
			for (Employee e : emp) {
				System.out.println(e.getId());
				System.out.println(e.getName());
				System.out.println(e.getPhone());
				System.out.println(e.getEmail());
				System.out.println(e.getPassword());
			}
		} else {
			System.err.println("Invalid Id or password :");
		}
	}

	public static void verifyEmpByPhone() {
		System.out.println("Enter the phone :");
		long phone = sc.nextLong();
		System.out.println("Enter the Password:");
		String password = sc.next();
		List<Employee> emp = EmpDao.findByPhoneAndPass(phone, password);
		if (emp.size() > 0) {
			for (Employee e : emp) {
				System.out.println(e.getId());
				System.out.println(e.getName());
				System.out.println(e.getPhone());
				System.out.println(e.getEmail());
				System.out.println(e.getPassword());
			}
		} else {
			System.err.println("Invalid Phone Number or password :");
		}
	}

	public static void verifyEmpByEmail() {
		System.out.println("Enter the Email id : ");
		String email = sc.next();
		System.out.println("Enter Password :");
		String password = sc.next();
		List<Employee> emp = EmpDao.findByEmailAndPass(email, password);
		if (emp.size() > 0) {
			for (Employee e : emp) {
				System.out.println(e.getId());
				System.out.println(e.getName());
				System.out.println(e.getPhone());
				System.out.println(e.getEmail());
				System.out.println(e.getPassword());
			}
		} else {
			System.err.println("Invalid Email Id or password :");
		}
	}

	public static void findCompById() {
		int comp_id = sc.nextInt();
		Company d = CompDao.findById(comp_id);
		if (d != null) {
			System.out.println("Company Id:" + d.getId());
			System.out.println("Company Name:" + d.getName());
			System.out.println("Company Location:" + d.getLocation());
		} else {
			System.err.println("Invalid Company Id :");
		}
	}

	public static void findAllEmp() {
		List<Employee> findAllEmp = EmpDao.findAllEmp();
		for (Employee e : findAllEmp) {
			System.out.println("All Employee Details:-");
			System.out.println(e.getId());
			System.out.println(e.getName());
			System.out.println(e.getPhone());
			System.out.println(e.getEmail());
			System.out.println(e.getPassword());
			System.out.println(e.getCompany());
			System.out.println("______________");
		}
	}
}
