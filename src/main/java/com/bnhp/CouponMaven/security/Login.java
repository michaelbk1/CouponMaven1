package com.bnhp.CouponMaven.security;

import com.bnhp.CouponMaven.utils.ClientType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Login {
    private String email;
    private String Password;
    private ClientType clientType;
}
