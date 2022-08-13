package com.bnhp.CouponMaven.controllers;


import com.bnhp.CouponMaven.Services.AdminService;
import com.bnhp.CouponMaven.Services.CompanyService;
import com.bnhp.CouponMaven.Services.CustomerService;
import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Company;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.beans.Customer;
import com.bnhp.CouponMaven.exceptions.CouponSystemException;
import com.bnhp.CouponMaven.security.Login;
import com.bnhp.CouponMaven.security.LoginManager;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.List;


@RestController
@RequestMapping("api/coupons")
@RequiredArgsConstructor
public class CouponSysController {

    @Autowired
    private LoginManager loginManager;
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;



    @GetMapping("/companies")
    public List<Company> getAllCompanies() {
        return adminService.getAllCompanies();
    }



    @GetMapping("/companies/{id}")
    public Company getSingleCompanies(@PathVariable int id) throws CouponSystemException
    {
            return adminService.getSingleCompany(id);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) throws CouponSystemException {
        adminService.deleteCompany(id);
    }


    @PutMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int id, @RequestBody Company company) throws CouponSystemException {
        adminService.updateCompany(id, company);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponSystemException
    {
        adminService.addCompany(company);
    }


    @GetMapping("/companyCoupons/{id}")
    public List<Coupon>getCompanyCoupons(@RequestParam int companyId) throws CouponSystemException
    {
        return companyService.getCompanyCoupons(companyId);
    }


    @GetMapping("/companyCoupons/{companyId}?{category}")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestParam Category category) throws CouponSystemException {
        System.out.println("getCompanyCoupons -> companyId=" + companyId + " category=" + category);
        return companyService.getCompanyCoupons(companyId, category);
    }

    @GetMapping("/companyCoupons/{companyId}?{maxPrice}")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId, @RequestParam double maxPrice) throws CouponSystemException {
        return companyService.getCompanyCoupons(companyId, maxPrice);
    }


    @PostMapping("/companyCoupons/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@PathVariable int companyId, @RequestBody Coupon couponDto) throws CouponSystemException {
        companyService.addCoupon(companyId, couponDto);
    }

    @PutMapping("/companyCoupons/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int companyId, @RequestBody Coupon couponDto) throws CouponSystemException {
        companyService.updateCoupon(companyId, couponDto);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return adminService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getSingleCustomer(@PathVariable int id) throws CouponSystemException {
        return adminService.getSingleCustomer(id);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int id, @RequestBody Customer  customerDto) throws CouponSystemException {
        adminService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) throws CouponSystemException {
        adminService.deleteCustomer(id);
    }
    @GetMapping("/customerCoupons/{customerId}")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerCoupons(customerId);
    }

    @GetMapping("/customerCoupons/{customerId}?{category}")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestParam Category category) throws CouponSystemException {
        return customerService.getCustomerCoupons(customerId, category);
    }

    @GetMapping("/customerCoupons/{customerId}?{maxPrice}")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestParam double maxPrice) throws CouponSystemException {
        return customerService.getCustomerCoupons(customerId, maxPrice);
    }

    @PostMapping("/customerCoupons/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int customerId, @RequestBody Coupon coupon) throws CouponSystemException {
        customerService.purchaseCoupon(customerId, coupon);
    }

}
