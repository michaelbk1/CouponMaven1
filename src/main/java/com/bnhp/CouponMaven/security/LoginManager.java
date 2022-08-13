package com.bnhp.CouponMaven.security;

import com.bnhp.CouponMaven.Services.AdminService;
import com.bnhp.CouponMaven.Services.ClientService;
import com.bnhp.CouponMaven.Services.CompanyService;
import com.bnhp.CouponMaven.Services.CustomerService;
import com.bnhp.CouponMaven.exceptions.CouponSystemException;
import com.bnhp.CouponMaven.exceptions.ErrMsg;
import com.bnhp.CouponMaven.repos.CompanyRepository;
import com.bnhp.CouponMaven.repos.CouponRepository;
import com.bnhp.CouponMaven.repos.CustomerRepository;
import com.bnhp.CouponMaven.utils.ART_UTIL;
import com.bnhp.CouponMaven.utils.ClientType;

import com.bnhp.CouponMaven.utils.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginManager {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;




    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private CustomerRepository customerRepository;


    public int login(String email, String password, ClientType clientType) throws CouponSystemException {
        int id = -1;
        System.out.println("\n  LOGIN MANAGER STARTED ");

        System.out.println("  LOGIN MANAGER FOR " + clientType + " " + email + " " + password + "\n");
        ClientService service = null;

        switch (clientType) {
            case Administrator: {
                if (email.equals("admin@admin.com") && password.equals("admin")) {
                    System.out.println(ART_UTIL.FACADE);
                    service = (ClientService) adminService;
                } else {
                    throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
                }
                return 0;
            }
            case Company:
                service = (ClientService) companyService;
                id = service.login(email, password);
                if (id == 0) {
                    service = null;
                    throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
                }
                System.out.println(ART_UTIL.COMPANY);
                return  id;


            case Customer:
                service = (ClientService) customerService;
                if (service.login(email, password)==0) {
                    service = null;
                    throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
                }
                id = customerService.login(email, password);
                System.out.println(ART_UTIL.CUSTOMER);
                if (id == 0) {
                    service = null;
                    throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
                }

                Print.printCaption("Login failed" + email + " " + clientType);



                return id;
            default:
                throw new CouponSystemException(ErrMsg.NOT_AUTHORIZED);
        }

    }

}
