package com.ks.cloud.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ks.cloud.entity.Users;
import com.ks.cloud.security.SecurityService;
import com.ks.cloud.users.dto.UserDTO;
import com.ks.cloud.users.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService{
  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private SecurityService securityService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public void saveUser(UserDTO userDTO) {
    Users user = new Users();

    user.setUserId(userDTO.getUserId());
    user.setUsername(userDTO.getUsername());
    user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
    user.setRole(userDTO.getRole());

    this.usersRepository.save(user);
  }

  @Override
  public boolean login(UserDTO userDTO) {
    return this.securityService.login(userDTO.getUserId(), userDTO.getPassword());
  }

  @Override
  public Users getUserInfo(String userId) {
    Users userInfo = usersRepository.findByUserIdEquals(userId);
    userInfo.setPassword(null);

    return userInfo;
  }
}
