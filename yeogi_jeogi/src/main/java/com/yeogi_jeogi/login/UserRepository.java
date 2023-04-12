package com.yeogi_jeogi.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<USERS, Integer>{
	Optional<USERS> findByID(String ID);
}
