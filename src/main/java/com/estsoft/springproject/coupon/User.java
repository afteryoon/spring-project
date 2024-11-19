package com.estsoft.springproject.coupon;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<ICoupon> coupons;
    
    public User(String id) {
        coupons = new ArrayList<>();
    }
    
    public int getTotalCouponCount() {
        return coupons.size();
    }


    public void addCoupon(ICoupon dummyCoupon) {
        if (dummyCoupon.isValid()) {
            coupons.add(dummyCoupon);
        }
    }
}
