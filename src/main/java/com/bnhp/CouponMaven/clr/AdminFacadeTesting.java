package com.bnhp.CouponMaven.clr;


import com.bnhp.CouponMaven.Services.AdminService;
import com.bnhp.CouponMaven.beans.Company;
import com.bnhp.CouponMaven.beans.Customer;
import com.bnhp.CouponMaven.repos.CustomerRepository;
import com.bnhp.CouponMaven.security.LoginManager;
import com.bnhp.CouponMaven.utils.ART_UTIL;
import com.bnhp.CouponMaven.utils.ClientType;
import com.bnhp.CouponMaven.utils.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.bnhp.CouponMaven.security.*;

@Component
@Order(2)
public class AdminFacadeTesting implements CommandLineRunner {
    @Autowired
    private AdminService adminService;

    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        Print.printCaption("ADMIN");

        Print.printCaption(" INITIAL ALL COMPANIES ");
        adminService.getAllCompanies().forEach(System.out::println);
        //######################################################
        Company company  = Company.builder().email("comp6@gmail.com").name("company6").password("1234").build();
        Print.printCaption(" ADD COMPANY ");
        adminService.addCompany(company);
        adminService.getAllCompanies().forEach(System.out::println);
        //######################################################
        Print.printCaption(" ADD COMPANY 6 (duplicate email) " );
        try {
            adminService.addCompany(company);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //######################################################
        System.out.println("ADD COMPANY 6 (duplicate name)");
        try {
            company.setEmail("aaaa@gmail.com");
            adminService.addCompany(company);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //######################################################
        Print.printCaption("get Single Company  ");
        System.out.println(adminService.getSingleCompany(6));
        //######################################################
        Print.printCaption(" update  Company ");
        //company.setName("test update company");
        //adminService.updateCompany(6,company);
        System.out.println(adminService.getSingleCompany(6));
        //######################################################
        Print.printCaption(" DELETE company8 ");
        adminService.deleteCompany(8);
        //######################################################
        Print.printCaption(" DELETE company 6 ");
        adminService.deleteCompany(6);
        adminService.getAllCompanies().forEach(System.out::println);
        //######################################################
        System.out.println(ART_UTIL.CUSTOMER);
        Print.printCaption("==INITIAL ALL CUSTOMERS==");
        adminService.getAllCustomers().forEach(System.out::println);
        Print.printCaption("==ADD CUSTOMER customer6==");
        Customer customer = Customer.builder().firstName("customer6").lastName("Customer").email("customer6@gmail.com").password("1234").build();
        adminService.addCustomer(customer);
        //#####################################################
         adminService.getAllCustomers().forEach(System.out::println);
        Print.printCaption(" ADD CUSTOMER customer6 (duplicate) ");
        try {
            adminService.addCustomer(customer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //######################################################
        Print.printCaption(" update CUSTOMER customer 6 ");
        Customer cust2 = adminService.getSingleCustomer(2);
        cust2.setLastName("test2");
        adminService.updateCustomer(2,cust2);
        System.out.println(adminService.getSingleCustomer(2));
        // ######################################################
        Print.printCaption(" DELETE CUSTOMER customer 6 ");
        adminService.deleteCustomer(6);
        try {
            adminService.getSingleCustomer(6);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Print.printCaption(" ALL CUSTOMERS ");
        adminService.getAllCustomers().forEach(System.out::println);


        //adminService = (AdminService) loginManager.login("admin@admin.com","admin",  ClientType.Administrator);
    }
}
