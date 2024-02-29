package com.ks.cloud.users.service;

import com.ks.cloud.entity.Users;
import com.ks.cloud.users.dto.UserDTO;

public interface UserService {
    void saveUser(UserDTO userDTO);

    boolean login(UserDTO userDTO);

    Users getUserInfo(String userId);
}
