package com.bnhp.CouponMaven.Services;

import com.bnhp.CouponMaven.beans.Company;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.beans.Customer;
import com.bnhp.CouponMaven.exceptions.CouponSystemException;


import java.util.List;

public interface AdminService {
    public int login(String email, String password);
    //###############################################################################
    public void addCompany(Company  company ) throws CouponSystemException;
    public void updateCompany(int companyId, Company company) throws CouponSystemException;
    public void deleteCompany(int companyID) throws CouponSystemException;
    public List<Company> getAllCompanies();
    public Company getSingleCompany(int companyID) throws CouponSystemException;
    //###############################################################################
    public void addCustomer(Customer customer) throws CouponSystemException;
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException;
    public void deleteCustomer(int customerID) throws CouponSystemException;
    public List<Customer> getAllCustomers();
    public Customer getSingleCustomer(Integer customerID) throws CouponSystemException;
    //###############################################################################
    public Coupon getSingleCoupon(Integer couponID) throws CouponSystemException;

}

