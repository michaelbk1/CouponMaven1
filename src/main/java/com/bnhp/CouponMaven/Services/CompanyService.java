package com.bnhp.CouponMaven.Services;

import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Company;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {

    public int login(String email, String password);

    public void addCoupon(int companyId, Coupon newCoupon) throws CouponSystemException;

    public void updateCoupon(int couponId, Coupon updatedCoupon) throws CouponSystemException;

    public void deleteCoupon(int couponId) throws CouponSystemException;

    public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException;

    public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemException;

    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemException;

//    public Company getCompanyDetails(int companyId) throws CouponSystemException;


}
