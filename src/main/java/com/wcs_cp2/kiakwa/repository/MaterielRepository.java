package com.wcs_cp2.kiakwa.repository;

import java.util.UUID;

import com.wcs_cp2.kiakwa.model.Materiel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterielRepository extends JpaRepository<Materiel, UUID> {

}