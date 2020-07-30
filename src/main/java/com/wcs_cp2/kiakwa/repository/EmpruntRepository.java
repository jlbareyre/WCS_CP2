package com.wcs_cp2.kiakwa.repository;

import com.wcs_cp2.kiakwa.model.Emprunt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long>{
    
}