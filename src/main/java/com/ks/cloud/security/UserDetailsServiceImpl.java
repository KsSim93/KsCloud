package com.ks.cloud.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ks.cloud.entity.Users;
import com.ks.cloud.users.repository.UsersRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Users user = usersRepository.findByUserIdEquals(userId);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + userId);
        }
        // 비밀번호 암호화 및 사용자 역할 설정
        String encodedPassword = user.getPassword();
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(user.getRole());

        return new User(user.getUserId(),encodedPassword,authorities);
    }
    
}
