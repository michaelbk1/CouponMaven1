package com.bnhp.CouponMaven;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication //@Configuration + @ComponentScan(basePakages = "com.bnhp.CouponMaven")
@EnableScheduling

public class CouponMavenApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CouponMavenApplication.class, args);

		System.out.println("Spring IOC Container was loaded");

	}

}
