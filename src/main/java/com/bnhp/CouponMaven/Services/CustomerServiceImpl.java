package com.bnhp.CouponMaven.Services;

import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.beans.Customer;
import com.bnhp.CouponMaven.exceptions.CouponSystemException;
import com.bnhp.CouponMaven.exceptions.ErrMsg;
import com.bnhp.CouponMaven.repos.CustomerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Data
public class CustomerServiceImpl extends ClientService implements CustomerService {
    private Customer customer;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public int login(String email, String password) {
        if (customerRepository.existsByEmailAndPassword(email, password)) {
            customer = customerRepository.findByEmail(email);
            return customer.getId();
        }
        return 0;
    }

    @Override
    public void purchaseCoupon(int customerId, Coupon coupon) throws CouponSystemException {
        LocalDate date = LocalDate.now();
        java.sql.Date currentDate = java.sql.Date.valueOf(date);

        int couponId = coupon.getId();

        if (couponRepository.existByCustomer(coupon.getId(), customerId))
            throw new CouponSystemException(ErrMsg.COUPON_CUSTOMER_EXIST);

        if (coupon.getAmount() == 0)
            throw new CouponSystemException(ErrMsg.COUPON_PURCHASE_SOLD_OUT);

        if (currentDate.after(coupon.getEndDate()))
            throw new CouponSystemException(ErrMsg.COUPON_PURCHASE_EXPIRED);


        coupon.setAmount(coupon.getAmount()-1 );
        couponRepository.saveAndFlush(coupon);

        /* add purchased coupon to customer */
        customer = getCustomerDetails(customerId);
        customer.getCoupons().add(coupon);
        customerRepository.saveAndFlush(customer);

    }
    @Override
    public List<Coupon> getCustomerCoupons(int customerId) throws CouponSystemException {
        List<Coupon>  coupons;
        coupons = couponRepository.findByCustomer(customerId);
        return coupons;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, Category category) throws CouponSystemException {
        List<Coupon> coupons = couponRepository.findByCustomerAndCategory(customerId, category.name());
        return coupons;
    }

    @Override
    public List<Coupon> getCustomerCoupons(int customerId, double maxPrice) throws CouponSystemException {
        List<Coupon> coupons = couponRepository.findByCustomerAndMaxPrice(customerId, maxPrice);
        return coupons;

    }

    @Override
    public Customer getCustomerDetails(int customerId) throws CouponSystemException {
        return customerRepository.findById(customerId).orElseThrow((() -> new CouponSystemException(ErrMsg.CUSTOMER_NOT_FOUND)));
    }


}
