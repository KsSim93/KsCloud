package com.ks.cloud.users.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ks.cloud.entity.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, UUID>{

	Users findByUserIdEquals(String userId);

}
