package com.ing.billing.services;

import java.util.List;

import com.ing.billing.beans.Bill;
import com.ing.billing.beans.Customer;
import com.ing.billing.beans.Plan;
import com.ing.billing.beans.PostpaidAccount;
import com.ing.billing.daoservices.BillingDAOServices;
import com.ing.billing.exceptions.BillDetailsNotFoundException;
import com.ing.billing.exceptions.BillingServicesDownException;
import com.ing.billing.exceptions.CustomerDetailsNotFoundException;
import com.ing.billing.exceptions.InvalidBillMonthException;
import com.ing.billing.exceptions.PlanDetailsNotFoundException;
import com.ing.billing.exceptions.PostpaidAccountNotFoundException;

public class BillingServicesImpl implements BillingServices {

	BillingDAOServices billingDAOServices;
	PostpaidAccount postpaidAccount;

	@Override
	public List<Plan> getPlanAllDetails() throws BillingServicesDownException {
		return billingDAOServices.getAllPlans();
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planID)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {
		long mobileNo = 1234567890L;
		Plan plan = billingDAOServices.getPlan(planID);
		if (plan == null)
			throw new PlanDetailsNotFoundException("Plan Not Found");
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		postpaidAccount = new PostpaidAccount(mobileNo, plan, customer, null);
		billingDAOServices.insertPostPaidAccount(customerID, postpaidAccount);
		return mobileNo;
	}

	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillingServicesDownException, PlanDetailsNotFoundException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		double monthlyBill = noOfLocalSMS * 0.25 + noOfStdSMS * 1 + noOfLocalCalls * 1 + noOfStdCalls * 2
				+ internetDataUsageUnits * 5;

		return monthlyBill;
	}

	@Override
	public Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		return customer;
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BillingServicesDownException {
		return billingDAOServices.getAllCustomers();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		PostpaidAccount postpaidAccount = billingDAOServices.getCustomerPostPaidAccount(customerID, mobileNo);
		if (postpaidAccount == null)
			throw new PostpaidAccountNotFoundException("Postpaid account not found");
		if (postpaidAccount == null)
			throw new PostpaidAccountNotFoundException();
		return postpaidAccount;
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		return billingDAOServices.getCustomerPostPaidAccounts(customerID);
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException, BillingServicesDownException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		PostpaidAccount postpaidAccount = billingDAOServices.getCustomerPostPaidAccount(customerID, mobileNo);
		if (postpaidAccount == null)
			throw new PostpaidAccountNotFoundException("Postpaid account not found");
		
		Bill bill = billingDAOServices.getMonthlyBill(customerID, mobileNo, billMonth);
		if (bill == null)
			throw new BillDetailsNotFoundException("Bill details not found");
		return bill;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		return billingDAOServices.getCustomerPostPaidAccountAllBills(customerID, mobileNo);
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID) throws CustomerDetailsNotFoundException,
			PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException {
		Plan plan = billingDAOServices.getPlan(planID);
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		PostpaidAccount postpaidAccount = billingDAOServices.getCustomerPostPaidAccount(customerID, mobileNo);
		if (postpaidAccount == null)
			throw new PostpaidAccountNotFoundException("Postpaid account not found");
		postpaidAccount.setPlan(plan);
		billingDAOServices.updatePostPaidAccount(customerID, postpaidAccount);
		return true;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		PostpaidAccount postpaidAccount = billingDAOServices.getCustomerPostPaidAccount(customerID, mobileNo);
		if (postpaidAccount == null)
			throw new PostpaidAccountNotFoundException("Postpaid account not found");
		return billingDAOServices.deletePostPaidAccount(customerID, mobileNo);
	}

	@Override
	public boolean deleteCustomer(int customerID)
			throws BillingServicesDownException, CustomerDetailsNotFoundException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		billingDAOServices.deleteCustomer(customerID);
		return true;
	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			PlanDetailsNotFoundException {
		Customer customer = billingDAOServices.getCustomer(customerID);
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		PostpaidAccount postpaidAccount = billingDAOServices.getCustomerPostPaidAccount(customerID, mobileNo);
		if (postpaidAccount == null)
			throw new PostpaidAccountNotFoundException("Postpaid account not found");
		return billingDAOServices.getPlanDetails(customerID, mobileNo);
	}

	@Override
	public Customer acceptCustomerDetails(Customer customer) throws BillingServicesDownException {
		return billingDAOServices.insertCustomer(customer);
	}

	@Override
	public boolean authenticateCustomer(Customer customer)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		customer = billingDAOServices.getCustomer(customer.getCustomerID());
		if (customer == null)
			throw new CustomerDetailsNotFoundException("Customer not found");
		return true;
	}

}
