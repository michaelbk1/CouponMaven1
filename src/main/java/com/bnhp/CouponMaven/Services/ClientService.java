package com.bnhp.CouponMaven.Services;

import com.bnhp.CouponMaven.repos.CompanyRepository;
import com.bnhp.CouponMaven.repos.CouponRepository;
import com.bnhp.CouponMaven.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {

    @Autowired
    protected CompanyRepository companyRepository;

    @Autowired
    protected CouponRepository couponRepository;

    @Autowired
    protected CustomerRepository customerRepository;

    public abstract int login(String email, String password);

}
