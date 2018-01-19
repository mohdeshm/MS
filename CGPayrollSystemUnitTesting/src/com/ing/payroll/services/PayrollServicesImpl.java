package com.ing.payroll.services;
import java.sql.SQLException;
import java.util.List;

import com.ing.payroll.beans.Associate;
import com.ing.payroll.beans.BankDetails;
import com.ing.payroll.beans.Salary;
import com.ing.payroll.daoservices.PayrollDAOServices;
import com.ing.payroll.exception.AssociateDetailsNotFoundException;
import com.ing.payroll.exception.PayrollServicesDownException;
public class PayrollServicesImpl implements PayrollServices{

	private PayrollDAOServices payrollDAOServices;
	public PayrollServicesImpl(PayrollDAOServices mockDAOServices) {
		payrollDAOServices=mockDAOServices;
	}

	@Override
	public int acceptAssociateDetails(String firstName, String lastName, String emailID, String department,
			String designation, String pancard, int yearlyInvestmentUnder80C, int basicSalary, int companyPf, int epf,
			int accountNumber, String bankName, String ifscCode) throws PayrollServicesDownException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int calculateNetSalary(int associateId)
			throws AssociateDetailsNotFoundException, PayrollServicesDownException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Associate getAssociateDetails(int associateId)
			throws PayrollServicesDownException, AssociateDetailsNotFoundException {
		
		return null;
	}

	@Override
	public List<Associate> getAllAssociatesDetails() throws PayrollServicesDownException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeAssociate(int associateId) throws PayrollServicesDownException {
		// TODO Auto-generated method stub
		return false;
	}
		
	
}
