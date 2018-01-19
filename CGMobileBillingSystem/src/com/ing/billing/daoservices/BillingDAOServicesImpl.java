package com.ing.billing.daoservices;
import java.util.List;

import com.ing.billing.beans.Bill;
import com.ing.billing.beans.Customer;
import com.ing.billing.beans.Plan;
import com.ing.billing.beans.PostpaidAccount;
public class BillingDAOServicesImpl implements BillingDAOServices {

	@Override
	public Customer insertCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long insertPostPaidAccount(int customerID, PostpaidAccount account) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean updatePostPaidAccount(int customerID, PostpaidAccount account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double insertMonthlybill(int customerID, long mobileNo, Bill bill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertPlan(Plan plan) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deletePostPaidAccount(int customerID, long mobileNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bill getMonthlyBill(int customerID, long mobileNo, String billMonth) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBills(int customerID, long mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostpaidAccount> getCustomerPostPaidAccounts(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer getCustomer(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Plan> getAllPlans() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plan getPlan(int planID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostpaidAccount getCustomerPostPaidAccount(int customerID, long mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Plan getPlanDetails(int customerID, long mobileNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCustomer(int customerID) {
		// TODO Auto-generated method stub
		return false;
	}
}