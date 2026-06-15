package com.duoc.ratetheanime.repository;

import com.duoc.ratetheanime.model.Usuario_Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioLoginRepository extends JpaRepository<Usuario_Login, Integer> {
    Optional<Usuario_Login> findByUsername(String username);
}