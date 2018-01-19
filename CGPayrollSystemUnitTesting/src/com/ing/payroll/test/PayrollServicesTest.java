package com.ing.payroll.test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.ing.payroll.beans.Associate;
import com.ing.payroll.beans.BankDetails;
import com.ing.payroll.beans.Salary;
import com.ing.payroll.daoservices.PayrollDAOServices;
import com.ing.payroll.exception.AssociateDetailsNotFoundException;
import com.ing.payroll.exception.PayrollServicesDownException;
import com.ing.payroll.services.PayrollServices;
import com.ing.payroll.services.PayrollServicesImpl;

import junit.framework.Assert;

public class PayrollServicesTest {

	private static PayrollServices payrollServices;
	private static PayrollDAOServices mockDAOServices;
	private List<Associate> associateList;
	Salary salary1 = new Salary(5000, 1000, 1000);
	Salary salary2=new Salary(5000, 1000, 2000);
	BankDetails bankDetails1 = new BankDetails(123, "SBI", "SCV123");
	BankDetails bankDetails2=new BankDetails(234,"Citi","C10000");

	@BeforeClass
	public static void setUpTestEnv() {
		mockDAOServices=Mockito.mock(PayrollDAOServices.class);
		payrollServices = new PayrollServicesImpl(mockDAOServices);
				
	}

	@Before
	public void setUpTestMockEnv() throws SQLException {
		Associate associate1 = new Associate(1000, 80000, "mohit", "Dewshmuhk", "morgan", "senior analyst", "BU123SDSD",
				"mohit.bn.md@gmail.com", salary1, bankDetails1);
		Associate associate2 = new Associate(1001, 3000, "Sapa", "Saha", "deutch", "Senior Analyst", "BVCNB1233",
				"sapa@gmailcv.vom", salary2, bankDetails2);
		
		associateList=new ArrayList<Associate>();
		associateList.add(associate1);
		associateList.add(associate2);
		
		Mockito.when(mockDAOServices.getAssociate(1000)).thenReturn(associate1);
		Mockito.when(mockDAOServices.getAssociate(1001)).thenReturn(associate2);

	}

	@Test(expected = AssociateDetailsNotFoundException.class)
	public void testGetAssociateDetailsForInvalidId() throws PayrollServicesDownException, AssociateDetailsNotFoundException, SQLException {
		payrollServices.getAssociateDetails(1234);
		Mockito.verify(mockDAOServices).getAssociate(1234);
	}

	@Test
	public void testGetAssociateDetailsForValidId() throws PayrollServicesDownException, AssociateDetailsNotFoundException {
		Associate expectedAssociate=new Associate(1000, 80000, "mohit", "Dewshmuhk", "morgan", "senior analyst", "BU123SDSD",
				"mohit.bn.md@gmail.com", salary1, bankDetails1);
		Associate actualAssociate=payrollServices.getAssociateDetails(1000);
	}

	@Test
	public void testGetAllAssociateDetails() throws PayrollServicesDownException, AssociateDetailsNotFoundException, SQLException {
		List<Associate> expectedAssociateList=associateList;
		List<Associate> actiualAssociateList=payrollServices.getAllAssociatesDetails();
	
		Mockito.verify(mockDAOServices).getAssociates();
		assertEquals(expectedAssociateList,actiualAssociateList);
	}
	
	@After
	public void tearUpTestMockEnv() {

	}

	@AfterClass
	public static void tearUpTestEnv() {
		payrollServices = null;
		mockDAOServices = null;
	}

}
