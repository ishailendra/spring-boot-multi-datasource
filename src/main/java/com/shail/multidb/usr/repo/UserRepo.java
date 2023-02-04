package com.shail.multidb.usr.repo;

import com.shail.multidb.usr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
}
