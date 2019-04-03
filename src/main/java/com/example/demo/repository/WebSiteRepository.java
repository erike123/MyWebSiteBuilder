package com.example.demo.repository;

import com.example.demo.domein.entities.Website;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WebSiteRepository extends JpaRepository<Website,String> {

    Optional<Website> findById(String id);
}
