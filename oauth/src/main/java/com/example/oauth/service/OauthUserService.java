package com.example.oauth.service;

import com.example.oauth.dto.OauthUserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OauthUserService {

    public List<OauthUserDTO> getAllUsers();
    public int saveUser(OauthUserDTO userDTO);
}
