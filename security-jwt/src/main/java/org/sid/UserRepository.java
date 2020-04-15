package org.sid;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserName(String userName);
    
    @Query("from Users u where u.userName = :x and u.password = :y")
    Users findByUserNameAndPassword(@Param("x") String userName,@Param("y") String password);
}
