package com.bnhp.CouponMaven.advice;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ErrDetails {

    private final String key = "Coupon";
    private String description;

}

