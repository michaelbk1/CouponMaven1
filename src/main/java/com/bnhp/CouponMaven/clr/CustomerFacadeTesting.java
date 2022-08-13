package com.bnhp.CouponMaven.clr;

import com.bnhp.CouponMaven.Services.AdminService;
import com.bnhp.CouponMaven.Services.CustomerService;
import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.beans.Customer;
import com.bnhp.CouponMaven.security.LoginManager;
import com.bnhp.CouponMaven.utils.ClientType;
import com.bnhp.CouponMaven.utils.Print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class CustomerFacadeTesting implements CommandLineRunner {

    private Customer customer;
    private Coupon coupon;
    @Autowired
    LoginManager loginManager;

    @Autowired
    private AdminService adminService;

    @Autowired
    private CustomerService customerService;


    @Override
    public void run(String... args) throws Exception {
        Print.printCaption("CUSTOMER LOGGED IN");
        int customerId = loginManager.login("customer1@gmail.com", "1234", ClientType.Customer);
        customer = adminService.getSingleCustomer(customerId);
        System.out.println(customer);
        Print.printCaption("Customer Initial Coupons");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);
        //===================================================================================
        Print.printCaption("Purchace coupons 3");
        coupon = adminService.getSingleCoupon(3);
        customerService.purchaseCoupon(customerId,coupon );
        Print.printCaption("Get customers coupons");
        customerService.getCustomerCoupons(customerId).forEach(System.out::println);
        //===================================================================================
        Print.printCaption("Get customers coupons - Food");
        customerService.getCustomerCoupons(customerId, Category.Food).forEach(System.out::println);
        Print.printCaption("Get customers coupons - Max price 60");
        customerService.getCustomerCoupons(customerId, 60).forEach(System.out::println);


    }
}
