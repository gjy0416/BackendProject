package com.yeogi_jeogi.login;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChangeableRepository extends JpaRepository<CHANGEABLEUSERS, Integer>{
	Optional<CHANGEABLEUSERS> findByID(String ID);
}
