package com.jirepos.service;

import com.jirepos.CoreStringUtils;

public class UserService {
    public String userName(){
        return CoreStringUtils.append("aaa", "bbb");
    }
}
