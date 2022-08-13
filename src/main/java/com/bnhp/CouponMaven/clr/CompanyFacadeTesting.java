package com.bnhp.CouponMaven.clr;


import com.bnhp.CouponMaven.Services.AdminService;
import com.bnhp.CouponMaven.Services.CompanyService;
import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Company;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.security.LoginManager;
import com.bnhp.CouponMaven.utils.ClientType;
import com.bnhp.CouponMaven.utils.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
@Order(3)
public class CompanyFacadeTesting implements CommandLineRunner {

    private Company company;

    @Autowired
    LoginManager loginManager;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CompanyService companyService;



    @Override
    public void run(String... args) throws Exception {
        int companyId = loginManager.login("comp1@gmail.com", "1234", ClientType.Company);
        System.out.println(companyId);
        Print.printCaption("Logged in " +  companyId);
        company = adminService.getSingleCompany(companyId);
        LocalDate date = LocalDate.now();
        java.sql.Date sqlDateStart = java.sql.Date.valueOf(date);
        java.sql.Date sqlDateEnd = java.sql.Date.valueOf(date.plusMonths(3));
        //===================================================================================
        Print.printCaption("COMPANY INFO");
        System.out.println(company);
        companyService.getCompanyCoupons(companyId).forEach(System.out::println);
        //===================================================================================
        Print.printCaption("ADD COUPON");
        Coupon coupon = Coupon.builder().category(Category.Electricity).company(company).amount(100).
                description("coupon company fascade").startDate(sqlDateStart).endDate(sqlDateEnd).
                title("company coupon 1").price(10.99).image("bbbb").build();
        try {
            companyService.addCoupon(companyId, coupon);
            companyService.getCompanyCoupons(companyId).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //===================================================================================
        Print.printCaption("ADD DUPLICATE COUPON");
        try {
            companyService.addCoupon(companyId, coupon);
            companyService.getCompanyCoupons(company.getId()).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Print.printCaption("UPDATE COUPON 6");
        coupon.setId(6);
        coupon.setPrice(12);
        companyService.updateCoupon(6, coupon);
        companyService.getCompanyCoupons(company.getId()).forEach(System.out::println);
        //===================================================================================
        Print.printCaption("GET COMPANY COUPONS MAX PRICE");
        companyService.getCompanyCoupons(companyId,100).forEach(System.out::println);
        //===================================================================================
        Print.printCaption("GET COMPANY COUPONS AND CATEGORY");
        companyService.getCompanyCoupons(companyId, Category.Food).forEach(System.out::println);
    }
}
