package com.ksk.sjsecurity.service;

import com.ksk.sjsecurity.entity.UserInfo;
import com.ksk.sjsecurity.mapper.UserDetailsMapper;
import com.ksk.sjsecurity.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserInfoDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = userInfoRepository.findByName(username);
        return userInfo.map(UserDetailsMapper::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
