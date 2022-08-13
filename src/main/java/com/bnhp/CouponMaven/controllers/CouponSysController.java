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

    @GetMapping("/companycoupon/{id}")
    public List<Coupon>getCompanyCoupons(@RequestParam int companyId) throws CouponSystemException
    {
        return companyService.getCompanyCoupons(companyId);
    }


    //public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemException;

    //public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemException;

    //public Company getCompanyDetails(int companyId) throws CouponSystemException;


}



/*

    @GetMapping("nameAndWeight")
    public List<Cat> getAllCatsByNameAndWeight(@RequestParam String name,
                                               @RequestParam double weight) throws CouponSystemException {

        return catService.getAllCatsByNameAndWeight(name, weight);

    }

    @GetMapping("nameOrWeight")
    public List<Cat> getAllCatsByNameOrWeight(@RequestParam String name,
                                              @RequestParam double weight) throws CouponSystemException {
        return catService.getAllCatsByNameOrWeight(name, weight);

    }

    @GetMapping("weight/asc")
    public List<Cat> getAllCatsOrderByWeightAsc() {
        return catService.getAllCatsOrderByWeightAsc();
    }

    @GetMapping("weight/desc")
    public List<Cat> getAllCatsOrderByWeightDesc() {
        return catService.getAllCatsOrderByWeightDesc();
    }

    @GetMapping("name/stating")
    public List<Cat> getAllCatsStartingWith(@RequestParam String prefix) {
        return catService.getAllCatsStartingWith(prefix);
    }

    @GetMapping("weight/avg")
    public double getCatsWeightAvg() {
        return catService.getCatsWeightAvg();
    }


}

    */

/*






    */

/*

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addCat(@RequestBody Cat cat) {
        catService.addCat(cat);
    }


    @GetMapping("nameAndWeight")
    public List<Cat> getAllCatsByNameAndWeight(@RequestParam String name,
                                               @RequestParam double weight) throws CatSystemException {

        return catService.getAllCatsByNameAndWeight(name, weight);

    }

    @GetMapping("nameOrWeight")
    public List<Cat> getAllCatsByNameOrWeight(@RequestParam String name,
                                              @RequestParam double weight) throws CatSystemException {
        return catService.getAllCatsByNameOrWeight(name, weight);

    }

    @GetMapping("weight/asc")
    public List<Cat> getAllCatsOrderByWeightAsc() {
        return catService.getAllCatsOrderByWeightAsc();
    }

    @GetMapping("weight/desc")
    public List<Cat> getAllCatsOrderByWeightDesc() {
        return catService.getAllCatsOrderByWeightDesc();
    }

    @GetMapping("name/stating")
    public List<Cat> getAllCatsStartingWith(@RequestParam String prefix) {
        return catService.getAllCatsStartingWith(prefix);
    }

    @GetMapping("weight/avg")
    public double getCatsWeightAvg() {
        return catService.getCatsWeightAvg();
    }


* */

/*
    private final LoginManager loginManager;
    private final AdminService adminService;
    private final CompanyService companyService;
    private final CustomerService customerService;


    /*
    @GetMapping
    public List<Cat> getAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/{id}")
    public Cat getSingleCat(@PathVariable int id) throws CouponSystemException {
        return catService.getSingleCat(id);
    }


    @GetMapping("/")
    public int test() {
        return 999;
//        return loginManager.login(login.getEmail(), login.getPassword(), login.getClientType());
    }
*/
/*
    @PostMapping("/login")
    public int login(@RequestBody Login login) {
        System.out.println("***LOGIN***");
        return 0;//loginManager.login(login.getEmail(), login.getPassword(), login.getClientType());
    }



    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>customers");
        return adminService.getAllCustomers();
    }

    @GetMapping("/companies/{id}")
    public Company getSingleCompany(@PathVariable int id) throws CouponSystemException {
        return adminService.getSingleCompany(id);
    }


    @GetMapping("/customers/{id}")
    public Customer getSingleCustomer(@PathVariable int id) throws CouponSystemException {
        return adminService.getSingleCustomer(id);
    }


    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) throws CouponSystemException {
        adminService.addCompany(company);
    }

    @PostMapping("/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@RequestBody Customer customer) throws CouponSystemException {
        adminService.addCustomer(customer);
    }


    @PutMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@PathVariable int id, @RequestBody Company  companyDto) throws CouponSystemException {
        adminService.updateCompany(id, companyDto);
    }

    @PutMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int id, @RequestBody Customer  customerDto) throws CouponSystemException {
        adminService.updateCustomer(id, customerDto);
    }

    @DeleteMapping("/companies/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable int id) throws CouponSystemException {
        adminService.deleteCompany(id);
    }

    @DeleteMapping("/customers/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) throws CouponSystemException {
        adminService.deleteCustomer(id);
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

    @GetMapping("/customerCoupons/{customerId}")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId) throws CouponSystemException {
        return null;//customerService.getCustomerCoupons(customerId);
    }

    @GetMapping("/customerCoupons/{customerId}?{category}")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestParam Category category) throws CouponSystemException {
        return null;//customerService.getCustomerCoupons(customerId, category);
    }

    @GetMapping("/customerCoupons/{customerId}?{maxPrice}")
    public List<Coupon> getCustomerCoupons(@PathVariable int customerId, @RequestParam double maxPrice) throws CouponSystemException {
        return null;//customerService.getCustomerCoupons(customerId, maxPrice);
    }

    @PostMapping("/customerCoupons/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void purchaseCoupon(@PathVariable int customerId, @RequestBody Coupon coupon) throws CouponSystemException {
        customerService.purchaseCoupon(customerId, coupon);
    }


}
*/