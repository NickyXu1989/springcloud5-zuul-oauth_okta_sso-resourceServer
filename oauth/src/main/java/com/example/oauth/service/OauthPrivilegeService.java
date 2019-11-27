package com.example.oauth.service;

import com.example.oauth.dto.OauthPrivilegeDTO;

import java.util.List;

public interface OauthPrivilegeService {

    public List<OauthPrivilegeDTO> getPrivilegeByUserId(String id);
}
