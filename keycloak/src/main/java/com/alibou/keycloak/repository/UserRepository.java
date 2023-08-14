package com.alibou.keycloak.repository;

import com.alibou.keycloak.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
