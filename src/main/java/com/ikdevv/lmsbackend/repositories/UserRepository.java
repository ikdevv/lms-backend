package com.ikdevv.lmsbackend.repositories;

import com.ikdevv.lmsbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User , Integer> {
    User findByUsername(String username);
}
