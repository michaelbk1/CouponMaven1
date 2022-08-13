package com.bnhp.CouponMaven.clr;

import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Company;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.beans.Customer;
import com.bnhp.CouponMaven.repos.CompanyRepository;
import com.bnhp.CouponMaven.repos.CouponRepository;
import com.bnhp.CouponMaven.repos.CustomerRepository;
import com.bnhp.CouponMaven.utils.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
@Order(1)
public class Init implements CommandLineRunner {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        //Company
        Print.printCaption("INIT");
        Print.printCaption("INIT COMPANIES");
        Company company1 = Company.builder().email("comp1@gmail.com").name("company1").password("1234").build();
        Company company2 = Company.builder().email("comp2@gmail.com").name("company2").password("1234").build();
        Company company3 = Company.builder().email("comp3@gmail.com").name("company3").password("1234").build();
        Company company4 = Company.builder().email("comp4@gmail.com").name("company4").password("1234").build();
        Company company5 = Company.builder().email("comp5@gmail.com").name("company5").password("1234").build();
        companyRepository.saveAll(Arrays.asList(company1, company2, company3, company4, company5));
        companyRepository.findAll().forEach(System.out::println);
//##########################################################################################################################
        LocalDate date = LocalDate.now();
        java.sql.Date sqlDateStart = java.sql.Date.valueOf(date);
        java.sql.Date sqlDateEnd = java.sql.Date.valueOf(date.plusYears(1));



        Coupon coupon1 = Coupon.builder()
                .category(Category.Food)
                .company(company1)
                .amount(100)
                .description("description 1")
                .startDate(sqlDateStart)
                .endDate(sqlDateEnd)
                .title("title 1")
                .price(100.25)
                .image("aaaa")
                .build();
        Coupon coupon2 = Coupon.builder()
                .category(Category.Restaurant)
                .company(company1)
                .amount(50)
                .description("description 2")
                .startDate(sqlDateStart)
                .endDate(sqlDateEnd)
                .title("title 2")
                .price(100.25)
                .image("aaaa")
                .build();
        Coupon coupon3 = Coupon.builder()
                .category(Category.Food)
                .company(company2)
                .amount(45)
                .description("description 3")
                .startDate(sqlDateStart)
                .endDate(sqlDateEnd)
                .title("title 3")
                .price(50)
                .image("aaaa")
                .build();
        sqlDateStart = java.sql.Date.valueOf(date.minusDays(10));
        sqlDateEnd = java.sql.Date.valueOf(date.minusDays(2));

        Coupon coupon4 = Coupon.builder()
                .category(Category.Food)
                .company(company3)
                .amount(1000)
                .description("description 4")
                .startDate(sqlDateStart)
                .endDate(sqlDateEnd)
                .title("title 4")
                .price(32)
                .image("aaaa")
                .build();
        Coupon coupon5 = Coupon.builder()
                .category(Category.Restaurant)
                .company(company4)
                .amount(100)
                .description("description 5")
                .startDate(sqlDateStart)
                .endDate(sqlDateEnd)
                .title("title 5")
                .price(57)
                .image("aaaa")
                .build();
        couponRepository.saveAll(Arrays.asList(coupon1, coupon2, coupon3, coupon4, coupon5));
        Print.printCaption("INIT COMPANY COUPONS");
        couponRepository.findAll().forEach(System.out::println);

        Print.printCaption("INIT CUSTOMERS");
        Customer customer1 = Customer.builder().firstName("Customer").lastName("c1").email("customer1@gmail.com").password("1234").coupons(Arrays.asList(coupon1, coupon2)).build();
        Customer customer2 = Customer.builder().firstName("Customer").lastName("c2").email("customer2@gmail.com").password("1234").build();
        Customer customer3 = Customer.builder().firstName("Customer").lastName("c3").email("customer3@gmail.com").password("1234").build();
        Customer customer4 = Customer.builder().firstName("Customer").lastName("c4").email("customer4@gmail.com").password("1234").build();
        Customer customer5 = Customer.builder().firstName("Customer").lastName("c5").email("customer5@gmail.com").password("1234").build();
        customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3, customer4, customer5));
        customerRepository.findAll().forEach(System.out::println);

        Print.printCaption("INIT CLOSE");
    }
}
