package com.bnhp.CouponMaven.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.MERGE},mappedBy = "company")
    @Singular
    @ToString.Exclude
    @JsonIgnore
    private  List<Coupon> coupons = new ArrayList<>();


}
