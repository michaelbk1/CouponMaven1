package com.bnhp.CouponMaven.Services;

import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.beans.Customer;
import com.bnhp.CouponMaven.exceptions.CouponSystemException;
import java.util.List;

public interface CustomerService {

    public int login(String email, String password);
    public void purchaseCoupon(int customerId, Coupon coupon) throws CouponSystemException;
    public List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException;
    public List<Coupon> getCustomerCoupons(int customerId, Category category) throws CouponSystemException;
    public List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws CouponSystemException;
    public Customer getCustomerDetails(int customerId) throws CouponSystemException;
}




