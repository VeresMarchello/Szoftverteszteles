package com.meiit.szoftteszt.employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao
{
	Connection connection;

	public EmployeeDaoImpl(Connection connection)
	{
		this.connection = connection;
	}

	public void hireEmployee(Employee employee) throws ClassNotFoundException, SQLException
	{
		int id = employee.getId();
		String name = employee.getName();
		int salary = employee.getSalary();
		String division = employee.getDivision();

		PreparedStatement statement = connection.prepareStatement("INSERT INTO Employee VALUES (?, ?, ?, ?)");
		statement.setInt(1, id);
		statement.setString(2, name);
		statement.setInt(3, salary);
		statement.setString(4, division);

		statement.executeUpdate();
	}

	public Employee getEmployee(int id) throws ClassNotFoundException, SQLException
	{
		PreparedStatement statement = connection.prepareStatement("SELECT * FROM Employee WHERE id = ?");
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();

		String name = resultSet.getString(2);
		int salary = resultSet.getInt(3);
		String division = resultSet.getString(4);

		resultSet.close();

		Employee employee = new Employee(id, name, salary, division);
		
		return employee;
	}

	public void fireEmployee(int id) throws ClassNotFoundException, SQLException
	{
		PreparedStatement statement = connection.prepareStatement("DELETE FROM Employee WHERE id = ?");
		statement.setInt(1, id);

		statement.executeUpdate();
	}
}
