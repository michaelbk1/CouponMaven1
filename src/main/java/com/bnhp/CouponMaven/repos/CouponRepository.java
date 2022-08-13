package com.bnhp.CouponMaven.repos;

import com.bnhp.CouponMaven.beans.Category;
import com.bnhp.CouponMaven.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Query(value = "SELECT c.* FROM `coupon2`.`coupons` c join `coupon2`.`customers_coupons` cc on c.id=cc.coupons_id where cc.customer_id=?1", nativeQuery = true)
    List<Coupon> findByCustomer(int customerId);



    @Query(value = "SELECT case when EXISTS(SELECT * FROM `coupon2`.customers_coupons where `coupons_id` = ?1 and `customer_id` = ?2 ) then 'true' else 'false' end as a", nativeQuery = true)
    boolean existByCustomer(int couponId, int customerId);


    @Query(value = "SELECT c.* FROM `coupon2`.`coupons` c join `coupon2`.`customers_coupons` cc on c.id=cc.coupons_id where cc.customer_id=?1 and c.price<=?2", nativeQuery = true)
    List<Coupon> findByCustomerAndMaxPrice(int customerId, double maxPrice);

    @Query(value = "SELECT c.* FROM `coupon2`.`coupons` c join `coupon2`.`customers_coupons` cc on c.id=cc.coupons_id  where cc.customer_id=?1 and c.category=?2", nativeQuery = true)
    List<Coupon> findByCustomerAndCategory(int customerId, String category);

    @Query(value = "SELECT case when EXISTS(SELECT * FROM `coupon2`.`coupons` where `company_id` = ?1  and `title` = ?2) then 'true' else 'false' end as a", nativeQuery = true)
    boolean existByCompanyIdAndTitle(int companyId, String title);

    List<Coupon> findAllByCompanyId(int companyId);

    List<Coupon> findAllByCompanyIdAndCategory(int companyId, Category category);

    List<Coupon> findAllByCompanyIdAndPriceLessThan(int companyId, double maxPrice);

    List<Coupon> deleteByEndDateGreaterThan(Date valueOf);

}
