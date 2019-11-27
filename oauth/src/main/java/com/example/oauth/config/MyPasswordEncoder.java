package com.example.oauth.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyPasswordEncoder implements PasswordEncoder {


    @Override
    public String encode(CharSequence arg0){
        return arg0.toString();
    }


    //        arg0为用户输入
    //        arg1为数据库存储

    @Override
    public boolean matches(CharSequence arg0,String arg1){
        System.out.println(arg0);
        System.out.println(arg1);
        return arg1.equals(arg0.toString());


//        if("1".equals(arg0.toString())){
//            return true;
//        }
//        return false;

    }
}
