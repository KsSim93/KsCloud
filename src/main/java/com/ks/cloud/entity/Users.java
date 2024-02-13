package com.ks.cloud.entity;

import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
	private UUID id;
	@Column(columnDefinition = "VARCHAR(255)", unique = true)
    private String userId;
	@Column(columnDefinition = "VARCHAR(255)")
    private String username;
	@Column(columnDefinition = "VARCHAR(255)")
    private String password;
	@Column(columnDefinition = "VARCHAR(100)")
    private String role;
	
    public void setPassword(String password) {
        // 패스워드를 BCrypt 알고리즘을 사용하여 해시화
        this.password = new BCryptPasswordEncoder().encode(password);
    }
}
