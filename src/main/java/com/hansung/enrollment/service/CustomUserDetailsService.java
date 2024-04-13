package com.hansung.enrollment.service;

import com.hansung.enrollment.entity.Users;
import com.hansung.enrollment.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return User.builder()
                .username(users.getUsername())
                .password(users.getPassword())
                // 기본적으로 부여할 권한 설정
                .authorities("ROLE_USER")
                .build();
    }
}