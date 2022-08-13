package com.bnhp.CouponMaven.beans;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Table(name = "coupons")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;
    @Enumerated(EnumType.STRING)
    private Category category;
    private String title;
    private String description;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int amount;
    private double price;
    private String image;

}



