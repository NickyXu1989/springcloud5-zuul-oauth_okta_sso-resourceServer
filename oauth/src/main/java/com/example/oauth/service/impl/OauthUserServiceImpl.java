package com.example.oauth.service.impl;

import com.example.oauth.dao.OauthUserDAO;
import com.example.oauth.dto.OauthUserDTO;
import com.example.oauth.service.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OauthUserServiceImpl implements OauthUserService {

    @Autowired
    private OauthUserDAO oauthUserDAO;

    @Override
    public List<OauthUserDTO> getAllUsers() {
        List<OauthUserDTO> users = oauthUserDAO.getAllUsers();
        return users;
    }

    @Override
    @Transactional
    public int saveUser(OauthUserDTO userDTO) {
//        try {
//            int count = oauthUserDAO.saveUser(userDTO);
//            return count;
//        } catch (Exception exception) {
//            throw exception;
//        }
        int count = 0;
        count = oauthUserDAO.countUserByName(userDTO.getUsername());
        if(count > 0) {
            return 0;
        }
        else {
            count = oauthUserDAO.saveUser(userDTO);
            return count;
        }
    }

}
