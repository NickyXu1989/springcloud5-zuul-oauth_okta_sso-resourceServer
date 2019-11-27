package com.example.oauth.service.impl;


import com.example.oauth.dao.OauthPrivilegeDAO;
import com.example.oauth.dto.OauthPrivilegeDTO;
import com.example.oauth.service.OauthPrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OauthPrivilegeServiceImpl implements OauthPrivilegeService {

    @Autowired
    private OauthPrivilegeDAO oauthPrivilegeDAO;

    @Override
    public List<OauthPrivilegeDTO> getPrivilegeByUserId(String id) {
        return oauthPrivilegeDAO.getPrivilegeByUserId(id);
    }
}
