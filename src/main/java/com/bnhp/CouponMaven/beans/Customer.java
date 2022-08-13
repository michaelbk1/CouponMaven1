package com.bnhp.CouponMaven.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    @Singular
    @JsonIgnore
    private List<Coupon> coupons = new ArrayList<>();


}


