package com.example.oauth.controller;

import com.example.oauth.dao.OauthUserDAO;
import com.example.oauth.dto.OauthUserDTO;
import com.example.oauth.service.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping(value = "/test")
public class OauthController {

    @Autowired
    private OauthUserService oauthUserService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }


    @RequestMapping(value = "/userTest", method = RequestMethod.GET)
    public Map getAllUsers() {
        Map result = new HashMap();
        List<OauthUserDTO> userInfo = oauthUserService.getAllUsers();
        result.put("userInfo", userInfo);
        return result;
    }


    @RequestMapping(value = "/newUser", method = RequestMethod.POST)
    public Map saveUser(
//            @RequestParam("id") String id,
            @RequestParam("avatar") String avatar,
            @RequestParam("first_name") String firstName,
            @RequestParam("last_name") String lastName,
            @RequestParam("mail_address") String mailAddress,
            @RequestParam("user_name") String userName,
            @RequestParam("password") String password
    ) {
        Map result = new HashMap();
        OauthUserDTO user = new OauthUserDTO();
        user.setId(UUID.randomUUID().toString());
        user.setAvatar(avatar);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setMailAddress(mailAddress);
        user.setUsername(userName);
        user.setPassword(password);
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        user.setEnabled(true);
        try {
            int count = oauthUserService.saveUser(user);
            result.put("count", count);
        } catch (Exception e) {
            throw e;
        }

        return result;
    }
}
