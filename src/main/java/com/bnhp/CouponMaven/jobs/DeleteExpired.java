
package com.bnhp.CouponMaven.jobs;

import com.bnhp.CouponMaven.beans.Coupon;
import com.bnhp.CouponMaven.repos.CouponRepository;
import com.bnhp.CouponMaven.utils.Print;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


@Component
public class DeleteExpired{
    @Autowired
    private CouponRepository couponRepository;
    @Scheduled(fixedRate = 1000*60*60*24)
    @Transactional

    public void daily(){
        Print.printCaption("JOB DELETE BY END Date Greater Than");
        List<Coupon> coupons = couponRepository.deleteByEndDateGreaterThan(Date.valueOf(LocalDate.now()));
        coupons.forEach(System.out::println);
    }
}

