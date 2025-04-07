package com.example.assjava6.repository;

import com.example.assjava6.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    @Query("SELECT a FROM Authority a WHERE a.account.username = :username")
    List<Authority> findByAccount_Username(String username);

}

