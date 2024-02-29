package com.ks.cloud.users.dto;

import lombok.Data;

@Data
public class UserDTO {

  private Long id;

  private String userId;

  private String username;

  private String password;

  private String role;
}
