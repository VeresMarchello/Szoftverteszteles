package com.meiit.konyvtar;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.meiit.szoftteszt.employee.Employee;
import com.meiit.szoftteszt.employee.EmployeeDao;
import com.meiit.szoftteszt.employee.EmployeeDaoImpl;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDaoImplTest
{
	@Mock
	Connection mockConnection;
	
	@Mock
	PreparedStatement mockPreparedStatement;

	@Mock
	ResultSet mockResultSet;
	
	EmployeeDao dao;
	
	Employee expectedEmployee;
	
	@Before
	public void setup() throws ClassNotFoundException, SQLException
	{
		dao = new EmployeeDaoImpl(mockConnection);
		expectedEmployee = new Employee(1, "Jakab Gipsz", 100000, "A");
		
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
	}
	
	@Test
	public void testHireEmployee() throws ClassNotFoundException, SQLException
	{
		dao.hireEmployee(expectedEmployee);
		
		verify(mockPreparedStatement, times(1)).setInt(eq(1), anyInt());
		verify(mockPreparedStatement, times(1)).setInt(eq(3), anyInt());
		verify(mockPreparedStatement, times(1)).setString(eq(2), anyString());
		verify(mockPreparedStatement, times(1)).setString(eq(4), anyString());

		ArgumentCaptor<Integer> setArgument1 = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<Integer> setArgument3 = ArgumentCaptor.forClass(Integer.class);
		ArgumentCaptor<String> setArgument2 = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<String> setArgument4 = ArgumentCaptor.forClass(String.class);
		
		verify(mockPreparedStatement).setInt(eq(1), setArgument1.capture());
		verify(mockPreparedStatement).setInt(eq(3), setArgument3.capture());	
		verify(mockPreparedStatement).setString(eq(2), setArgument2.capture());	
		verify(mockPreparedStatement).setString(eq(4), setArgument4.capture());	
		
		assertEquals(expectedEmployee.getId(), setArgument1.getValue().intValue());
		assertEquals(expectedEmployee.getSalary(), setArgument3.getValue().intValue());
		assertEquals(expectedEmployee.getName(), setArgument2.getValue());
		assertEquals(expectedEmployee.getDivision(), setArgument4.getValue());
	}
	
	@Test
	public void testGetEmployee() throws ClassNotFoundException, SQLException 
	{
		when(mockResultSet.getString(2)).thenReturn(expectedEmployee.getName());
		when(mockResultSet.getInt(3)).thenReturn(expectedEmployee.getSalary());
		when(mockResultSet.getString(4)).thenReturn(expectedEmployee.getDivision());
		
		Employee actualEmployee = dao.getEmployee(1);
		
		assertTrue(actualEmployee.equals(expectedEmployee));
	}
	
	@Test
	public void testFireEmployee() throws ClassNotFoundException, SQLException 
	{
		dao.fireEmployee(1);
		
		verify(mockPreparedStatement, times(1)).executeUpdate();
	}

}
