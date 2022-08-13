package com.bnhp.CouponMaven.Services;

import com.bnhp.CouponMaven.beans.Company;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.beans.Customer;
import com.bnhp.CouponMaven.exceptions.CouponSystemException;

import com.bnhp.CouponMaven.exceptions.ErrMsg;
import com.bnhp.CouponMaven.repos.CompanyRepository;
import com.bnhp.CouponMaven.repos.CouponRepository;
import com.bnhp.CouponMaven.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ClientService implements AdminService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public int login(String email, String password) {
        return 0;
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsByEmail(company.getEmail())) {
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_ALREADY_EXIST);
        }
        if (companyRepository.existsByName(company.getName())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_ALREADY_EXIST);
        }
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        if (!companyRepository.existsById(company.getId())) {
            throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
        } else {
            Company companyDb = companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND));
            if (companyDb.getName() != company.getName()) {
                throw new CouponSystemException(ErrMsg.COMPANY_NAME_NOT_UPDATABLE);
            }
            if (companyDb.getEmail() != company.getEmail()) {
                throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_NOT_UPDATABLE);
            }

            try {
                companyRepository.saveAndFlush(company);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        try {
            System.out.println(companyRepository.existsById(companyId));
            if (!companyRepository.existsById(companyId)) {
                throw new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND);
            }
            companyRepository.deleteById(companyId);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();


    }

    @Override
    public Company getSingleCompany(int companyID) throws CouponSystemException {
        return companyRepository.findById(companyID).orElseThrow(() -> new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        try {
            if (customerRepository.existsByEmail(customer.getEmail())) {
                throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_ALREADY_EXIST);
            }

            customerRepository.save(customer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (customerRepository.getOne(customer.getId()) == null) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND);
        }
        Customer fetchedCustomer = this.customerRepository.findById(customer.getId()).orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND));
        try {
            fetchedCustomer.setEmail(customer.getEmail());
            fetchedCustomer.setPassword(customer.getPassword());
            fetchedCustomer.setFirstName(customer.getFirstName());
            fetchedCustomer.setLastName(customer.getLastName());
            customerRepository.save(fetchedCustomer);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void deleteCustomer(int customerID) throws CouponSystemException {
        if (!this.customerRepository.existsById(customerID)) {
            throw new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND);
        }
        customerRepository.deleteById(customerID);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(Integer customerID) throws CouponSystemException {
        return customerRepository.findById(customerID).orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND));
    }

    public Coupon getSingleCoupon(Integer couponID) throws CouponSystemException {
        return couponRepository.findById(couponID).orElseThrow(() -> new CouponSystemException(ErrMsg.COUPON_NOT_EXIST));
    }

}