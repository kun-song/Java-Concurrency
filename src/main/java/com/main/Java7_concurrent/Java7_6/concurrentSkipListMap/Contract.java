package com.main.Java7_concurrent.Java7_6.concurrentSkipListMap;

/**
 * Author:  satansk
 * Date:    10:18 at 2015/7/18
 * Email:   satansk@hotmail.com
 */
public class Contract {
    private String name;
    private String phone;

    public Contract(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
