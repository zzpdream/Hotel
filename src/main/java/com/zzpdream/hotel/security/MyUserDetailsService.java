package com.zzpdream.hotel.security;

import com.zzpdream.hotel.dao.UserDao;
import com.zzpdream.hotel.entity.User;
import com.zzpdream.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanyuli on 2018/5/28.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User userBean = new User();
        userBean.setName(s);
        User userInfo = userDao.findByName(s);
        if(userInfo == null) {
            throw new UsernameNotFoundException("username is not exist.");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userInfo.getRole().getName()));
        return new org.springframework.security.core.userdetails.User(userInfo.getUsername(),
                userInfo.getPassword(), authorities);
    }
}

