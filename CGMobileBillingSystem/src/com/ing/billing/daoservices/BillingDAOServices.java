package com.ing.billing.daoservices;
import java.util.List;

import com.ing.billing.beans.Bill;
import com.ing.billing.beans.Customer;
import com.ing.billing.beans.Plan;
import com.ing.billing.beans.PostpaidAccount;


public interface BillingDAOServices {
	Customer insertCustomer(Customer customer) ;
	long insertPostPaidAccount(int customerID, PostpaidAccount account);
	boolean updatePostPaidAccount(int customerID, PostpaidAccount account);
	double insertMonthlybill(int customerID, long mobileNo, Bill bill);
	int insertPlan(Plan plan) ;
	boolean deletePostPaidAccount(int customerID, long mobileNo);
	Bill getMonthlyBill(int customerID, long mobileNo, String billMonth);
	List<Bill>getCustomerPostPaidAccountAllBills(int customerID, long mobileNo);
	List<PostpaidAccount> getCustomerPostPaidAccounts(int customerID);
	Customer getCustomer(int customerID);
	List<Customer>  getAllCustomers();
    List<Plan> getAllPlans();
	Plan getPlan(int planID) ;
	PostpaidAccount getCustomerPostPaidAccount(int customerID, long mobileNo);
	Plan getPlanDetails(int customerID, long mobileNo);
	boolean deleteCustomer(int customerID);
}