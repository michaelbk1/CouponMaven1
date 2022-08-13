package com.bnhp.CouponMaven.Services;

import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Company;
import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.exceptions.CouponSystemException;
import com.bnhp.CouponMaven.exceptions.ErrMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CompanyServiceImpl extends ClientService implements CompanyService {
    //@Autowired
    private Company company;
    //@Autowired
    private Coupon coupon;

    @Override
    public int login(String email, String password) {
        System.out.println("company login ");
        if (companyRepository.existsByEmailAndPassword(email, password)) {
            company = companyRepository.findByEmail(email);
            System.out.println("company login " + company.getName());
            return company.getId();
        }
        System.out.println("company login 0" );
        return 0;
    }

    @Override
    public void addCoupon(int companyId, Coupon newCoupon) throws CouponSystemException {
        if (couponRepository.existByCompanyIdAndTitle(companyId, newCoupon.getTitle()))
            throw new CouponSystemException(ErrMsg.COMPANY_COUPON_EXIST);

        couponRepository.save(newCoupon);
    }

    @Override
    public void updateCoupon(int couponId, Coupon updatedCoupon) throws CouponSystemException {
        if (updatedCoupon.getId() != couponId) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXIST);
        }
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXIST);
        }
        couponRepository.saveAndFlush(updatedCoupon);

    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSystemException {
        if (!couponRepository.existsById(couponId)) {
            throw new CouponSystemException(ErrMsg.COUPON_NOT_EXIST);
        }
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) throws CouponSystemException {
        return couponRepository.findAllByCompanyId(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, Category category) throws CouponSystemException {
        return  couponRepository.findAllByCompanyIdAndCategory(companyId,category);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId, double maxPrice) throws CouponSystemException {
        return couponRepository.findAllByCompanyIdAndPriceLessThan(companyId,maxPrice);
    }

//    @Override
//    public Company getCompanyDetails(int companyId) throws CouponSystemException {
//        return companyRepository.findById(companyId).orElseThrow((() -> new CouponSystemException(ErrMsg.COMPANY_NOT_FOUND)));
//    }

}
