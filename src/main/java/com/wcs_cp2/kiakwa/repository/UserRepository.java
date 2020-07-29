package com.wcs_cp2.kiakwa.repository;

import java.util.UUID;
import com.wcs_cp2.kiakwa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

}