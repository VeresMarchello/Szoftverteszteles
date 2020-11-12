package com.meiit.szoftteszt.employee;

import java.sql.SQLException;

public interface EmployeeDao
{
	public void hireEmployee(Employee employee) throws ClassNotFoundException, SQLException;

	public Employee getEmployee(int id) throws ClassNotFoundException, SQLException;

	public void fireEmployee(int id) throws ClassNotFoundException, SQLException;
}
