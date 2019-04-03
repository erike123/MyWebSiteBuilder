package com.example.demo.repository;

import com.example.demo.domein.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<com.example.demo.domein.entities.Role, String> {

    Role findByAuthority(String authority);
}
