package com.bnhp.CouponMaven.repos;

import com.bnhp.CouponMaven.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByEmail(String email);
    boolean existsByName(String name);
    boolean existsByEmailAndPassword(String email, String password);
    Company findByEmail(String email);
}





