package com.example.oauth.entity;

import com.example.oauth.dto.OauthPrivilegeDTO;
import com.example.oauth.dto.OauthUserDTO;
import com.example.oauth.service.OauthPrivilegeService;
import com.example.oauth.service.OauthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class OauthUserDetails extends OauthUserDTO implements UserDetails, Serializable {

    @Autowired
    private OauthPrivilegeService oauthPrivilegeService;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        List<OauthPrivilegeDTO> privileges = oauthPrivilegeService.getPrivilegeByUserId(this.getId());

        for(OauthPrivilegeDTO privilegeDTO : privileges) {
//            System.out.println();
            grantedAuthorities.add(new SimpleGrantedAuthority(privilegeDTO.getName()));
        }
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
