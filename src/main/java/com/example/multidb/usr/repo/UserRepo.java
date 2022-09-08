package com.example.multidb.usr.repo;

import com.example.multidb.usr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
