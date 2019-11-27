package com.example.oauth.dao;

import com.example.oauth.dto.OauthPrivilegeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface OauthPrivilegeDAO {
    public List<OauthPrivilegeDTO> getPrivilegeByUserId(String id);
}
