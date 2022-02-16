package com.springsecuritydemo.data.persistance;

import com.springsecuritydemo.data.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilageRepository extends JpaRepository<Privilege, Long> {
    Privilege findByAuthority(String authority);
}
