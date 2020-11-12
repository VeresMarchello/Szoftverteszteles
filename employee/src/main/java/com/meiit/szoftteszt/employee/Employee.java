package com.meiit.szoftteszt.employee;

public class Employee
{
	private int id;
	private String name;
	private int salary;
	private String division;
	
	public Employee()
	{
		super();
	}

	public Employee(int id, String name, int salary, String division)
	{
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.division = division;
	}

	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public int getSalary()
	{
		return salary;
	}
	public void setSalary(int salary)
	{
		this.salary = salary;
	}

	public String getDivision()
	{
		return division;
	}
	public void setDividion(String division)
	{
		this.division = division;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
		{
			return true;
		}
		
		if (!(obj instanceof Employee))
		{
			return false;
		}
		
		Employee emp = (Employee) obj;
		
		return
			this.getId() == emp.getId() &&
			this.getName() == emp.getName() &&
			this.getSalary() == emp.getSalary() &&
			this.getDivision() == emp.getDivision();
	}
	
	
}
